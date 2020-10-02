import java.util.concurrent.RecursiveAction;

public class SendRequestsAction extends RecursiveAction {

    private final int requestsCount;

    SendRequestsAction(int requestsCount) {
        this.requestsCount = requestsCount;
    }

    @Override
    protected void compute() {
        if (requestsCount < 100) {
            System.out.println("Doing all now " + requestsCount);
        } else {
            System.out.println("Doing recursively " + requestsCount);
            SendRequestsAction a1 = new SendRequestsAction(requestsCount / 2);
            SendRequestsAction a2 = new SendRequestsAction(requestsCount / 2);
            a1.fork();
            a2.fork();
//          invokeAll(a1, a2); // you can use that instead of fork
        }
    }
}
