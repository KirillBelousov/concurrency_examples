import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

// Demonstrates using ThreadFactory to make understandable thread names for debugging
public class Main {

    static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread running in " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread completed");
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("My custom thread for running my tasks");
                return t;
            }
        });
        es.execute(new MyTask());
        es.execute(new MyTask());
        es.execute(new MyTask());
        Thread.sleep(2000);
        es.shutdown();
    }
}