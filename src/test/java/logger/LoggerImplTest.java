package logger;

import logger.level.LogLevel;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

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
    public void testGetLogger() throws Exception {
        loggerInstance = LoggerImpl.getLogger();
        loggerInstance.setConsoleOutput(true);
        loggerInstance.setLogLevel(LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage("LOG", LogLevel.LEVEL_FATAL);

        assertEquals("FATAL - LOG", baos.toString());
    }

    @Test
    public void testGetLogger1() throws Exception {
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
}