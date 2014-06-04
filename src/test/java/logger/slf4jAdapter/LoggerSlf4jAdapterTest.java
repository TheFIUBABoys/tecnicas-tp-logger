package logger.slf4jAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lucas on 5/31/2014.
 */
public class LoggerSlf4jAdapterTest {

    private Logger logger;
    private File standardOutputStream;
    private ILoggerFactory loggerFactory;
    private ByteArrayOutputStream baos;

    private void setUpStandarOutputRedirect() throws IOException {
        standardOutputStream = new File("tmp_stdout_adapter.txt");
        standardOutputStream.createNewFile();
        System.setOut(new PrintStream(standardOutputStream));
    }

    @After
    public void tearDownStandarOutputRedirect() throws IOException {
        standardOutputStream.delete();
        System.setOut(System.out);
    }

    @Before
    public void setUp() throws Exception {
        loggerFactory = LoggerSlf4jAdapterFactoryBinder.getSingleton().getLoggerFactory();
        logger = loggerFactory.getLogger(LoggerSlf4jAdapter.class.getName());
        setUpStandarOutputRedirect();
    }

    @Test
    public void testLoggerFactory() throws Exception {
        logger = loggerFactory.getLogger(LoggerSlf4jAdapter.class.getName());
        assertEquals(logger.getClass().getName(),LoggerSlf4jAdapter.class.getName());
    }

    @Test
    public void testLogWarn() throws Exception {
        logger.warn("Message This Message");
        assertMessageLoggedCorrectlyForLevel("WARN");
    }

    @Test
    public void testLogError() throws Exception {
        logger.warn("Message This Message");
        assertMessageLoggedCorrectlyForLevel("ERROR");
    }


    @Test
    public void testLogTrace() throws Exception {
        logger.warn("Message This Message");
        assertMessageLoggedCorrectlyForLevel("TRACE");
    }

    @Test
    public void testLogDebug() throws Exception {
        logger.warn("Message This Message");
        assertMessageLoggedCorrectlyForLevel("DEBUG");
    }

    @Test
    public void testLogInfo() throws Exception {
        logger.warn("Message This Message");
        assertMessageLoggedCorrectlyForLevel("INFO");
    }


    @Test
    public void testLogFormattedWithOneObject() throws Exception {
        String error = "Error Message {} %n";
        String formattedObject = "This message";
        logger.error(error,formattedObject);
        assertFormattedMessageLoggedCorrectly();

    }

    @Test
    public void testLogFormattedWithTwoObjects() throws Exception {

        String error = "Error Message {} {} %n";
        String f1 = "This";
        String f2 = "message";
        logger.error(error,f1,f2);
        assertFormattedMessageLoggedCorrectly();

    }

    @Test
    public void testLogFormattedWithSeveralObjects() throws Exception {
        String error = "{} {} {} {} %n";
        String f1 = "Error";
        String f2 = "Message";
        String f3 = "This ";
        String f4 = "message";
        logger.error(error,f1,f2,f3,f4);
        assertFormattedMessageLoggedCorrectly();

    }

    private void assertFormattedMessageLoggedCorrectly() throws FileNotFoundException {
        Scanner s = new Scanner(standardOutputStream);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();

        assertTrue(list.get(0).equals("ERROR"));
        assertTrue(list.get(1).equals("-"));
        assertTrue(list.get(2).equals("Error"));
        assertTrue(list.get(3).equals("Message"));
        assertTrue(list.get(4).equals("This"));
        assertTrue(list.get(5).equals("message"));
    }

    private void assertMessageLoggedCorrectlyForLevel(String level) throws FileNotFoundException {
        Scanner s = new Scanner(standardOutputStream);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();
        assertEquals(list.get(0), level);
        assertEquals(list.get(1),"-");
        assertEquals(list.get(3), "Message");
        assertEquals(list.get(4), "This");
        assertEquals(list.get(5), "Message");
    }

}
