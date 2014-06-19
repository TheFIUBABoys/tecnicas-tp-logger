package logger;

import logger.filters.custom.UserFilterImpl;
import logger.level.LogLevel;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class LoggerImplTest {

    private Logger loggerInstance;
    private ByteArrayOutputStream baos;

    @Before
    public void setUp() {
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @Test
    public void getLogger() throws Exception {
        loggerInstance = LoggerImpl.getLogger();
        loggerInstance.setConsoleOutput(true);
        loggerInstance.setLogLevel(LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage("LOG", LogLevel.LEVEL_FATAL);

        assertEquals("FATAL - LOG", baos.toString());
    }

    @Test
    public void getLogger1() throws Exception {
        // This one logs.
        loggerInstance = LoggerImpl.getLogger("logger1");
        loggerInstance.setConsoleOutput(true);
        loggerInstance.setLogLevel(LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage("LOG", LogLevel.LEVEL_FATAL);

        // This one does not log.
        loggerInstance = LoggerImpl.getLogger("logger2");
        loggerInstance.setConsoleOutput(true);
        loggerInstance.setLogLevel(LogLevel.LEVEL_OFF);
        loggerInstance.logMessage("LOG", LogLevel.LEVEL_FATAL);

        assertEquals("FATAL - LOG", baos.toString());
    }

    @Test
    public void throwableApi() {
        loggerInstance = LoggerImpl.getLogger("logger1");
        loggerInstance.setLogLevel(LogLevel.LEVEL_INFO);
        loggerInstance.setConsoleOutput(true);
        String error = "Error Message";
        loggerInstance.logMessage(error, LogLevel.LEVEL_ERROR, new Throwable("Testing", new Throwable("Cause")));
        assertEquals("ERROR - Error Message. Exception: Testing. Due to: java.lang.Throwable: Cause", baos.toString());
    }

    @Test
    public void getLogLevel() {
        loggerInstance = LoggerImpl.getLogger("logger1");
        loggerInstance.setLogLevel(LogLevel.LEVEL_INFO);
        assertEquals(LogLevel.LEVEL_INFO, loggerInstance.getLogLevel());
    }

    @Test
    public void filter() {
        loggerInstance = LoggerImpl.getLogger("logger1");
        loggerInstance.setLogLevel(LogLevel.LEVEL_TRACE);
        loggerInstance.addFilter(new UserFilterImpl());
        loggerInstance.logMessage("DONT LOG", LogLevel.LEVEL_TRACE);
        assertEquals("", baos.toString());
        loggerInstance.logMessage("LOG", LogLevel.LEVEL_ERROR);
        assertEquals("ERROR - LOG", baos.toString());
    }

}