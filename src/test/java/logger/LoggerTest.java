package logger;

import logger.format.LogFormatImpl;
import logger.level.LogLevel;
import logger.writer.FileWriter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoggerTest {

    private Logger loggerInstance;
    private File standardOutputStream;
    private String permanentFilename = "Log.txt";
    private String temporaryFilename = "TempLog.txt";

    private void setUpStandarOutputRedirect() throws IOException {
        standardOutputStream = new File("tmp_stdout.txt");
        standardOutputStream.createNewFile();
        System.setOut(new PrintStream(standardOutputStream));
    }

    private void tearDownStandarOutputRedirect() throws IOException {
        standardOutputStream.delete();
        System.setOut(System.out);
    }

    @Before
    public void setUp() throws Exception {
        loggerInstance = LoggerImpl.getLogger();
        loggerInstance.setConsoleOutput(true);
        loggerInstance.setMessageFormat(new LogFormatImpl("%p - %m"));
        loggerInstance.addOutput(new FileWriter(permanentFilename));
        loggerInstance.addOutput(new FileWriter(temporaryFilename));
        setUpStandarOutputRedirect();
    }

    @After
    public void tearDown() throws Exception {
        tearDownStandarOutputRedirect();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        File directory = new File(("./"));
        for (File f : directory.listFiles())
            if (f.getName().endsWith(".txt"))
                f.delete();
    }

    @Test
    public void logMessageInfo() throws Exception {
        loggerInstance.setLogLevel(LogLevel.LEVEL_INFO);

        String error = "Error Message%n";
        String debug = "Debug Message%n";
        String info = "Info Message%n";

        loggerInstance.logMessage(error, LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage(debug, LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage(info, LogLevel.LEVEL_INFO);

        assertMessageLoggedCorrectly();

    }

    @Test
    public void logMessageOff() throws Exception {
        loggerInstance.setLogLevel(LogLevel.LEVEL_OFF);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Debug Message%n", LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage("Info Message%n", LogLevel.LEVEL_INFO);

        assertNothingWasLogged();

    }

    private void assertNothingWasLogged() throws FileNotFoundException {
        Scanner s = new Scanner(standardOutputStream);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();

        assertTrue(list.isEmpty());
    }

    @Test
    public void logMessageFatal() throws Exception {
        loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Debug Message%n", LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage("Warn Message%n", LogLevel.LEVEL_WARN);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);

        assertOnlyFatalWasLogged();
    }

    private void assertOnlyFatalWasLogged() throws FileNotFoundException {
        Scanner s = new Scanner(standardOutputStream);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();

        assertTrue(list.get(0).equals("FATAL"));
        assertTrue(list.get(2).equals("Fatal"));
    }

    @Test
    public void addSameOutputFileSeveralTimes() throws Exception {
        loggerInstance.setLogLevel(LogLevel.LEVEL_INFO);

        String error = "Error Message%n";
        String debug = "Debug Message%n";
        String info = "Info Message%n";

        loggerInstance.logMessage(error, LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage(debug, LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage(info, LogLevel.LEVEL_INFO);
        for (int i = 0; i < 10; i++) {
            loggerInstance.addOutput(new FileWriter(temporaryFilename));
            loggerInstance.addOutput(new FileWriter(permanentFilename));
        }
        assertMessageLoggedCorrectly();

    }

    private void assertMessageLoggedCorrectly() throws FileNotFoundException {
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

        assertFalse(list.get(4).equals("DEBUG"));
        assertFalse(list.get(6).equals("Debug"));

        assertTrue(list.get(4).equals("INFO"));
        assertTrue(list.get(5).equals("-"));
        assertTrue(list.get(6).equals("Info"));
        assertTrue(list.get(7).equals("Message"));
    }


}