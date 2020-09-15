import java.util.concurrent.*;

// Demonstrates setting custom default exception handler for all threads
public class Main {

    static class MyTask implements Runnable {
        @Override
        public void run() {
            int x = 1/0;
        }
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService es = Executors.newFixedThreadPool(2);

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Default exception handler. Exception: " + e);
            }
        });

        try {
            es.execute(new MyTask());
            es.execute(() -> { throw new UnsupportedOperationException(); });
        } catch (Exception x) {
            System.out.println("Exception: " + x);
        }

        es.shutdown();

    }
}