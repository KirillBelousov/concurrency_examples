import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This example demonstrates how one can use CyclicBarrier
 * in a way similar to how CountDownLatch works
 */
public class Main {

    private static CyclicBarrier barrier = new CyclicBarrier(4);

    static class Service implements Runnable {
        private final String name;
        private final int delay;

        Service(String name, int delay) {
            this.name = name;
            this.delay = delay;
        }

        @Override
        public void run() {
            System.out.println("Initializing service: " + name);
            try {
                Thread.sleep(delay * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Service initialized: " + name);
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(3);
        Service s1 = new Service("Network", 5);
        Service s2 = new Service("Disk", 3);
        Service s3 = new Service("Fingerprints", 2);

        System.out.println("Waiting for services to initialize");
        es.execute(s1);
        es.execute(s2);
        es.execute(s3);
        barrier.await();
        System.out.println("Services initialized, booting up");
        es.shutdown();
    }
}
