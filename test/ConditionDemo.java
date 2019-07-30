import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liqibo
 * @description TODO
 * @date 2019/7/10 15:22
 **/
public class ConditionDemo {

    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    private volatile static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        ConditionDemo demo = new ConditionDemo();

        ThreadGroup tg = new ThreadGroup("tg");
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(tg, () -> {
                try {
                    demo.test();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        Thread.sleep(3000);
        System.out.println(count);
    }

    public void test() throws InterruptedException {
        lock.lock();
        for (int i = 0; i < 4; i++) {
            condition.signalAll();
            count++;
            condition.await();
        }
        condition.signalAll();
        lock.unlock();
    }

}
