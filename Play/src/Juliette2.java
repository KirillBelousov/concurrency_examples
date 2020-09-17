public class Juliette2 extends Actor {
    @Override
    protected void doPlay() {
        say("Waiting for Romeo...");
        synchronized (stage) {
            try {
                stage.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            say("I love you, too, Romeo");
        }
    }
}
