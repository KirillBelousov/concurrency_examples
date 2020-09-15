public class Main {
    // Remove the volatile keyword and Thread1 (may) never stop
    private static volatile int count = 0;

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println("thread 1 run");
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
            System.out.println("thread 2 run");
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