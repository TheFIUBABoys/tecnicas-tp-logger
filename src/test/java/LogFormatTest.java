import level.LevelDebug;
import level.LogLevel;
import org.junit.Assert;

import static org.junit.Assert.*;

public class LogFormatTest {

    private LogFormat messageFormat;
    private LogFormat messageFormatInferred;

    @org.junit.Before
    public void setUp() throws Exception {
        messageFormat = new LogFormat("%p - %m");
        messageFormatInferred = new LogFormat("%t - %F - %L - %M");
    }

    @org.junit.Test
    public void testFormatLogMessage() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();

        Assert.assertEquals("DEBUG - Message", messageFormat.formatLogMessage(message, logLevel));
    }
    @org.junit.Test
    public void testTest() throws Exception {
        String message = "Message";
        LogLevel logLevel = new LevelDebug();

        Assert.assertEquals("main - LogFormatTest.java - 30 - testTest",  messageFormatInferred.formatLogMessage(message, logLevel));
    }
}