import org.junit.Before;
import org.junit.Test;



public class LoggerTest {

    private Logger loggerInstance;

    @Before
    public void setUp() throws Exception {
        loggerInstance = Logger.getLogger();
        loggerInstance.setConsoleOutput(true);
        loggerInstance.setMessageFormat(new LogFormat("%p - %m"));
        loggerInstance.addOutputFile("Log.txt");
        loggerInstance.addOutputFile("Log2.txt");
    }

    @Test
    public void testLogMessageInfo() throws Exception {
        loggerInstance.setLogLevel(Logger.LEVEL_INFO);
        loggerInstance.logMessage("Error Message%n", Logger.LEVEL_ERROR);
        loggerInstance.logMessage("Debug Message%n", Logger.LEVEL_DEBUG);
        loggerInstance.logMessage("Info Message%n", Logger.LEVEL_INFO);
    }

    @Test
    public void testLogMessageOff() throws Exception {
        loggerInstance.setLogLevel(Logger.LEVEL_OFF);
        loggerInstance.logMessage("Error Message%n", Logger.LEVEL_ERROR);
        loggerInstance.logMessage("Debug Message%n", Logger.LEVEL_DEBUG);
        loggerInstance.logMessage("Info Message%n", Logger.LEVEL_INFO);
    }

    @Test
    public void testLogMessageFatal() throws Exception {
        loggerInstance.setLogLevel(Logger.LEVEL_FATAL);
        loggerInstance.logMessage("Error Message%n", Logger.LEVEL_ERROR);
        loggerInstance.logMessage("Debug Message%n", Logger.LEVEL_DEBUG);
        loggerInstance.logMessage("Warn Message%n", Logger.LEVEL_WARN);
        loggerInstance.logMessage("Fatal Message%n", Logger.LEVEL_FATAL);
    }
}