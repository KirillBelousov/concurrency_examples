/**
 * A simple ExecutorService example
 * This programs emulates some delivery service
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Delivery d1 = new Delivery("Nevsky Prospect, 21");
        Delivery d2 = new Delivery("Engelsa St., 52");
        Delivery d3 = new Delivery("Fontanka Emb., 3");
        Delivery d4 = new Delivery("Simonova St., 66b");

        DeliveryService service = new DeliveryService();
        service.deliver(d1);
        service.deliver(d2);
        service.deliver(d3);
        service.deliver(d4);

        // We need to properly shutdown our service
        // so that all our delivery threads complete
        // and then we exit
        service.shutdown();

        System.out.println("All deliveries are completed");
    }
}