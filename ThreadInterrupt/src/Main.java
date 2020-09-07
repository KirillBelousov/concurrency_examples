import java.util.concurrent.*;

// Thread interruption example. 
// In this example MyRunnable thread periodically queries Thread.isInterrupted()
// so she knows if some other thread requested her to interrupt
public class Main {

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Starting");
            for(long i=0; i<100000L; ++i) {
                if(Thread.interrupted()) {
                    System.out.println("Interrupted!");
                    break;
                } else {
                    System.out.println(i);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Thread t1 = new Thread(new MyRunnable());
        t1.start();
        Thread.sleep(10);
        t1.interrupt();
    }
}