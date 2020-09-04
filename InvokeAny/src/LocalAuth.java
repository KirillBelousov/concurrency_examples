public class LocalAuth extends AuthTask {
    public LocalAuth(String userName, String password) {
        super(userName, password);
    }

    @Override
    protected Boolean authenticate() throws InterruptedException {
        System.out.println("Authenticating locally...");
        Thread.sleep(1000);
        System.out.println("Authenticated locally!");
        return true;
    }
}