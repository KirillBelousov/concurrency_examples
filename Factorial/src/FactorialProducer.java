import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class FactorialProducer {

    private AtomicInteger oldVal = new AtomicInteger();
    private AtomicLong oldResult = new AtomicLong();

    private final AtomicReference<CachedResult> cachedResult = new AtomicReference<>(new CachedResult(0, 1));

    public long getFactorial(int val) throws InterruptedException {
        CachedResult newResult = new CachedResult(val, calcFactorial(val));
        while(!cachedResult.compareAndSet(cachedResult.get(), newResult));
        return cachedResult.get().getRes();
    }

//    public long getFactorial(int val) throws InterruptedException {
//        System.out.println("Thread " + Thread.currentThread().getName() + " started calculating");
//        synchronized (this) {
//            if (val == oldVal.get()) {
//                System.out.println("Cached value returned in thread " + Thread.currentThread().getName());
//                return oldResult.get();
//            }
//        }
//        long res = calcFactorial(val);
//        synchronized (this) {
//            oldVal.set(val);
//            oldResult.set(res);
//        }
//        return oldResult.get();
//    }

    private long calcFactorial(int val) throws InterruptedException {
        System.out.println("Calculting in thread " + Thread.currentThread().getName());
        Thread.sleep(5000);
        long res = 1;
        for (int i = 1; i <= val; ++i) {
            res *= val;
        }
        return res;
    }
}
