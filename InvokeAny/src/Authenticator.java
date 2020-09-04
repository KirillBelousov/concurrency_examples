import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Authenticator {

    private ExecutorService es = Executors.newFixedThreadPool(2);

    public boolean authenticate(String userName, String password) throws ExecutionException, InterruptedException {
        AuthTask auth1 = new NetworkAuth(userName, password);
        AuthTask auth2 = new LocalAuth(userName, password);
        List<AuthTask> tasks = new ArrayList<>();
        tasks.add(auth1);
        tasks.add(auth2);
        boolean result = es.invokeAny(tasks);
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        return result;
    }
}