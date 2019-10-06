import org.junit.Test;

import java.math.BigInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DemoTest {


    @Test
    public void main() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        BigInteger cmp = new BigInteger("10000");
        Thread t = new Thread(new Runnable() {
            BigInteger i = new BigInteger("0");
            @Override
            public void run() {
                for (int j = 0; j < 10000000; j++) {
                    lock.lock();
                    i = i.add(new BigInteger("1"));
                    //if (Thread.currentThread().isInterrupted()) {
                    //    break;
                    //}
                    if (i.equals(cmp)) {
                        System.out.println(i.toString());
                        Thread.currentThread().interrupt();
                    }
                    lock.unlock();
                }
                System.out.println(i.toString());
            }
        });

        t.start();
        Thread.sleep(700);
        t.interrupt();
        Thread.sleep(700);
        System.out.println("Done");

    }
}