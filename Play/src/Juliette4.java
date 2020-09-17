public class Juliette4 extends Actor {

    private final Romeo3 romeo;

    Juliette4(Romeo3 romeo) {
        this.romeo = romeo;
    }

    @Override
    protected void doPlay() {
        say("Waiting for Romeo...");
        synchronized (stage) {
            while(!romeo.hasSaid()) {
                try {
                    stage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            say("I love you, too, Romeo");
        }
    }
}
