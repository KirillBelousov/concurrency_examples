public class FactorialCalculatorBetterSynchronized implements FactorialCalculator {

    // This factorial calculator is ok because we synchronize access to state as needed
    private long oldArg ;
    private long oldResult;

    @Override
    public  long calculateFactorial(int val)  {
        synchronized (this) {
            if (oldArg == val) {
                return oldResult;
            }
        }
        long res = doCalc(val);
        synchronized (this) {
            oldArg = val;
            oldResult = res;
        }
        return oldResult;
    }

    private long doCalc(int val)  {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(val == 0) return 1;
        long res = 1;
        for(int i=1; i<=val; ++i) {
            res *= i;
        }
        return res;
    }
}
