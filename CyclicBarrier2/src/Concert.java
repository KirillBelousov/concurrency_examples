import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Concert implements Runnable {

    private final CyclicBarrier cb;

    Concert(int bandSize) {
        cb = new CyclicBarrier(bandSize, this);
    }

    public void tuningDone() {
        try {
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("All musicians have tuned in. Now playing!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Concert is over");
        Musician.shutdown();

    }
}
