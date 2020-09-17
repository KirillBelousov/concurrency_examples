/**
 * A simple example of using ThreadLocal
 * The class BrokenStopWatch does not correctly reports threads start time
 * because its fields are simultaneously by several threads
 * Using ThreadLocal we turn our StopWtach into a per-thread singleton
 * and the time gets reported correctly
 */
public class Main {

//    private static StopWatch stopwatch = new BrokenStopWatch();
      private static StopWatch stopwatch = new GoodStopWatch();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            stopwatch.start();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stopwatch.end();
            System.out.println("Thread " + Thread.currentThread().getName() + " started at: " + stopwatch.getStarted() + " ended at: " + stopwatch.getEnded());
        };

        Thread t1 = new Thread(r);
        t1.start();
        Thread.sleep(2000);
        Thread t2 = new Thread(r);
        t2.start();
    }
}