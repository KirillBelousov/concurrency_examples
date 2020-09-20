import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PingPongWrong {
    private final int MAX = 10;
    private volatile int counter = 0;
    private volatile boolean ponged = true;

    private static ExecutorService es = Executors.newFixedThreadPool(2);

    public synchronized void ping() {
        counter++;
        System.out.println("Ping");
    }

    public synchronized void pong() {
        counter++;
        System.out.println("Pong");
    }

    public static void main(String... arg) {
        final PingPongWrong pingpong = new PingPongWrong();
        es.submit(() -> {
            while (pingpong.counter < pingpong.MAX) {
                pingpong.ping();
            }
        });
        es.submit(() -> {
            while (pingpong.counter < pingpong.MAX) {
                pingpong.pong();
            }
        });

        es.shutdown();
    }
}