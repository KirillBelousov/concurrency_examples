import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class PingerTests {
    @Test
    public void testPing() throws IOException {
        Pinger pinger = new Pinger();
        String pingOutput = pinger.pingSite();

        assertTrue(pingOutput.contains("google.com"));
    }
}
