import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple example of a deadlock
 */
public class Deadlock {

    private static ExecutorService es = Executors.newFixedThreadPool(2);

    static class Programmer {
        private final String name;


        public Programmer(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public synchronized void reviewCode(Programmer programmer) {
            System.out.format("%s: reviewing %s's code !%n",
                    this.name, programmer.getName());
            programmer.submitCode(this);
        }

        public synchronized void submitCode(Programmer programmer) {
            System.out.format("%s: submitting my code to %s for a review!%n", this.name, programmer.getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Programmer jack =  new Programmer("Vanya");
        Programmer jill = new Programmer("Sanya");

        es.submit(() -> jack.reviewCode(jill));

        es.submit(() -> jill.reviewCode(jack));

        es.shutdown();

        System.out.println("Project completed!");
    }
}