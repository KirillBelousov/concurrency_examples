import java.util.concurrent.*;


public class Main {

    static class MyRunnable implements Runnable {

        private int i = 0;

        @Override
        public void run() {
            System.out.println("Starting");
            try {
                for (i = 0; i < Long.MAX_VALUE; ++i) {
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                    System.out.println("i=" + i);
                    Thread.sleep(30);
                }
            } catch(InterruptedException x) {
                cleanup();
            }
        }

        private void cleanup() {
            System.out.println("Terminated in the sleep last value=" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<?> f = es.submit(new MyRunnable());
        Thread.sleep(200);
        f.cancel(true);
    }
}