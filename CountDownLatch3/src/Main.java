import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * An example of using CountDownLatch for simple testing
 * of asynchronous callbacks
 */

class AsyncEngine implements Runnable {
    private Callback callback;
    private static ExecutorService es = Executors.newFixedThreadPool(1);

    void start() {
        es.execute(this);
    }

    void setCallback(AsyncEngine.Callback callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.onSuccess();
    }

    public void stop() {
        es.shutdown();
    }

    public interface Callback {
        void onSuccess();
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        AsyncEngine engine = new AsyncEngine();
        engine.setCallback(new AsyncEngine.Callback() {
            @Override
            public void onSuccess() {
                System.out.println("Success!");
                latch.countDown();
            }
        });
        engine.start();
        boolean res = latch.await(10000, TimeUnit.MILLISECONDS);
        System.out.println("Test " + (res ? "Passed" : "Failed"));
        engine.stop();
    }
}
