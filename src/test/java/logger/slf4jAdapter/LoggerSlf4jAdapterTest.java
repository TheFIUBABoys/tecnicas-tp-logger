package logger.slf4jAdapter;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lucas on 5/31/2014.
 */
public class LoggerSlf4jAdapterTest {

    private Logger logger;
    private ILoggerFactory loggerFactory;
    private ByteArrayOutputStream baos;
    private Marker marker;

    private void setUpStandarOutputRedirect() throws IOException {
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @Before
    public void setUp() throws Exception {
        marker = MarkerFactory.getMarker("test");
        loggerFactory = LoggerSlf4jAdapterFactoryBinder.getSingleton().getLoggerFactory();
        logger = loggerFactory.getLogger(LoggerSlf4jAdapter.class.getName());
        setUpStandarOutputRedirect();
    }

    @Test
    public void testLoggerFactory() throws Exception {
        logger = loggerFactory.getLogger(LoggerSlf4jAdapter.class.getName());
        assertEquals(logger.getClass().getName(),LoggerSlf4jAdapter.class.getName());
        assertEquals(logger.getName(),LoggerSlf4jAdapter.class.getName());
    }

    @Test
    public void testLogWarn() throws Exception {
        logger.warn("Message This Message");
        assertMessageLoggedCorrectlyForLevel("WARN");
        assertTrue(logger.isWarnEnabled());
    }

    @Test
    public void testLogError() throws Exception {
        logger.error("Message This Message");
        assertMessageLoggedCorrectlyForLevel("ERROR");
        assertTrue(logger.isErrorEnabled());
    }


    @Test
    public void testLogTrace() throws Exception {
        logger.trace("Message This Message");
        assertMessageLoggedCorrectlyForLevel("TRACE");
        assertTrue(logger.isTraceEnabled());
    }

    @Test
    public void testLogDebug() throws Exception {
        logger.debug("Message This Message");
        assertMessageLoggedCorrectlyForLevel("DEBUG");
        assertTrue(logger.isDebugEnabled());
    }

    @Test
    public void testLogInfo() throws Exception {
        logger.info("Message This Message");
        assertMessageLoggedCorrectlyForLevel("INFO");
        assertTrue(logger.isInfoEnabled());
    }


    @Test
    public void testLogFormattedWithOneObject() throws Exception {
        String error = "Error Message {}";
        String formattedObject = "This message";
        logger.error(error,formattedObject);
        assertFormattedMessageLoggedCorrectlyForLevel("ERROR");

        logger.trace(error,formattedObject);
        assertFormattedMessageLoggedCorrectlyForLevel("TRACE");

        logger.warn(error,formattedObject);
        assertFormattedMessageLoggedCorrectlyForLevel("WARN");

        logger.info(error,formattedObject);
        assertFormattedMessageLoggedCorrectlyForLevel("INFO");

        logger.debug(error,formattedObject);
        assertFormattedMessageLoggedCorrectlyForLevel("DEBUG");
    }

    @Test
    public void testLogFormattedWithTwoObjects() throws Exception {

        String error = "Error Message {} {}";
        String f1 = "This";
        String f2 = "message";
        logger.error(error,f1,f2);
        assertFormattedMessageLoggedCorrectlyForLevel("ERROR");

        logger.trace(error,f1,f2);
        assertFormattedMessageLoggedCorrectlyForLevel("TRACE");

        logger.warn(error,f1,f2);
        assertFormattedMessageLoggedCorrectlyForLevel("WARN");

        logger.info(error,f1,f2);
        assertFormattedMessageLoggedCorrectlyForLevel("INFO");

        logger.debug(error,f1,f2);
        assertFormattedMessageLoggedCorrectlyForLevel("DEBUG");

    }

    @Test
    public void testLogFormattedWithSeveralObjects() throws Exception {
        String error = "{} {} {} {}";
        String f1 = "Error";
        String f2 = "Message";
        String f3 = "This";
        String f4 = "message";
        logger.error(error,f1,f2,f3,f4);
        assertFormattedMessageLoggedCorrectlyForLevel("ERROR");

        logger.trace(error,f1,f2,f3,f4);
        assertFormattedMessageLoggedCorrectlyForLevel("TRACE");

        logger.warn(error,f1,f2,f3,f4);
        assertFormattedMessageLoggedCorrectlyForLevel("WARN");

        logger.info(error,f1,f2,f3,f4);
        assertFormattedMessageLoggedCorrectlyForLevel("INFO");

        logger.debug(error,f1,f2,f3,f4);
        assertFormattedMessageLoggedCorrectlyForLevel("DEBUG");

    }

    private void assertFormattedMessageLoggedCorrectlyForLevel(String level) throws Exception {
        String s = baos.toString();
        assertEquals(level+" - Error Message This message",s);
        setUpStandarOutputRedirect();
    }

    private void assertMessageLoggedCorrectlyForLevel(String level) throws Exception {
        String s = baos.toString();
        assertEquals(level+" - Message This Message",s);

    }

    private void assertFormattedMessageLoggedCorrectlyForLevel (String level, Throwable t) throws Exception {
        String s = baos.toString();
        assertEquals(level+" - Error Message. Exception: " + t.getMessage() +
                ". Due to: " + t.getCause(),s);
        setUpStandarOutputRedirect();
    }

    @Test
    public void testLogWarnWithMarker() throws Exception {
        logger.warn(marker,"Message This Message");
        assertMessageLoggedCorrectlyForLevel("WARN");
        assertTrue(logger.isWarnEnabled(marker));
    }

    @Test
    public void testLogErrorWithMarker() throws Exception {
        logger.error(marker,"Message This Message");
        assertMessageLoggedCorrectlyForLevel("ERROR");
        assertTrue(logger.isErrorEnabled(marker));
    }


    @Test
    public void testLogTraceWithMarker() throws Exception {
        logger.trace(marker,"Message This Message");
        assertMessageLoggedCorrectlyForLevel("TRACE");
        assertTrue(logger.isTraceEnabled(marker));
    }

    @Test
    public void testLogDebugWithMarker() throws Exception {
        logger.debug(marker,"Message This Message");
        assertMessageLoggedCorrectlyForLevel("DEBUG");
        assertTrue(logger.isDebugEnabled(marker));
    }

    @Test
    public void testLogInfoWithMarker() throws Exception {
        logger.info(marker,"Message This Message");
        assertMessageLoggedCorrectlyForLevel("INFO");
        assertTrue(logger.isInfoEnabled(marker));
    }


    @Test
    public void testLogFormattedWithOneObjectWithMarker() throws Exception {
        String error = "Error Message {}";
        String formattedObject = "This message";
        logger.error(marker,error,formattedObject);
        assertFormattedMessageLoggedCorrectlyForLevel("ERROR");

        logger.trace(marker,error,formattedObject);
        assertFormattedMessageLoggedCorrectlyForLevel("TRACE");

        logger.warn(marker,error,formattedObject);
        assertFormattedMessageLoggedCorrectlyForLevel("WARN");

        logger.info(marker,error,formattedObject);
        assertFormattedMessageLoggedCorrectlyForLevel("INFO");

        logger.debug(marker,error,formattedObject);
        assertFormattedMessageLoggedCorrectlyForLevel("DEBUG");
    }

    @Test
    public void testLogFormattedWithTwoObjectsWithMarker() throws Exception {

        String error = "Error Message {} {}";
        String f1 = "This";
        String f2 = "message";
        logger.error(marker,error,f1,f2);
        assertFormattedMessageLoggedCorrectlyForLevel("ERROR");

        logger.trace(marker,error,f1,f2);
        assertFormattedMessageLoggedCorrectlyForLevel("TRACE");

        logger.warn(marker,error,f1,f2);
        assertFormattedMessageLoggedCorrectlyForLevel("WARN");

        logger.info(marker,error,f1,f2);
        assertFormattedMessageLoggedCorrectlyForLevel("INFO");

        logger.debug(marker,error,f1,f2);
        assertFormattedMessageLoggedCorrectlyForLevel("DEBUG");


    }

    @Test
    public void testLogFormattedWithSeveralObjectsWithMarker() throws Exception {
        String error = "{} {} {} {}";
        String f1 = "Error";
        String f2 = "Message";
        String f3 = "This";
        String f4 = "message";
        logger.error(marker,error,f1,f2,f3,f4);
        assertFormattedMessageLoggedCorrectlyForLevel("ERROR");

        logger.trace(marker,error,f1,f2,f3,f4);
        assertFormattedMessageLoggedCorrectlyForLevel("TRACE");

        logger.warn(marker,error,f1,f2,f3,f4);
        assertFormattedMessageLoggedCorrectlyForLevel("WARN");

        logger.info(marker,error,f1,f2,f3,f4);
        assertFormattedMessageLoggedCorrectlyForLevel("INFO");

        logger.debug(marker,error,f1,f2,f3,f4);
        assertFormattedMessageLoggedCorrectlyForLevel("DEBUG");

    }

    @Test
    public void testLogFormattedWithOneException() throws Exception {
        String error = "Error Message";
        try {
            throw new Exception("Exception 1", new Exception("Cause"));
        } catch (Exception e) {
            logger.error(error,e);
            assertFormattedMessageLoggedCorrectlyForLevel("ERROR",e);

            logger.trace(error,e);
            assertFormattedMessageLoggedCorrectlyForLevel("TRACE",e);

            logger.warn(error,e);
            assertFormattedMessageLoggedCorrectlyForLevel("WARN",e);

            logger.info(error,e);
            assertFormattedMessageLoggedCorrectlyForLevel("INFO",e);

            logger.debug(error,e);
            assertFormattedMessageLoggedCorrectlyForLevel("DEBUG",e);

        }
    }

    @Test
    public void testLogFormattedWithOneExceptionUsingMarker() throws Exception {
        String error = "Error Message";
        try {
            throw new Exception("Exception 1", new Exception("Cause"));
        } catch (Exception e) {
            logger.error(marker, error,e);
            assertFormattedMessageLoggedCorrectlyForLevel("ERROR",e);

            logger.trace(marker, error,e);
            assertFormattedMessageLoggedCorrectlyForLevel("TRACE",e);

            logger.warn(marker, error,e);
            assertFormattedMessageLoggedCorrectlyForLevel("WARN",e);

            logger.info(marker, error,e);
            assertFormattedMessageLoggedCorrectlyForLevel("INFO",e);

            logger.debug(marker, error,e);
            assertFormattedMessageLoggedCorrectlyForLevel("DEBUG",e);

        }
    }

}
