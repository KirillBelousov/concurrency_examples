import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadInterruptSamples {

    @Test
    public void threadMustHandleItsInterrupt() throws InterruptedException {
        class MyRunnable implements Runnable {

            public void run() {
                for(long i=0; i<Long.MAX_VALUE; ++i) {
                    System.out.println(i);
                }
            }
        }

        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();
        Thread.sleep(200);
        t.interrupt();
        t.join();
    }

    @Test
    public void threadMustHandleItsInterrupt2() throws InterruptedException {
        class MyRunnable implements Runnable {

            public void run() {
                for(long i=0; i<Long.MAX_VALUE; ++i) {
                    System.out.println(i);
                }
                System.out.println("Out");
            }
        }

        MyRunnable r = new MyRunnable();
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<?> f = es.submit(r);
        Thread.sleep(200);
        f.cancel(true);
        es.shutdown();
    }
}
