import java.util.Date;

public class BrokenStopWatch implements StopWatch {

    private long started;
    private long ended;

    @Override
    public String getStarted() {
        return new Date(started).toString();
    }

    @Override
    public String getEnded() {
        return new Date(ended).toString();
    }


    @Override
    public void start() {
        started =  System.currentTimeMillis();
    }

    @Override
    public void end() {
        ended =  System.currentTimeMillis();
    }
}