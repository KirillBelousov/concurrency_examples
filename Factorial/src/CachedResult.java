public class CachedResult {
    private final int arg;
    private final long res;

    public CachedResult(int val, long res) {
        this.arg = val;
        this.res = res;
    }

    public long getRes() {
        return res;
    }
}
