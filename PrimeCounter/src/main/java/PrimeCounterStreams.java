import java.util.stream.IntStream;

public class PrimeCounterStreams implements PrimeCounter {

    public int countPrimes(int start, int end) throws InterruptedException {
        return (int)IntStream.rangeClosed(start, end).filter(x -> isPrime(x)).count();
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
