import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * This example demonstrates using Callable interface to submit tasks which return some result
 * Here our Delivery task returns the price paid by the customer for the delivery
 * We submit the task for execution and then wait for the Future to get the task result;
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Delivery d1 = new Delivery("Nevsky Prospect, 21");

        DeliveryService service = new DeliveryService();
        Future<Integer> future = service.deliver(d1);

        int price = future.get();

        System.out.println("Customer paid: " + price + " roubles");

        // We need to properly shutdown our service
        // so that all our delivery threads complete
        // and then we exit
        service.shutdown();

        System.out.println("All deliveries are completed");    }
}
