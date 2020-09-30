import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * A simple example of a delyaed queue.
 * Carantined people leave the carantine
 * as soon as their carantine duration expires
 * Each of the people is assigned a different duration.
 */
class Carantined implements Delayed {

    private final String name;
    private final Date date;

    Carantined(Date releaseAt, String name) {
        this.name = name;
        this.date = releaseAt;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(date.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long diff = date.getTime() - ((Carantined) o).date.getTime();
        return (int) diff;

    }

    @Override
    public String toString() {
        return "Carantined{" +
                "releaseAt=" + date +
                ", name='" + name + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Carantined c1 = new Carantined(new Date(System.currentTimeMillis() + 1000), "Jack");
        Carantined c2 = new Carantined(new Date(System.currentTimeMillis() + 200), "John");
        Carantined c3 = new Carantined(new Date(System.currentTimeMillis() + 2000), "Jill");

        DelayQueue<Carantined> que = new DelayQueue<>();
        que.put(c1);
        que.put(c2);
        que.put(c3);

        for (int i = 0; i < 3; ++i) {
            Carantined c = que.take();
            System.out.println("Released " + c);
        }
    }
}
