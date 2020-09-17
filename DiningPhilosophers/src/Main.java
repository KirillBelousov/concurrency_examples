import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int NUM_PHILOSOPHERS = 10;

        List<Chopstick> chopsticks = new ArrayList<>();
        for (int i = 0; i < NUM_PHILOSOPHERS; ++i) {
            chopsticks.add(new Chopstick(i));
        }

        List<Philosopher> philosophers = new ArrayList<>();
        for (int i = 0; i < NUM_PHILOSOPHERS; ++i) {
            Philosopher philosopher = new Philosopher(i);
            philosopher.assignChopstick(chopsticks.get(i), chopsticks.get((i + 1) % NUM_PHILOSOPHERS));
            philosophers.add(philosopher);
        }

        for (Philosopher philosopher : philosophers) {
            philosopher.eat();
        }

    }
}
