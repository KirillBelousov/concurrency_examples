import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrimeCounterTestsParallel {

    @Test
    public void countPrimes1to10Parallel() throws InterruptedException {
        PrimeCounter counter = new PrimeCounterParallel();
        int result = counter.countPrimes(1, 10);
        assertEquals(4, result);
    }

    @Test
    public void countPrimes1to10000Parallel() throws InterruptedException {
        PrimeCounter counter = new PrimeCounterParallel();
        int result = counter.countPrimes(1, 10000);
        assertEquals(1229, result);
    }
}
