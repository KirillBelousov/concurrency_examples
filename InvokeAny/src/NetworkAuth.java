public class NetworkAuth extends AuthTask {
    public NetworkAuth(String userName, String password) {
        super(userName, password);
    }

    @Override
    protected Boolean authenticate() {
        boolean result = false;
        System.out.println("Authenticating by network...");
        try {
            Thread.sleep(5000);
            result = true;
        } catch(InterruptedException e) {
            System.out.println("Network authentication cancelled!");
            Thread.currentThread().interrupt();
        }
        if(result) {
            System.out.println("Authenticated by network!");
        }
        return result;
    }
}