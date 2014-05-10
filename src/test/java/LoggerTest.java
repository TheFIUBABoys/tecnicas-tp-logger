import level.LevelDebug;
import level.LevelError;
import level.LevelInfo;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

public class LoggerTest {

    @Before
    public void setUp() throws Exception {
        Logger.setConsoleOutput(true);
        Logger.setLogLevel(new LevelInfo());
        Logger.setMessageFormat(new LogFormat("%p - %m"));
        Logger.addOutputFile("Log.txt");
        Logger.addOutputFile("Log2.txt");
    }

    @Test
    public void testLogMessage() throws Exception {
        Logger.logMessage("Error Message%n", new LevelError());
        Logger.logMessage("Debug Message%n", new LevelDebug());
        Logger.logMessage("Info Message%n", new LevelInfo());
    }
}