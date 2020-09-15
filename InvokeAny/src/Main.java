import java.util.concurrent.ExecutionException;
// Demonstration of ExecutorService.invokeAny()
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Authenticator authenticator = new Authenticator();
        boolean result = authenticator.authenticate("Alexander", "12345");
        System.out.println("Authentication result: " + result);
    }
}
