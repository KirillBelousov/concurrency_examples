import java.util.concurrent.ForkJoinPool;

/**
 * Elementary ForkJoinPool example.
 * We recursively do an action, returning a total result
 */
public class Main {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        WorkerTask workerTask = new WorkerTask(200);
        System.out.println(forkJoinPool.invoke(workerTask));


    }
}
