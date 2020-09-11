import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrimeCounterTestsSerial {

    @Test
    public void countPrimes1to10Serial() throws InterruptedException {
        PrimeCounter counter = new PrimeCounterSerial();
        int result = counter.countPrimes(1, 10);
        assertEquals(4, result);
    }

    @Test
    public void countPrimes1to10000Serial() throws InterruptedException {
        PrimeCounter counter = new PrimeCounterSerial();
        int result = counter.countPrimes(1, 10000);
        assertEquals(1229, result);
    }
}
