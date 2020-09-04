import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService es = Executors.newSingleThreadExecutor();

        System.out.println("Starting a task which throws an exception");

        es.submit(() ->
        {
            // The below statement will throw ArithmeticException
            // because we attempt to divide by zero.
            // However this exception will be never printed out
            // because we do not explicitly wait for the Future of this task
            int x = 1 / 0;
        });

        System.out.println("Exception was thrown but nothing has been printed!");

        System.out.println("Starting a task which throws an exception and waiting for the result");

        Future<Integer> f = es.submit(() ->
        {
            // The below statement will throw ArithmeticException
            // because we attempt to divide by zero.
            // However this exception will be never printed out
            // because we do not explicitly wait for the Future of this task
            int x = 1 / 0;
            return x;
        });

        try {
            f.get();
        } catch (ExecutionException e) {
            System.err.println("The exception is caught here!");
            e.printStackTrace();
        }

        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

    }
}