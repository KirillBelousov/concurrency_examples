public class FactorialCalculator {

    private long oldArg = -1;
    private long oldResult;

    public long calculateFactorial(int val) throws InterruptedException {
        if(val == oldArg) {
            System.out.println("Return cached value");
            return oldResult;
        } else {
            System.out.println("Return new value");
            oldArg = val;
            try {
                oldResult = doCalc(val);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return oldResult;
        }
    }

    private long doCalc(int val) throws InterruptedException {
        Thread.sleep(5000);
        if(val == 0) return 1;
        long res = 1;
        for(int i=1; i<=val; ++i) {
            res *= i;
        }
        return res;
    }
}
