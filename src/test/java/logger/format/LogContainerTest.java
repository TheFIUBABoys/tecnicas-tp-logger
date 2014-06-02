package logger.format;

import com.google.gson.Gson;
import logger.level.LogLevel;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class LogContainerTest {

    private LogContainer logContainer;

    @Before
    public void setUp() throws Exception {
        logContainer = new LogContainerImpl();
    }

    @Test
    public void testSetDate() throws Exception {
        Date dateSet = new Date();
        logContainer.setDate(dateSet);
        assertEquals(dateSet.toString(), logContainer.getDate());
    }

    @Test
    public void testSetLoggerName() throws Exception {
        String loggerName = "logger";
        logContainer.setLoggerName(loggerName);
        assertEquals(loggerName, logContainer.getLoggerName());
    }

    @Test
    public void testSetLogLevel() throws Exception {
        LogLevel level = LogLevel.LEVEL_DEBUG;
        logContainer.setLogLevel(level.toString());
        assertEquals(level.toString(), logContainer.getLogLevel());
    }

    @Test
    public void testSetMessage() throws Exception {
        String message = "message";
        logContainer.setMessage(message);
        assertEquals(message, logContainer.getMessage());
    }

    @Test
    public void testToJson() throws Exception {
        logContainer.setMessage("doesn't matter");
        logContainer.setDate(new Date());
        logContainer.setLoggerName("dont care");
        logContainer.setLogLevel(LogLevel.LEVEL_TRACE.toString());

        Gson gson = new Gson();
        String json = gson.toJson(logContainer);
        assertEquals(json, logContainer.toJson());
    }
}