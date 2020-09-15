public class FactorialCalculatorImmutableState implements FactorialCalculator {

    private static class CachedValue {
        private final long oldArg ;
        private final long oldResult;

        CachedValue(long arg, long res) {
            oldArg = arg;
            oldResult = res;
        }

        long getCachedValue(int arg) {
            if(arg == oldArg) {
                return  oldResult;
            } else return -1;
        }
    }

    volatile CachedValue cachedValue = new CachedValue(0, 1);


    @Override
    public long calculateFactorial(int val)  {
        CachedValue cachedValue = this.cachedValue;
        long res = cachedValue.getCachedValue(val);
        if(res == -1) {
            res = doCalc(val);
            this.cachedValue = new CachedValue(val, res);
        }
        return res;
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
