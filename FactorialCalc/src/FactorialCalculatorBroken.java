public class FactorialCalculatorBroken implements FactorialCalculator {

    // This factorial calculator is broken because it doesn't guard oldArg and oldResult together
    private long oldArg ;
    private long oldResult;

    @Override
    public long calculateFactorial(int val)  {
        if(oldArg == val) {
            return oldResult;
        } else {
            oldArg = val;
            oldResult = doCalc(val);
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
