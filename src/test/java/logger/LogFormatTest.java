package logger;

import level.LevelDebug;
import level.LogLevel;
import loggerExceptions.InvalidFormatException;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFormatTest {

    private LogFormat messageFormat;
    private LogFormat threadFormat;
    private LogFormat defaultFormat;
    private LogFormat messageFormatDate;

    @org.junit.Before
    public void setUp() throws Exception {
        messageFormat = new LogFormatImpl("%p - %m");
        threadFormat = new LogFormatImpl("%t - %L - %M");
        defaultFormat = new LogFormatImpl();
        messageFormatDate = new LogFormatImpl("%d{dd-MM-yyyy}");
    }

    @org.junit.Test
    public void testFormatLogMessage() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();

        Assert.assertEquals("DEBUG - Message", messageFormat.formatLogMessage(message, logLevel));
    }

    @org.junit.Test
    public void testDefaultFormatLogMessage() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();

        Assert.assertEquals("DEBUG - Message", defaultFormat.formatLogMessage(message, logLevel));
    }

    @org.junit.Test
    public void testFormatWithDate() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();
        String dateFormat = "dd-MM-yyyy";
        String expectedDate = (new SimpleDateFormat(dateFormat)).format(new Date());

        Assert.assertEquals(expectedDate, messageFormatDate.formatLogMessage(message, logLevel));
    }

    @org.junit.Test(expected = InvalidFormatException.class)
    public void testInvalidFormat() throws Exception {
        String invalidFormatString = "%p - %m - %w";
        new LogFormatImpl(invalidFormatString);
    }

    @org.junit.Test
    public void testThreadFormat() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();
        String expected = "main - 64 - testThreadFormat";

        Assert.assertEquals(expected, threadFormat.formatLogMessage(message, logLevel));
    }
}