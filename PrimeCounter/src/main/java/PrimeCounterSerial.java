public class PrimeCounterSerial implements PrimeCounter {

    public int countPrimes(int start, int end) {
        int count = 0;
        for(int i=start; i<=end; ++i) {
            if(isPrime(i)) {
                ++count;
            }
        }
        return count;
    }

    private boolean isPrime(int val) {
        if(val == 1) return false;
        if(val == 2) return true;
        for(int i = 2; i<=val/2; ++i) {
            if(val%i == 0) return false;
        }
        return true;
    }
}
