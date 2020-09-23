import java.util.Random;
import java.util.concurrent.*;

class Statistician implements Callable<Void> {

    private static Random rand = new Random();
    private CyclicBarrier cb;
    private final int id;
    private int count;
    private static ExecutorService es = Executors.newCachedThreadPool();

    Statistician(int id) {
        this.id = id;
        count = rand.nextInt(1000) + 100;
    }

    public static void shutdown() {
        es.shutdownNow();
    }

    @Override
    public Void call() throws Exception {
        while(!Thread.currentThread().isInterrupted()) {
            log("Statistician " + id + " collecting statistics...");
            delay();
            count = count/2;
            log("Statistician " + id + " reported: " + count);
            cb.await();
        }
        return null;
    }

    private void delay() throws InterruptedException {
        Thread.sleep((rand.nextInt(3) + 1)*1000);
    }

    private void log(String msg) {
        System.out.println(msg);
    }

    public int getCount() {
        return count;
    }

    public void  collectStatistics(CyclicBarrier cb) {
        this.cb = cb;
        es.submit(this);
    }
}
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Statistician statistician1 = new Statistician(1);
        Statistician statistician2 = new Statistician(2);
        Statistician statistician3 = new Statistician(3);

        CyclicBarrier cb = new CyclicBarrier(3, () -> {
            int total  = statistician1.getCount() + statistician2.getCount() + statistician3.getCount();
            log("Total: " + total);
            if(total <= 0) {
                Statistician.shutdown();
            }
        });


        statistician1.collectStatistics(cb);
        statistician2.collectStatistics(cb);
        statistician3.collectStatistics(cb);
    }

    private static void log(String msg) {
        System.out.println(msg);
    }
}
