package logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import level.LogLevel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoggerTest {

    private Logger loggerInstance;
    private File standarOutputSteam;
    private String permanentFilename = "Log.txt";
    private String temporaryFilename = "TempLog.txt";

    private void setUpStandarOutputRedirect() throws IOException {
        standarOutputSteam = new File("tmp_stdout.txt");
        standarOutputSteam.createNewFile();
        System.setOut(new PrintStream(standarOutputSteam));
    }

    private void tearDownStandarOutputRedirect() throws IOException {
        standarOutputSteam.delete();
        System.setOut(System.out);
    }

    @Before
    public void setUp() throws Exception {
        loggerInstance = LoggerImpl.getLogger();
        loggerInstance.setConsoleOutput(true);
        loggerInstance.setMessageFormat(new LogFormatImpl("%p - %m"));
        loggerInstance.addOutputFile(permanentFilename);
        loggerInstance.addOutputFile(temporaryFilename);
        setUpStandarOutputRedirect();
    }

    @After
    public void tearDown() throws Exception {
        tearDownStandarOutputRedirect();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        File directory = new File(("./"));
        for(File f: directory.listFiles())
            if(f.getName().endsWith(".txt"))
                f.delete();
    }

    @Test
    public void testLogMessageInfo() throws Exception {
        loggerInstance.setLogLevel(LogLevel.LEVEL_INFO);

        String error = "Error Message%n";
        String debug = "Debug Message%n";
        String info = "Info Message%n";

        loggerInstance.logMessage(error, LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage(debug, LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage(info, LogLevel.LEVEL_INFO);

        Scanner s = new Scanner(standarOutputSteam);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();

        assertTrue(list.get(0).equals("ERROR"));
        assertTrue(list.get(1).equals("-"));
        assertTrue(list.get(2).equals("Error"));
        assertTrue(list.get(3).equals("Message"));

        assertFalse(list.get(4).equals("DEBUG"));
        assertFalse(list.get(6).equals("Debug"));

        assertTrue(list.get(4).equals("INFO"));
        assertTrue(list.get(5).equals("-"));
        assertTrue(list.get(6).equals("Info"));
        assertTrue(list.get(7).equals("Message"));

    }

    @Test
    public void testLogMessageOff() throws Exception {
        loggerInstance.setLogLevel(LogLevel.LEVEL_OFF);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Debug Message%n", LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage("Info Message%n", LogLevel.LEVEL_INFO);

        Scanner s = new Scanner(standarOutputSteam);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();

        assertTrue(list.isEmpty());

    }

    @Test
    public void testLogMessageFatal() throws Exception {
        loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Debug Message%n", LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage("Warn Message%n", LogLevel.LEVEL_WARN);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);

        Scanner s = new Scanner(standarOutputSteam);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();

        assertFalse(list.get(0).equals("ERROR"));
        assertFalse(list.get(2).equals("Error"));

        assertTrue(list.get(0).equals("FATAL"));
        assertTrue(list.get(2).equals("Fatal"));
    }

    @Test
    public void testAddSameOutputFileSeveralTimes() throws Exception {
        loggerInstance.setLogLevel(LogLevel.LEVEL_INFO);

        String error = "Error Message%n";
        String debug = "Debug Message%n";
        String info = "Info Message%n";

        loggerInstance.logMessage(error, LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage(debug, LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage(info, LogLevel.LEVEL_INFO);
        for (int i = 0; i<10; i++){
            loggerInstance.addOutputFile(temporaryFilename);
            loggerInstance.addOutputFile(permanentFilename);
        }
        Scanner s = new Scanner(standarOutputSteam);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();

        assertTrue(list.get(0).equals("ERROR"));
        assertTrue(list.get(1).equals("-"));
        assertTrue(list.get(2).equals("Error"));
        assertTrue(list.get(3).equals("Message"));

        assertFalse(list.get(4).equals("DEBUG"));
        assertFalse(list.get(6).equals("Debug"));

        assertTrue(list.get(4).equals("INFO"));
        assertTrue(list.get(5).equals("-"));
        assertTrue(list.get(6).equals("Info"));
        assertTrue(list.get(7).equals("Message"));

    }

}