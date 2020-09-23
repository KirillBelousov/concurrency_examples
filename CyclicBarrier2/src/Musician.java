import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Musician implements Runnable {
    private final String instrument;
    private final Concert concert;
    private static final Random rand = new Random();
    private static ExecutorService es = Executors.newFixedThreadPool(10);

    public Musician(String instrument, Concert concert) {
        this.instrument = instrument;
        this.concert = concert;
    }

    public static void shutdown() {
        es.shutdown();
    }

    @Override
    public void run() {
        System.out.println(instrument + " tuning...");
        try {
            Thread.sleep((rand.nextInt(5) + 1)*1000);
        } catch(InterruptedException x) {
            x.printStackTrace();
        }
        System.out.println(instrument + " tuning done, ready!");
        concert.tuningDone();
    }

    public void play() {
        es.execute(this);
    }
}
