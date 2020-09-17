import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Actor implements Runnable {

    protected static final Object stage = new Object();

    private static ExecutorService es = Executors.newCachedThreadPool();

    public static void endPlay() {
        es.shutdown();
    }

    public void play() {
        es.execute(this);
    }

    @Override
    public void run() {
        doPlay();
    }

    public void say(String text) {
        System.out.println(text);
    }

    abstract protected void doPlay();
}
