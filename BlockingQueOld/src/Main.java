/**
 * An example of a blocking queue using wait/notify mechanism
 * There's no need for that since Java 5, use BlockingQueue instead
 */

class MyBlockingQue {
    private int[] vals = new int[10];
    private int indexPut, indexGet, count;

    public synchronized void put(int val) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        vals[indexPut] = val;
        if (++indexPut == 10) indexPut = 0;
        count++;
        notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        int val = vals[indexGet];
        if (++indexGet == 10) indexGet = 0;
        count--;
        notifyAll();
        return val;
    }

    private boolean isEmpty() {
        return count == 0;
    }

    private boolean isFull() {
        return count == 10;
    }
}

public class Main {
    public static void main(String[] args) {
        MyBlockingQue que = new MyBlockingQue();

        Thread t1 = new Thread() {
            int val;

            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        ++val;
                        que.put(val);
                        System.out.println("Put " + val);
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t2 = new Thread() {

            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        int val = que.get();
                        System.out.println("Got " + val);
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t1.start();
        t2.start();

    }
}
