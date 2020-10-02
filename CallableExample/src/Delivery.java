import java.util.Random;
import java.util.concurrent.Callable;

public class Delivery implements Callable<Integer> {
    private final String address;
    private final Random rand = new Random();

    public Delivery(String address) {
        this.address = address;
    }

    @Override
    public Integer call() {
        log("Delivery to " + address + " started");
        int price = calculatePrice();
        log("Delivery to " + address + " finished");
        return price;
    }

    private int calculatePrice() {
        randomDelay();
        return rand.nextInt(1000);
    }

    private void randomDelay() {
        try {
            Thread.sleep((rand.nextInt(5) + 1)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void log(String message) {
        System.out.println(message);
    }
}
