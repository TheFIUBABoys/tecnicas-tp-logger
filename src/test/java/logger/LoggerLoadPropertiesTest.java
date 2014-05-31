package logger;

import logger.level.LogLevel;
import logger.exceptions.WrongPropertyFormatException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.assertEquals;


public class LoggerLoadPropertiesTest {
    Logger loggerInstance;
    String filename = "logger­config.properties";
    String outputFilename = "outputFilename.txt";
    File standardOutputStream;


    private void setUpPropertyFileWithKeyValueDict(Map<String, String> dict) throws Exception {
        Properties applicationProps = new Properties();
        for (String key : dict.keySet()) {
            applicationProps.setProperty(key, dict.get(key));
        }

        FileOutputStream outputFile = new FileOutputStream(filename);
        applicationProps.store(outputFile, "---No Comment---");
        outputFile.close();

        loggerInstance.loadConfig();
    }

    private void tearDownStandardOutputRedirect() throws IOException {
        standardOutputStream.delete();
        System.setOut(System.out);
    }

    private void setUpStandardOutputRedirect() throws IOException {
        standardOutputStream = new File("tmp_stdout.txt");
        standardOutputStream.createNewFile();
        System.setOut(new PrintStream(standardOutputStream));
    }

    @Before
    public void setUp() throws Exception {
        // CREATING CONFIG FILE FOR LOGGER TO CREATE ITS READER
        FileOutputStream file = new FileOutputStream(filename);
        file.close();
        loggerInstance = LoggerImpl.getLogger("property");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        File directory = new File(("./"));
        for(File f: directory.listFiles()) {
            if (f.getName().endsWith(".txt"))
                f.delete();
            if (f.getName().endsWith(".properties"))
                f.delete();
        }
    }

    private void setUpConsoleOutputProperty() throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("consoleOutput", "true");
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testCheckIfLoadingConsoleOutputPropertyFromFileWork() throws Exception {
        setUpStandardOutputRedirect();
        setUpConsoleOutputProperty();
        loggerInstance.setMessageFormat(new LogFormatImpl("%m"));
        loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);
        loggerInstance.setLogLevel(LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);

        testFile(standardOutputStream);
        tearDownStandardOutputRedirect();
    }

    private void testFile(File f) throws Exception {
        Scanner s = new Scanner(f);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();
        assertEquals("Fatal", list.get(0));
        assertEquals("Message", list.get(1));
        assertEquals("Error", list.get(2));
        assertEquals("Message", list.get(3));
    }


    private void testFile(String aFilename) throws Exception {
        testFile(new File(aFilename));
    }


    private void setUpOutputFileProperty() throws Exception {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("outputFile", outputFilename);
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testLoadOutputFile() throws Exception {
        setUpOutputFileProperty();
        loggerInstance.setConsoleOutput(false);
        loggerInstance.setMessageFormat(new LogFormatImpl("%m"));
        loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);
        loggerInstance.setLogLevel(LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);

        testFile(outputFilename);
    }


    private void setUpOutputFilesProperty() throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("outputFile", outputFilename + "," + "1"+outputFilename);
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testLoadSeveralOutputFiles() throws Exception {
        setUpOutputFilesProperty();
        loggerInstance.setConsoleOutput(false);
        loggerInstance.setMessageFormat(new LogFormatImpl("%m"));
        loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);
        loggerInstance.setLogLevel(LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);

        testFile(outputFilename);
        testFile("1"+outputFilename );

    }

    private void setUpFatalLevelProperty() throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("logLevel", LogLevel.LEVEL_FATAL.toString());
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testLoadLogLevel() throws Exception {
        setUpFatalLevelProperty();
        loggerInstance.addOutputFile(outputFilename);
        loggerInstance.setMessageFormat(new LogFormatImpl("%m"));
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);

        testFile(outputFilename);
    }

    private void setUpFormatProperty() throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("logFormat", "%m");
        data.put("consoleOutput", "true");
        data.put("outputFile", "hullo.txt");
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testLoadLogFormat() throws Exception {
        setUpFormatProperty();
        loggerInstance.addOutputFile(outputFilename);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        testFile(outputFilename);
    }

    private void setUpWrongFormatConsoleProperty() throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("consoleOutput", "asdasdasd");
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test(expected = WrongPropertyFormatException.class)
    public void testWrongFormatConsole() throws Exception {
        setUpWrongFormatConsoleProperty();
    }

    private void setUpWrongFormatLevelProperty() throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("logLevel", "asdasdasd");
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test(expected = WrongPropertyFormatException.class)
    public void testWrongFormatLevel() throws Exception {
        setUpWrongFormatLevelProperty();
    }

    private void setUpWrongFormatFormatProperty() throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("logFormat", "%W");
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test(expected = WrongPropertyFormatException.class)
    public void testWrongFormatFormat() throws Exception {
        setUpWrongFormatFormatProperty();
    }

}