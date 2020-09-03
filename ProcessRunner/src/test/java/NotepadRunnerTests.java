import org.junit.Test;

import java.io.IOException;

public class NotepadRunnerTests {

    @Test
    public void testRunNotepad() throws IOException, InterruptedException {
        NotepadRunner notepadRunner = new NotepadRunner();
        notepadRunner.runAndDestroy();
    }

}
