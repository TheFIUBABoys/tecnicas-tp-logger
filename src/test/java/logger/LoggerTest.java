package logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import level.LogLevel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoggerTest {

    private Logger loggerInstance;
    private File standarOutputSteam;
    private String permanentFilename = "Log.txt";
    private String temporaryFilename = "TempLog.txt";

    private void setUpStandarOutputRedirect() throws FileNotFoundException, IOException{
        standarOutputSteam = new File("tmp_stdout.txt");
        standarOutputSteam.createNewFile();
        System.setOut(new PrintStream(standarOutputSteam));
    }

    private void tearDownStandarOutputRedirect() throws FileNotFoundException, IOException{
        System.setOut(System.out);
    }

    @Before
    public void setUp() throws Exception {
        loggerInstance = Logger.getLogger();
        loggerInstance.setConsoleOutput(true);
        loggerInstance.setMessageFormat(new LogFormat("%p - %m"));
        loggerInstance.addOutputFile(permanentFilename);
        loggerInstance.addOutputFile(temporaryFilename);
    }

    @Test
    public void testLogMessageInfo() throws Exception {
        setUpStandarOutputRedirect();
        loggerInstance.setLogLevel(LogLevel.LEVEL_INFO);

        String error = "Error Message%n";
        String debug = "Debug Message%n";
        String info = "Info Message%n";

        loggerInstance.logMessage(error, LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage(debug, LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage(info, LogLevel.LEVEL_INFO);

        Scanner s = new Scanner(standarOutputSteam);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
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

        tearDownStandarOutputRedirect();

    }

    @Test
    public void testLogMessageOff() throws Exception {
        setUpStandarOutputRedirect();

        loggerInstance.setLogLevel(LogLevel.LEVEL_OFF);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Debug Message%n", LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage("Info Message%n", LogLevel.LEVEL_INFO);

        Scanner s = new Scanner(standarOutputSteam);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();

        assertTrue(list.isEmpty());

        tearDownStandarOutputRedirect();
    }

    @Test
    public void testLogMessageFatal() throws Exception {
        setUpStandarOutputRedirect();

        loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Debug Message%n", LogLevel.LEVEL_DEBUG);
        loggerInstance.logMessage("Warn Message%n", LogLevel.LEVEL_WARN);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);

        Scanner s = new Scanner(standarOutputSteam);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();

        assertFalse(list.get(0).equals("ERROR"));
        assertFalse(list.get(2).equals("Error"));

        assertTrue(list.get(0).equals("FATAL"));
        assertTrue(list.get(2).equals("Fatal"));

        tearDownStandarOutputRedirect();
    }

}