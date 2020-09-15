import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(2);

        FactorialProducer factorialProducer = new FactorialProducer();
        Future<Long> f1 = es.submit(new CalculationTask(10, factorialProducer));
        Future<Long> f2 = es.submit(new CalculationTask(10, factorialProducer));
        System.out.println(f1.get());
        System.out.println(f2.get());
        es.shutdown();
    }

    private static class CalculationTask implements Callable<Long> {
        private final int val;
        private final FactorialProducer producer;

        public CalculationTask(int val, FactorialProducer producer) {
            this.val = val;
            this.producer = producer;
        }

        @Override
        public Long call() throws InterruptedException {
            long res = producer.getFactorial(val);
            return res;
        }
    }
}
