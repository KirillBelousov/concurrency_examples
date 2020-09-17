public class Romeo2 extends Actor {
    @Override
    protected void doPlay() {
        synchronized (stage) {
            say("I love you, Juliette");
            stage.notify();
        }
    }
}
