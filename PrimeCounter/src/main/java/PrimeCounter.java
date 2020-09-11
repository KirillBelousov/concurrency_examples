public interface PrimeCounter {

    /**
     * Returns prime numbers count in [start, end] range
     * @param start
     * @param end
     * @return
     */
    int countPrimes(int start, int end) throws InterruptedException;
}
