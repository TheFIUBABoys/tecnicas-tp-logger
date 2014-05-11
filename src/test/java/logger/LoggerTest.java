package logger;

import level.LogLevel;
import org.junit.Before;
import org.junit.Test;

public class LoggerTest {

    private Logger loggerInstance;
    private String permanentFilename = "Log.txt";
    private String temporaryFilename = "TempLog.txt";

    @Before
    public void setUp() throws Exception {
        loggerInstance = Logger.getLogger();
        loggerInstance.setConsoleOutput(true);
        loggerInstance.setMessageFormat(new LogFormat("%p - %m"));
        loggerInstance.addOutputFile(permanentFilename);
        loggerInstance.addOutputFile(temporaryFilename);
    }

    @Test
    public void testLogMessageInfo() throws Exception {
        loggerInstance.setLogLevel(LogLevel.LEVEL_INFO);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Debug Message%n", LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage("Info Message%n", LogLevel.LEVEL_INFO);
    }

    @Test
    public void testLogMessageOff() throws Exception {
        loggerInstance.setLogLevel(LogLevel.LEVEL_OFF);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Debug Message%n", LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage("Info Message%n", LogLevel.LEVEL_INFO);
    }

    @Test
    public void testLogMessageFatal() throws Exception {
        loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Debug Message%n", LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage("Warn Message%n", LogLevel.LEVEL_WARN);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);
    }

}