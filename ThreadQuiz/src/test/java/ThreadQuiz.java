import org.junit.Test;

// Simple threads quiz
public class ThreadQuiz {

    @Test
    public void quiz1() throws InterruptedException {

        // Choose correct answer:
        //  foo bar
        //  foo
        //  MyThread foo
        //  None of these.
        //  MyThread bar

        class MyThread extends Thread {
            MyThread() {
                System.out.print(" MyThread");
            }

            public void run() {
                System.out.print(" bar");
            }

            public void run(String s) {
                System.out.println(" baz");
            }
        }

        Thread t = new MyThread() {
            public void run() {
                System.out.println(" foo");
            }
        };
        t.start();
        t.join();
    }

    @Test
    public void quiz2() throws InterruptedException {

        // Choose correct answer:
        // Compilation error on line a
        // Compilation error on line b
        // 1..2..
        // 1..2..3
        // None of these
        class MyThread extends Thread {
            public void run() {
                for (int i = 1; i < 3; ++i) {
                    System.out.print(i + "..");
                }
            }
        }

        Thread t = new MyThread(); // a
        t.run(); // b
        t.join();

    }

    @Test
    public void quiz3() throws InterruptedException {

        // Choose correct answer:
        // Throws exception at runtime.
        // Prints "Inside Thread Inside Thread".
        // Prints "Inside Thread Inside Runnable".
        // None of these.
        // Does not compile.

        class MyThread extends Thread {
            MyThread() {
            }

            MyThread(Runnable r) {
                super(r);
            }

            public void run() {
                System.out.print("Inside Thread ");
            }
        }

        class RunnableDemo implements Runnable {
            public void run() {
                System.out.print(" Inside Runnable");
            }
        }

        Thread t1 = new MyThread();
        t1.start();
        Thread t2 = new MyThread(new RunnableDemo());
        t2.start();

        t1.join();
        t2.join();

    }

    @Test
    public void quiz4() throws InterruptedException {

        // Choose correct answer:
        // Compiler error: there is no start() method
        // Will print in this order: AB CD AB CD
        // Will print in this order: ABCD ABCD
        // Will print ABCD but can't predict the order
        // None of these

        class ThreadTest extends Thread
        {
            public void run()
            {
                for(int i = 0; i < 3; i++)
                {
                    System.out.println("A");
                    System.out.println("B");
                }
            }
        }
        class ThreadDemo extends Thread
        {
            public void run()
            {
                for(int i = 0; i < 3; i++)
                {
                    System.out.println("C");
                    System.out.println("D");
                }
            }
        }

        ThreadTest t1 = new ThreadTest();
        ThreadDemo t2 = new ThreadDemo();
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
