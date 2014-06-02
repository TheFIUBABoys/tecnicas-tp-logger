package logger.slf4jAdapter;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.fail;

/**
 * Created by Lucas on 5/31/2014.
 */
public class LoggerSlf4jAdapterTest {

    private LoggerSlf4jAdapter loggerInstance;
    private ByteArrayOutputStream baos;

    @Before
    public void setUp() {
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @Test
    public void testLoggerFactory() throws Exception {
        fail("Not implemented yet");
    }

    @Test
    public void testLogWarn() throws Exception {
        fail("Not implemented yet");
    }

    @Test
    public void testLogError() throws Exception {
        fail("Not implemented yet");
    }

    @Test
    public void testLogDebug() throws Exception {
        fail("Not implemented yet");
    }

    @Test
    public void testLogInfo() throws Exception {
        fail("Not implemented yet");
    }

}
