import java.util.concurrent.atomic.AtomicInteger;

public class CounterImpl {

    private AtomicInteger count = new AtomicInteger();

    public void increment() {
        count.getAndIncrement();
    }
}
