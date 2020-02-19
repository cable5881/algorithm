import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class DemoTest {


    @Test
    public void testRateLimiter() throws InterruptedException {
        final RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                rateLimiter.acquire();
                System.out.println(System.currentTimeMillis() / 1000);
            }).start();
        }

        Thread.sleep(10000);
    }

    @Test
    public void testForkJoin() {
        // 创建随机数组成的数组:
        long[] array = new long[400];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10);
        }
        // 最大并发数4
        ForkJoinPool fjp = new ForkJoinPool(4);
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        //提交一个Fork/Join任务并发执行，然后获得异步执行的结果
        Long result = fjp.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }
}

class SumTask extends RecursiveTask<Long> {

    static final int THRESHOLD = 100;
    long[] array;
    int start;
    int end;

    SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println(String.format("compute %d~%d = %d", start, end, sum));
            return sum;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;
        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        SumTask subtask1 = new SumTask(this.array, start, middle);
        SumTask subtask2 = new SumTask(this.array, middle, end);

        //不能调用subtask1.join(), subtask2.join()。
        //当对两个子任务调用fork()时，这个Worker线程就会把任务分配给另外两个Worker，但是它自己却停下来等待不干活了！
        //这样就白白浪费了Fork/Join线程池中的一个Worker线程，导致了4个子任务至少需要7个线程才能并发执行。
        //invokeAll的N个任务中，其中N-1个任务会使用fork()交给其它线程执行，但是，它还会留一个任务自己执行，
        //这样，就充分利用了线程池，保证没有空闲的不干活的线程
        invokeAll(subtask1, subtask2);

        Long subresult1 = subtask1.join();
        Long subresult2 = subtask2.join();
        Long result = subresult1 + subresult2;
        System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
        return result;
    }
}