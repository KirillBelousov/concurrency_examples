import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PingPong {
    private final int MAX = 10;
    private volatile int counter = 0;
    private volatile boolean ponged = true;
    private static ExecutorService es = Executors.newFixedThreadPool(2);

    public synchronized void ping() {
        try {
            if (ponged) {
                counter++;
                System.out.println("Ping");
                ponged = false;
            }
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void pong() {
        if (!ponged) {
            counter++;
            System.out.println("Pong");
            ponged = true;
        }
        notifyAll();
    }

    public static void main(String... arg) {
        final PingPong pingpong = new PingPong();
        es.submit(() -> {
            while (pingpong.counter < pingpong.MAX)
                pingpong.ping();
        });
        es.submit(() -> {
            while (pingpong.counter < pingpong.MAX)
                pingpong.pong();
        });

        es.shutdown();
    }
}