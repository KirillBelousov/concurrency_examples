/**
 * This example demonstrates (possible) visibility issues when we're not using
 * volatile
 * On my machine it never prints "Counter is greater than 5 now"
 * (although one might think it should)
 * Change the 'count' variable to be volatile and it does then print "Counter is greater than 5 now".
 */
public class Main {

    private static  int count = 0;

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            while (true) {
                if (count >= 5) {
                    System.out.println("Counter is greater than 5 now");
                    break;
                }
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            while (count < 10) {
                System.out.println("Counter is " + (count++) + " now");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}