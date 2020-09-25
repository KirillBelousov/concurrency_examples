import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Simple example of using Semaphore
 * Users often download some popular image from some server
 * Therefore the server restricts a number of concurrent download requests.
 * (no real images though, this is a simple console application)
 */
class Downloader {

    private Semaphore sema = new Semaphore(3);

    public void downloadPopularImage() throws InterruptedException {
        sema.acquire();
        System.out.println("Downloading...");
        Thread.sleep(3000);
        System.out.println("Downloaded..");
        sema.release();
    }
}

public class Main {

    private static Downloader downloader = new Downloader();
    private static ExecutorService es = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        for (int i = 0; i < 21; ++i) {
            es.submit(() -> {
                try {
                    downloader.downloadPopularImage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        es.shutdown();
    }
}
