import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Simple example of using Callable
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newSingleThreadExecutor();

        System.out.println("Lottery");

        Future<Set<Integer>> f = es.submit(new Lottery());

        System.out.println("Ожидаем результаты лотереи...");
        Set<Integer> results = f.get();

        System.out.println("Результаты лотереи " + results);

        es.shutdown();
    }
}
