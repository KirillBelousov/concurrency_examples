public class Juliette3 extends Actor {

    private final Romeo3 romeo;

    Juliette3(Romeo3 romeo) {
        this.romeo = romeo;
    }

    @Override
    protected void doPlay() {
        say("Waiting for Romeo...");
        synchronized (stage) {
            if(!romeo.hasSaid()) {
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
