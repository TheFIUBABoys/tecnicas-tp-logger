import level.LevelDebug;
import level.LogLevel;
import org.junit.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class LogFormatTest {

    private LogFormat messageFormat;
    private LogFormat messageFormatDate;

    @org.junit.Before
    public void setUp() throws Exception {
        messageFormat = new LogFormat("%p - %m");
        messageFormatDate = new LogFormat("%d{dd-MM-yyyy}");
    }

    @org.junit.Test
    public void testFormatLogMessage() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();

        Assert.assertEquals("DEBUG - Message", messageFormat.formatLogMessage(message, logLevel));
    }

    @org.junit.Test
    public void testFormatWithDate() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();
        String dateFormat = "dd-MM-yyyy";
        String expectedDate = (new SimpleDateFormat(dateFormat)).format(new Date());

        Assert.assertEquals(expectedDate, messageFormatDate.formatLogMessage(message, logLevel));
    }
}