import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This program demonstrates a deadlock which can happen if we do not make
 * the tasks separate from each other.
 * The task Task1 starts and submits Task2 to the same executor
 * However, the executor is a SingleThreadExecutor and therefore Task 2 never starts
 * and Task 1 never ends
 */
public class Main {

    private static ExecutorService es = Executors.newSingleThreadExecutor();

    static class Task2 implements Callable<String> {

        private final String name;

        public Task2(String name) {
            this.name = name;
        }

        @Override
        public String call() {
            log("Executing task " + name);
            return "Executing: name";
        }
    }

    static class Task1 implements Callable<String> {

        @Override
        public String call() throws Exception {
            log("Executing DoTasks");
            Future<String> task2Result = es.submit(new Task2("part 1"));
            return task2Result.get();
        }
    }

    public static void main(String[] args) {
        es.submit(new Task1());
    }

    private static void log(String message) {
        System.out.println(message);
    }
}