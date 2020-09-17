import java.util.Date;

public class GoodStopWatch implements StopWatch {

    private ThreadLocal<Long> started = new ThreadLocal<>();
    private ThreadLocal<Long> ended = new ThreadLocal<>();

    @Override
    public String getStarted() {
        return new Date(started.get()).toString();
    }

    @Override
    public String getEnded() {
        return new Date(ended.get()).toString();
    }


    @Override
    public void start() {
        started.set(System.currentTimeMillis());
    }

    @Override
    public void end() { ended.set(System.currentTimeMillis()); }
}