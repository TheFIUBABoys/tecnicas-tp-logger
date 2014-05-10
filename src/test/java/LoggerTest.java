import level.LevelDebug;
import level.LevelError;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoggerTest {

    @Before
    public void setUp() throws Exception {
        Logger.setConsoleOutput(true);
        Logger.setLogLevel(new LevelDebug());
        Logger.setMessageFormat(new LogFormat("%p - %m"));
    }

    @Test
    public void testLogMessage() throws Exception {
        Logger.logMessage("Message", new LevelError());
    }
}