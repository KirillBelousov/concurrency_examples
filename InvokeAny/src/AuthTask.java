import java.util.concurrent.Callable;

public abstract class AuthTask implements Callable<Boolean> {
    private final String userName;
    private final String password;

    public AuthTask(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Boolean call() throws Exception {
        return authenticate();
    }

    protected abstract Boolean authenticate() throws InterruptedException;
}