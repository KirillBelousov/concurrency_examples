import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Calling Future.cancel() to interrupt a task
public class Main {

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Starting");
            for(long i=0; i<100000L; ++i) {
                if(Thread.interrupted()) {
                    System.out.println("Interrupted! last number: " + i);
                    break;
                }
                System.out.println(i);
            }
            System.out.println("Done");
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<?> f = es.submit(new MyRunnable());
        Thread.sleep(20);
        f.cancel(true);
    }
}