import java.util.ArrayList;
import java.util.List;

/**
 * Simple example of CyclicBarrier
 * Each musician in a band of musicians tunes in for some time
 * then signals that she's ready
 * As soon as everyone is ready the concert starts.
 */
public class Main {

    public static void main(String[] args) {

        int bandSize = 5;
        Concert concert = new Concert(5);
        List<Musician> band = new ArrayList<>(bandSize);
        band.add(new Musician("bass", concert));
        band.add(new Musician("guitar", concert));
        band.add(new Musician("drums", concert));
        band.add(new Musician("synths", concert));
        band.add(new Musician("singer", concert));

        for(Musician musician: band) {
            musician.play();
        }
    }

}
