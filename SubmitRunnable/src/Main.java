import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Demonstrates that we can submit Runnable to ExecutorService.submit
// and the execution will always return null
public class Main {

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("In MyRunnable");
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<?> result = es.submit(new MyRunnable());
        System.out.println("Result = " + result.get());
    }
}
