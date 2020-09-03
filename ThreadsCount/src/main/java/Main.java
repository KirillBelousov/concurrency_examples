// JVM creates a thread to monitor Ctrl-Break for this simple console program
public class Main {

    public static void main(String[] args) {
        int activeCount = Thread.activeCount();
        System.out.println("Active threads count=" + activeCount);

        Thread[] threadsInfo = new Thread[activeCount];

        Thread.enumerate(threadsInfo);
        for(Thread ti : threadsInfo) {
            System.out.println("name=" + ti.getName());
            System.out.println("demon:" + (ti.isDaemon() ? " yes " : "no"));
        }
    }
}
