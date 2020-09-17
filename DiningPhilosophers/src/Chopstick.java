import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    private final int number;
    private Lock lock = new ReentrantLock();

    public Chopstick(int number) {
        this.number = number;
    }

    public boolean take() {
        return lock.tryLock();
//        lock.lock();
//        return true;
    }

    public void put() {
        lock.unlock();
    }

    public int getNumber() {
        return number;
    }
}
