/**
 * A simple example of a livelock
 */
public class Livelock {
    static class Computer {
        private Programmer owner;

        public Computer(Programmer d) {
            owner = d;
        }

        public synchronized void setOwner(Programmer d) {
            owner = d;
        }

        public synchronized void use() {
            System.out.printf("%s is coding!", owner.name);
        }
    }

    static class Programmer {
        private String name;
        private boolean wantsToCode;

        public Programmer(String n) {
            name = n;
            wantsToCode = true;
        }

        public String getName() {
            return name;
        }

        public void code(Computer computer, Programmer programmer) {
            while (wantsToCode) {
                if (computer.owner != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        continue;
                    }
                    continue;
                }

                if (programmer.wantsToCode) {
                    System.out.printf("%s:  %s has more important tasks to code%n", name, programmer.getName());
                    computer.setOwner(programmer);
                    continue;
                }

                computer.use();
                wantsToCode = false;
                System.out.printf("%s: I have completed my coding: %n", name);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Programmer programmer1 = new Programmer("Jack");
        Programmer programmer2 = new Programmer("Jill");

        final Computer s = new Computer(programmer1);

        Thread t1 = new Thread(() -> programmer1.code(s, programmer2));
        Thread t2 = new Thread(() -> programmer2.code(s, programmer1));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Project completed!");

    }
}