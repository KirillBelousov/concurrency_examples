import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A simple example of CountDownLatch.
 * Several threads report their readiness to the main thread
 * and then await until all the threads are ready.
 * then all the threads proceed simultaneously.
 */
class MyTask extends Thread {
    private static Random rand = new Random();
    private final CountDownLatch latchReady;
    private static int count;
    private final CountDownLatch latchStart;
    private final CountDownLatch latchFinish;

    public MyTask(CountDownLatch latchReady, CountDownLatch latchStart, CountDownLatch latchFinish) {
        this.latchReady = latchReady;
        this.latchStart = latchStart;
        this.latchFinish = latchFinish;
        setName(String.valueOf(++count));
    }

    public void run() {
        try {
            Thread.sleep((rand.nextInt(3) + 1) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + getName() + " ready");
        latchReady.countDown();
        try {
            latchStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Thread " + getName() + " running");
            Thread.sleep((rand.nextInt(3) + 1) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + getName() + " finished");
        latchFinish.countDown();
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(3);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownLatch latch3 = new CountDownLatch(3);

        MyTask t1 = new MyTask(latch, latch2, latch3);
        MyTask t2 = new MyTask(latch, latch2, latch3);
        MyTask t3 = new MyTask(latch, latch2, latch3);

        System.out.println("Waiting for the threads to become ready");

        es.submit(t1);
        es.submit(t2);
        es.submit(t3);

        latch.await();

        System.out.println("Letting threads run");
        latch2.countDown();

        System.out.println("Waiting for the threads to finish");
        latch3.await();

        System.out.println("All threads finished");

        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }
}
