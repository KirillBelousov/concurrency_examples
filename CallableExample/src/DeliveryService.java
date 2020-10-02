import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * This class represents our delivery service
 * By changing the ExecutorService we can change our execution policy
 * (the way we deliver the orders change)
 */
public class DeliveryService {

    /**
     * Uncomment other executors instead and see how the executio policy changes.
     * E.g. with a newSingleThreadExecutor all the deliveries are executed sequentially
     * with a newCachedThreadPool all the deliveries are executed in parallel
     */
    ExecutorService es = Executors.newFixedThreadPool(2);
    //ExecutorService es = Executors.newFixedThreadPool(4);
    //ExecutorService es = Executors.newSingleThreadExecutor();
    //ExecutorService es = Executors.newCachedThreadPool();

    /**
     * Execute a single delivery task
     * @param delivery task to execute
     */
    public Future<Integer> deliver(Delivery delivery) {
        return es.submit(delivery);
    }

    /**
     * Gracefully shuts down our delivery service
     * waiting for all ther task to complete
     * @throws InterruptedException
     */
    public void shutdown() throws InterruptedException {
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }
}
