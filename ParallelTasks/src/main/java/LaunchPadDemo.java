import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Demonstrates running asynchronous tasks
public class LaunchPadDemo {

    static class SpaceshipLaunch implements Runnable {

        @Override
        public void run() {
            System.out.println("Подготовка к старту");
            for(int i=0; i< 10; ++i) {
                System.out.println(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Полет нормальный");
        }
    }

    public static void main(String[] args) {
        parallelLaunchWithFixedThreadExecutor(2,3);
        // serialLaunchWithExecutorInAnotherThread(2);
        // parallelLaunchWithExecutor(2);
        // parallelLaunch(2);
        // serialLaunch(2);
    }

    private static void parallelLaunchWithFixedThreadExecutor(int shipsCount) {
        int launchPadsAvailable = Runtime.getRuntime().availableProcessors();
        System.out.println("Processors available=" + launchPadsAvailable);
        parallelLaunchWithFixedThreadExecutor(launchPadsAvailable, shipsCount);
    }

    private static void parallelLaunchWithFixedThreadExecutor(int launchPadsAvailable, int shipsCount) {
        ExecutorService es = Executors.newFixedThreadPool(launchPadsAvailable);

        SpaceshipLaunch spaceshipLaunch = new SpaceshipLaunch();
        System.out.println("Main process");
        for(int i=0; i<shipsCount; ++i) {
            es.execute(spaceshipLaunch);
        }
        es.shutdown();
        System.out.println("Main process ended");
    }

    private static void serialLaunchWithExecutorInAnotherThread(int shipsCount) {

        ExecutorService es = Executors.newSingleThreadExecutor();

        SpaceshipLaunch spaceshipLaunch = new SpaceshipLaunch();
        System.out.println("Main process");
        for(int i=0; i<shipsCount; ++i) {
            es.execute(spaceshipLaunch);
        }
        es.shutdown();
        System.out.println("Main process ended");
    }

    private static void parallelLaunchWithExecutor(int shipsCount) {

        ExecutorService es = Executors.newCachedThreadPool();

        SpaceshipLaunch spaceshipLaunch = new SpaceshipLaunch();
        System.out.println("Main process");
        for(int i=0; i<shipsCount; ++i) {
            es.execute(spaceshipLaunch);
        }
        es.shutdown();
        System.out.println("Main process ended");
    }

    private static void parallelLaunch(int shipsCount) {
        SpaceshipLaunch spaceshipLaunch = new SpaceshipLaunch();
        System.out.println("Main process");
        for(int i=0; i<shipsCount; ++i) {
            new Thread(spaceshipLaunch).start();
        }
        System.out.println("Main process ended");
    }

    private static void serialLaunch() {
        SpaceshipLaunch spaceshipLaunch = new SpaceshipLaunch();
        System.out.println("Main process");
        spaceshipLaunch.run();
        System.out.println("Main process ended");
    }


}
