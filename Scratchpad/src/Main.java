import java.util.List;
import java.util.concurrent.*;


public class Main {

    static class MyRunnable implements Runnable {

        private final String name;
        private final int duration;

        public MyRunnable(String name, int duration) {
            this.name = name;
            this.duration = duration;
        }

        @Override
        public void run() {
            System.out.println("Starting task " + name);
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed task " + name);
        }

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(new MyRunnable("Task 1", 5000));
        es.submit(new MyRunnable("Task 2", 1000));
        List<Runnable> tasks = es.shutdownNow();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        System.out.println("Done. Tasks not executed: " + tasks.size());
    }
}