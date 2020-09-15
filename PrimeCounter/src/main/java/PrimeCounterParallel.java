import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimeCounterParallel implements PrimeCounter {

    ExecutorService es = Executors.newFixedThreadPool(2);

    private int count;

    private class CountingTask implements Runnable {

        private final int start;
        private final int end;

        public CountingTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            for (int i = start; i <= end; ++i) {
                if (isPrime(i)) {
                    ++count;
                }
            }
        }
    }

    public int countPrimes(int start, int end) {

        es.submit(new CountingTask(start, (end + start) / 2));
        es.submit(new CountingTask((end + start) / 2 + 1, end));
        es.shutdown();
        try {
            es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    private boolean isPrime(int val) {
        if (val == 1) return false;
        if (val == 2) return true;
        for (int i = 2; i <= val / 2; ++i) {
            if (val % i == 0) return false;
        }
        return true;
    }

}
