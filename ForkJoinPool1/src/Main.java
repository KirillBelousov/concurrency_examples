import java.util.concurrent.ForkJoinPool;

/**
 * Elementary ForkJoinPool example.
 * We recursively do an action, no result is returned.
 */
public class Main {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        fjp.invoke(new SendRequestsAction(600));
    }
}
