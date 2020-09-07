import java.util.concurrent.*;

// Usage of exception handlers to catch exceptions in threads, and Thread.setName for debugging
public class Main {

    static class MyTask implements Runnable {
        @Override
        public void run() {
            int x = 1 / 0;
        }
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService es = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("My exceptional thread");
                t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println("Exception occured thread: " + t.getName() + " : exception " + e);
                    }
                });
                return t;
            }
        });
        try {
            es.execute(new MyTask());
        } catch (Exception x) {
            System.out.println("Exception: " + x); // We will never be here as the excetion occurs in another thread
        }

        es.shutdown();

    }
}