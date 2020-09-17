public class Romeo3 extends Actor {
    private boolean hasSaid;

    @Override
    protected void doPlay() {
        synchronized (stage) {
            say("I love you, Juliette");
            hasSaid = true;
            stage.notify();
        }
    }

    public boolean hasSaid() {
        return hasSaid;
    }
}
