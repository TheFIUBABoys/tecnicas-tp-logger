package logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import level.LogLevel;

import org.junit.*;

import static org.junit.Assert.*;


public class LoggerLoadPropertiesTest {
    Logger loggerInstance;
    String filename = "appProperties.txt";
    String outputFilename = "outputFilename.txt";
    File standarOutputSteam;

    private void setUpOutputFileProperty() throws Exception{
        File f = new File(outputFilename);
        f.delete();
        File f1 = new File(filename);
        f1.delete();
        loggerInstance = LoggerImpl.getLogger();
        loggerInstance.setConsoleOutput(false);
        Properties applicationProps = new Properties();
        applicationProps.setProperty("outputFile",outputFilename);
        FileOutputStream outputFile = new FileOutputStream(filename);
        applicationProps.store(outputFile, "---No Comment---");
        outputFile.close();
    }

    private void setUpConsoleOutputProperty() throws Exception{
        File f = new File(filename);
        f.delete();
        loggerInstance = LoggerImpl.getLogger();
        Properties applicationProps = new Properties();
        applicationProps.setProperty("consoleOutput","true");
        FileOutputStream outputFile = new FileOutputStream(filename);
        applicationProps.store(outputFile, "---No Comment---");
        outputFile.close();
    }

    private void setUpFatalLevelProperty() throws Exception{
        File f = new File(filename);
        f.delete();
        loggerInstance = LoggerImpl.getLogger();
        Properties applicationProps = new Properties();
        applicationProps.setProperty("logLevel",LogLevel.LEVEL_FATAL.toString());
        FileOutputStream outputFile = new FileOutputStream(filename);
        applicationProps.store(outputFile, "---No Comment---");
        outputFile.close();
    }

    private void setUpFormatProperty() throws Exception{
        File f = new File(filename);
        f.delete();
        loggerInstance = LoggerImpl.getLogger();
        Properties applicationProps = new Properties();
        applicationProps.setProperty("logFormat","%m");
        FileOutputStream outputFile = new FileOutputStream(filename);
        applicationProps.store(outputFile, "---No Comment---");
        outputFile.close();
    }

    private void setUpStandarOutputRedirect() throws IOException {
        standarOutputSteam = new File("tmp_stdout.txt");
        standarOutputSteam.createNewFile();
        System.setOut(new PrintStream(standarOutputSteam));
    }

    private void tearDownStandarOutputRedirect() throws IOException{
        standarOutputSteam.delete();
        System.setOut(System.out);
    }

    @Before
    public void setUp() throws Exception {
        loggerInstance = LoggerImpl.getLogger();
    }

    @Test
    public void testLoadConsoleOutput() throws Exception {
        setUpStandarOutputRedirect();

        setUpConsoleOutputProperty();
        loggerInstance.loadConfigFromFile(filename);
        loggerInstance.setMessageFormat(new LogFormatImpl("%m"));
        loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);
        loggerInstance.setLogLevel(LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);

        Scanner s = new Scanner(new File(outputFilename));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();

        assertTrue(list.get(0).equals("Fatal"));
        assertTrue(list.get(1).equals("Message"));
        assertTrue(list.get(2).equals("Error"));
        assertTrue(list.get(3).equals("Message"));

        tearDownStandarOutputRedirect();
    }

    @Test
    public void testLoadOutputFile() throws Exception {
        setUpOutputFileProperty();
        loggerInstance.loadConfigFromFile(filename);
        loggerInstance.setMessageFormat(new LogFormatImpl("%m"));
        loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);
        loggerInstance.setLogLevel(LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);


        Scanner s = new Scanner(new File(outputFilename));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();

        assertTrue(list.get(0).equals("Fatal"));
        assertTrue(list.get(1).equals("Message"));
        assertTrue(list.get(2).equals("Error"));
        assertTrue(list.get(3).equals("Message"));
    }

    @Test
    public void testLoadLogLevel() throws Exception {
        setUpStandarOutputRedirect();

        setUpFatalLevelProperty();
        loggerInstance.loadConfigFromFile(filename);
        loggerInstance.setMessageFormat(new LogFormatImpl("%m"));
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);

        Scanner s = new Scanner(standarOutputSteam);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();

        assertTrue(list.get(0).equals("Fatal"));
        assertTrue(list.get(1).equals("Message"));

        tearDownStandarOutputRedirect();
    }

    @Test
    public void testLoadLogFormat() throws Exception {
        setUpStandarOutputRedirect();

        setUpFormatProperty();
        loggerInstance.loadConfigFromFile(filename);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);

        Scanner s = new Scanner(standarOutputSteam);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();

        assertTrue(list.get(0).equals("Error"));
        assertFalse(list.get(0).equals("ERROR"));
        assertTrue(list.get(1).equals("Message"));
        assertTrue(list.get(2).equals("Fatal"));
        assertFalse(list.get(2).equals("FATAL"));
        assertTrue(list.get(3).equals("Message"));

        tearDownStandarOutputRedirect();
    }

    @After
    public void tearDown(){
        File f = new File(filename);
        f.delete();
    }

}