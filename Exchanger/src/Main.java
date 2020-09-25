import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple example of using Exchanger
 * Two kids are playing with their toys, then exchange the toys
 * Each kid has to wait until the other has completed the playing before exchanging
 */

enum Toy {
    BEAR,
    DOLL
}

class Child implements Runnable {

    private final String name;
    private final Exchanger<Toy> ex;
    private final int delay;
    private Toy toy;

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", toy=" + toy +
                '}';
    }

    Child(String name, Toy toy, Exchanger<Toy> ex, int delay) {
        this.name = name;
        this.toy = toy;
        this.ex = ex;
        this.delay = delay;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println(name + " plays with " + toy);
            try {
                Thread.sleep(delay * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(name + " done playing");
                toy = ex.exchange(toy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        Exchanger<Toy> ex = new Exchanger<>();
        es.execute(new Child("Dima", Toy.BEAR, ex, 5));
        es.execute(new Child("Lena", Toy.DOLL, ex, 1));

        Thread.sleep(10000);
        es.shutdown();
    }
}
