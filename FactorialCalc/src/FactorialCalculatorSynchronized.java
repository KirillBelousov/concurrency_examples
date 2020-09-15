public class FactorialCalculatorSynchronized implements FactorialCalculator {

    // This factorial calculator is ok because only one thread can enter the method
    // However the synchronization could have been better granulated
    private long oldArg ;
    private long oldResult;

    @Override
    public synchronized long calculateFactorial(int val)  {
        if(oldArg == val) {
            return oldResult;
        }
        oldArg = val;
        oldResult = doCalc(val);
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
