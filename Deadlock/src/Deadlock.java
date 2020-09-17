/**
 * A simple example of a deadlock
 */
public class Deadlock {
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

        Thread t1 = new Thread(() -> jack.reviewCode(jill));

        Thread t2 = new Thread(() -> jill.reviewCode(jack));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Project completed!");
    }
}