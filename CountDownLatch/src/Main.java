import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This example demonstrates CountDownLatch
 * The system has 3 services and does not boot up until
 * all the 3 services are initialized
 */
public class Main {

    private static CountDownLatch latch = new CountDownLatch(3);

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
            latch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(3);
        Service s1 = new Service("Network", 5);
        Service s2 = new Service("Disk", 3);
        Service s3 = new Service("Fingerprints", 2);

        System.out.println("Waiting for services to initialize");
        es.execute(s1);
        es.execute(s2);
        es.execute(s3);
        latch.await();
        System.out.println("Services initialized, booting up");
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }

}
