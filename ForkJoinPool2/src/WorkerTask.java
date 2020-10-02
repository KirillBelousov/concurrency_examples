import java.util.concurrent.RecursiveTask;

public class WorkerTask extends RecursiveTask<Integer> {

    private int workAmount;

    public WorkerTask(int workAmount) {
        this.workAmount = workAmount;
    }

    @Override
    protected Integer compute() {

        if (workAmount > 100) {

            System.out.println("Parallel execution " + workAmount);

            WorkerTask workerTask1 = new WorkerTask(workAmount / 2);
            WorkerTask workerTask2 = new WorkerTask(workAmount / 2);

            workerTask1.fork();

            int total = 0;
            total = workerTask2.compute() + workerTask1.join();

            return total;

        } else {
            System.out.println("Sequential execution: " + workAmount);
            return workAmount;
        }
    }
}
