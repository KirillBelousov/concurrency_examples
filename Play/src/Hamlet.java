public class Hamlet extends Actor {
    @Override
    protected void doPlay() {
        synchronized (stage) {
            say("To be or not to be");
            stage.notify();
        }
    }
}
