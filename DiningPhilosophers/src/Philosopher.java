import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Philosopher implements Runnable {
    private final int number;
    private Chopstick firstChopstick;
    private Chopstick secondChopstick;
    private static ExecutorService es = Executors.newCachedThreadPool();

    public Philosopher(int number) {
        this.number = number;
    }

    public void assignChopstick(Chopstick leftChopstick, Chopstick rightChopstick) {
        this.firstChopstick = leftChopstick;
        this.secondChopstick = rightChopstick;
//        this.firstChopstick = leftChopstick.getNumber() > rightChopstick.getNumber() ? leftChopstick : rightChopstick;
//        this.secondChopstick = firstChopstick == leftChopstick ? rightChopstick : leftChopstick;
    }

    public void eat() {
        es.execute(this);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (isHungry()) {
                if (firstChopstick.take()) {
                    if (secondChopstick.take()) {
                        doEat();
                        secondChopstick.put();
                    }
                    firstChopstick.put();
                }
            } else {
                doThink();
            }
        }
    }

    private void doThink() {
        System.out.println("Philosopher " + (number+1) + ": Thinking...");
        try {
            Thread.sleep((new Random().nextInt(5) + 1) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isHungry() {
        return new Random().nextBoolean();
    }

    private void doEat() {
        System.out.println("Philosopher " + (number+1) + ": Eating...");
        ;
        try {
            Thread.sleep((new Random().nextInt(5) + 1) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
