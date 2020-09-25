// Without synchronization the 2 threads below do not have any happens-before relationship between them
// Therefore (0, 1), (1, 0), (1, 1) and even (0, 0) can be printed
public class Main {
    static int x = 0, y = 0;
    static int a = 0, b = 0;
    public static void main(String[] args) throws InterruptedException {

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                x = b;
                a = 1;
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                y = a;
                b = 1;
            }
        });

        one.start(); two.start();
        one.join(); two.join();

        System.out.println("x=" + x + ", y=" + y);
    }
}