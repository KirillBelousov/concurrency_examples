import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("Factorial sample");

        FactorialCalculator factorialCalculator = new FactorialCalculatorBroken();
        //FactorialCalculator factorialCalculator = new FactorialCalculatorSynchronized();
        //FactorialCalculator factorialCalculator = new FactorialCalculatorBetterSynchronized();
        //FactorialCalculator factorialCalculator = new FactorialCalculatorImmutableState();

        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<Long> f1 = es.submit(new CalcFactorial(10, factorialCalculator));
        Thread.sleep(100);
        Future<Long> f2 = es.submit(new CalcFactorial(10, factorialCalculator));
        System.out.println("Factorial of 10: " + f1.get());
        System.out.println("Factorial of 10: " + f2.get());
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

    }

    private static class CalcFactorial implements Callable<Long> {
        private final FactorialCalculator factorialCalculator;
        private final int val;

        public CalcFactorial(int val, FactorialCalculator factorialCalculator) {
            this.val = val;
            this.factorialCalculator = factorialCalculator;
        }

        @Override
        public Long call() throws Exception {
            System.out.println("Calculating factorial in thread " + Thread.currentThread().getName());
            return factorialCalculator.calculateFactorial(val);
        }
    }
}
