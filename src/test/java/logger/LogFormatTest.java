package logger;

import logger.level.LevelDebug;
import logger.level.LogLevel;
import loggerExceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class LogFormatTest {

    private LogFormat messageFormat;
    private LogFormat threadFormat;
    private LogFormat defaultFormat;
    private LogFormat messageFormatDate;
    private LogFormat endOfLineFormat;

    @Before
    public void setUp() throws Exception {
        messageFormat = new LogFormatImpl("%p - %m");
        endOfLineFormat = new LogFormatImpl("%m%n");
        threadFormat = new LogFormatImpl("%t - %M");
        defaultFormat = new LogFormatImpl();
        messageFormatDate = new LogFormatImpl("%d{dd-MM-yyyy}");
    }

    @Test
    public void testFormatLogMessage() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();

        assertEquals("DEBUG - Message", messageFormat.formatLogMessage(message, logLevel));
    }

    @Test
    public void testDefaultFormatLogMessage() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();

        assertEquals("DEBUG - Message", defaultFormat.formatLogMessage(message, logLevel));
    }

    @Test
    public void testFormatWithDate() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();
        String dateFormat = "dd-MM-yyyy";
        String expectedDate = (new SimpleDateFormat(dateFormat)).format(new Date());

        assertEquals(expectedDate, messageFormatDate.formatLogMessage(message, logLevel));
    }

    @Test(expected = InvalidFormatException.class)
    public void testInvalidFormat() throws Exception {
        String invalidFormatString = "%p - %m - %w";
        new LogFormatImpl(invalidFormatString);
    }

    @Test
    public void testThreadFormat() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();
        String expected = "main - testThreadFormat";

        assertEquals(expected, threadFormat.formatLogMessage(message, logLevel));
    }

    @Test
    public void testConfigurableEol() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();
        String expected = "Message,";
        endOfLineFormat.setEndOfLineSeparator(",");

        assertEquals(expected, endOfLineFormat.formatLogMessage(message, logLevel));
    }
}