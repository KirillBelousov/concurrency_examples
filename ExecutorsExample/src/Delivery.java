import java.util.Random;

public class Delivery implements Runnable {
    private final String address;
    private final Random rand = new Random();

    public Delivery(String address) {
        this.address = address;
    }

    @Override
    public void run() {
        log("Delivery to " + address + " started");
        randomDelay();
        log("Delivery to " + address + " finished");
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
