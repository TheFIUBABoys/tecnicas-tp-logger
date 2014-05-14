package logger;

import java.io.*;
import java.util.*;

import level.LogLevel;
import loggerExceptions.*;
import org.junit.*;


public class LoggerLoadPropertiesTest {
    Logger loggerInstance;
    String filename = "appProperties.txt";
    String outputFilename = "outputFilename.txt";
    File standardOutputStream;


    private void setUpPropertyFileWithKeyValueDict(HashMap<String,String> dict)throws Exception{
        loggerInstance = null;
        loggerInstance = LoggerImpl.getLogger();;
        Properties applicationProps = new Properties();
        for (String key : dict.keySet()){
            applicationProps.setProperty(key, dict.get(key));
        }

        FileOutputStream outputFile = new FileOutputStream(filename);
        applicationProps.store(outputFile, "---No Comment---");
        outputFile.close();
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
        loggerInstance = LoggerImpl.getLogger();
    }

    @After
    public void tearDown() throws Exception {
        File f = new File(filename);
        f.delete();
    }

    private void setUpConsoleOutputProperty() throws Exception {
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("consoleOutput", "true");
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testLoadConsoleOutput() throws Exception {
        setUpStandardOutputRedirect();
        setUpConsoleOutputProperty();
        loggerInstance.loadConfigFromFile(filename);
        loggerInstance.setMessageFormat(new LogFormatImpl("%m"));
        loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);
        loggerInstance.setLogLevel(LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);

        testFile(standardOutputStream);
        tearDownStandardOutputRedirect();
    }

    private void testFile(File f) throws Exception{
        Scanner s = new Scanner(f);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();
        Assert.assertEquals("Fatal", list.get(0));
        Assert.assertEquals("Message", list.get(1));
        Assert.assertEquals("Error", list.get(2));
        Assert.assertEquals("Message", list.get(3));
    }


    private void testFile(String aFilename) throws Exception{
       testFile(new File (aFilename));
    }


    private void setUpOutputFileProperty() throws Exception {
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("outputFile", outputFilename);
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testLoadOutputFile() throws Exception {
        setUpOutputFileProperty();
        loggerInstance.loadConfigFromFile(filename);
        loggerInstance.setConsoleOutput(false);
        loggerInstance.setMessageFormat(new LogFormatImpl("%m"));
        loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);
        loggerInstance.setLogLevel(LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);

        testFile(outputFilename);
    }



    private void setUpOutputFilesProperty() throws Exception {
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("outputFile", outputFilename+","+outputFilename+"1");
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testLoadSeveralOutputFiles() throws Exception {
        setUpOutputFilesProperty();
        loggerInstance.loadConfigFromFile(filename);
        loggerInstance.setConsoleOutput(false);
        loggerInstance.setMessageFormat(new LogFormatImpl("%m"));
        loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);
        loggerInstance.setLogLevel(LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);

        testFile(outputFilename);
        testFile(outputFilename+"1");

    }

    private void setUpFatalLevelProperty() throws Exception {
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("logLevel", LogLevel.LEVEL_FATAL.toString());
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testLoadLogLevel() throws Exception {
        setUpStandardOutputRedirect();
        setUpFatalLevelProperty();
        loggerInstance.loadConfigFromFile(filename);
        loggerInstance.setMessageFormat(new LogFormatImpl("%m"));
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);

        testFile(standardOutputStream);
        tearDownStandardOutputRedirect();
    }

    private void setUpFormatProperty() throws Exception {
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("logFormat", "%m");
        data.put("consoleOutput", "true");
        data.put("outputFile", "hullo.txt");
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testLoadLogFormat() throws Exception {
        setUpStandardOutputRedirect();
        setUpFormatProperty();
        //loggerInstance.loadConfigFromFile(filename);
        loggerInstance.addOutputFile("hullo.txt");
        loggerInstance.logMessage("Error Message", LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Fatal Message", LogLevel.LEVEL_FATAL);

        testFile(standardOutputStream);
        tearDownStandardOutputRedirect();

    }

    private void setUpWrongFormatConsoleProperty() throws Exception{
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("consoleOutput", "asdasdasd");
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testWrongFormatConsole() throws Exception {
        try{
            setUpWrongFormatConsoleProperty();
            loggerInstance.loadConfigFromFile(filename);
            Assert.assertTrue(false);
        }
        catch(WrongPropertyFormatException e){
            Assert.assertTrue(true);
        }
        catch(Exception e){
            Assert.assertTrue(false);
        }
    }

    private void setUpWrongFormatLevelProperty() throws Exception{
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("logLevel", "asdasdasd");
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testWrongFormatLevel() throws Exception {
        try{
            setUpWrongFormatLevelProperty();
            loggerInstance.loadConfigFromFile(filename);
            Assert.assertTrue(false);
        }
        catch(WrongPropertyFormatException e){
            Assert.assertTrue(true);
        }
        catch(Exception e){
            Assert.assertTrue(false);
        }
    }

    private void setUpWrongFormatFormatProperty() throws Exception{
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("logFormat", "Tasdakldaslñd");
        setUpPropertyFileWithKeyValueDict(data);
    }

    public void testWrongFormatFormat() throws Exception {
        try{
            setUpWrongFormatFormatProperty();
            loggerInstance.loadConfigFromFile(filename);
            Assert.assertTrue(false);
        }
        catch(WrongPropertyFormatException e){
            Assert.assertTrue(true);
        }
        catch(Exception e){
            Assert.assertTrue(false);
        }
    }



}