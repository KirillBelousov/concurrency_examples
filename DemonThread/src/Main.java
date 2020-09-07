import java.util.concurrent.ExecutionException;

// This example shows that JVM kills all daemon threads
// when all user threads terminate
public class Main {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread running");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread completed");
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("Start");
        Thread t = new MyThread();
        t.setDaemon(true);
        t.start();
        System.out.println("Done");
    }
}