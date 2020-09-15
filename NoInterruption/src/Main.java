/**
 * This example demonstrates that it is a responsibility of a thread
 * to correctly handle an interrption request.
 * The main thread in this example requests another thread to interrupt.
 * However, that other thread does not handle the interrupt request in any way
 * and therefore never interupts (and the program hangs)
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            // This thread doesn't handle interruption requests at all
            // Don't do that in your programs
            int i = 0;
            while (true) {
                System.out.println("i=" + (i++));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        });

        t.start();
        Thread.sleep(1000);

        // Request thread t to interrupt
        // (but it never will because it ignores our request)
        System.out.println("Requesting thread to interrupt");
        t.interrupt();

    }
}
