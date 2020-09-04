import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

public class Lottery implements Callable<Set<Integer>> {

    private Random rand = new Random();

    @Override
    public Set<Integer> call() throws Exception {

        rand.setSeed(System.currentTimeMillis());

        Set<Integer> results = new HashSet<>();
        while(true) {
            System.out.println("Вращаем барабан...");
            delay();
            int val = rand.nextInt(36) + 1;
            if(results.contains(val)) continue;
            System.out.println("Выпадает шар " + val);
            results.add(val);
            if(results.size() == 5) break;
        }
        return results;
    }

    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
