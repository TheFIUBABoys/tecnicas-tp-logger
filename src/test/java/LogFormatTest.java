import level.LevelDebug;
import level.LogLevel;
import org.junit.Assert;

import static org.junit.Assert.*;

public class LogFormatTest {

    private LogFormat messageFormat;

    @org.junit.Before
    public void setUp() throws Exception {
        messageFormat = new LogFormat("%p - %m");
    }

    @org.junit.Test
    public void testFormatLogMessage() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();

        Assert.assertEquals("DEBUG - Message", messageFormat.formatLogMessage(message, logLevel, 0, "", ""));
    }
}