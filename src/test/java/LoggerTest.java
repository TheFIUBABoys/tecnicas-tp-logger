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
        Logger.setMessageFormat(new LogFormat("%p - %m"));
        Logger.addOutputFile("Log.txt");
        Logger.addOutputFile("Log2.txt");
    }

    @Test
    public void testLogMessageInfo() throws Exception {
        Logger.setLogLevel(Logger.LEVEL_INFO);
        Logger.logMessage("Error Message%n", Logger.LEVEL_ERROR);
        Logger.logMessage("Debug Message%n", Logger.LEVEL_DEBUG);
        Logger.logMessage("Info Message%n", Logger.LEVEL_INFO);
    }

    @Test
    public void testLogMessageOff() throws Exception {
        Logger.setLogLevel(Logger.LEVEL_OFF);
        Logger.logMessage("Error Message%n", Logger.LEVEL_ERROR);
        Logger.logMessage("Debug Message%n", Logger.LEVEL_DEBUG);
        Logger.logMessage("Info Message%n", Logger.LEVEL_INFO);
    }

    @Test
    public void testLogMessageFatal() throws Exception {
        Logger.setLogLevel(Logger.LEVEL_FATAL);
        Logger.logMessage("Error Message%n", Logger.LEVEL_ERROR);
        Logger.logMessage("Debug Message%n", Logger.LEVEL_DEBUG);
        Logger.logMessage("Warn Message%n", Logger.LEVEL_WARN);
        Logger.logMessage("Fatal Message%n", Logger.LEVEL_FATAL);
    }
}