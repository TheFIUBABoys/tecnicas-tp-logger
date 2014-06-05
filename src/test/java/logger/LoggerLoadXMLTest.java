package logger;

import logger.exceptions.WrongPropertyFormatException;
import logger.format.LogFormatImpl;
import logger.level.LogLevel;
import logger.writer.FileWriter;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class LoggerLoadXMLTest {
    Logger loggerInstance;
    String filename = "logger-config.xml";
    String outputFilename = "outputFilename.txt";
    File standardOutputStream;


    private void setUpPropertyFileWithKeyValueDict(Map<String, String> dict) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // Creating root element
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("loggerConfig");
        doc.appendChild(rootElement);

        for (String key : dict.keySet()) {
            Element elem = doc.createElement(key);
            elem.appendChild(doc.createTextNode(dict.get(key)));
            rootElement.appendChild(elem);
        }

        // Writing content to the disk
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filename));

        transformer.transform(source, result);

        if (loggerInstance != null){
            loggerInstance.loadConfig();
        }

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
        setUpConsoleOutputProperty();
        loggerInstance = LoggerImpl.getLogger("xml");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        File directory = new File(("./"));
        for (File f : directory.listFiles()) {
            if (f.getName().endsWith(".txt"))
                f.delete();
            if (f.getName().equals("logger-config.xml"))
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
        data.put("outputFile", outputFilename + "," + "1" + outputFilename);
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
        testFile("1" + outputFilename);

    }

    private void setUpFatalLevelProperty() throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("logLevel", LogLevel.LEVEL_FATAL.toString());
        setUpPropertyFileWithKeyValueDict(data);
    }

    @Test
    public void testLoadLogLevel() throws Exception {
        setUpFatalLevelProperty();
        loggerInstance.addOutput(new FileWriter(outputFilename));
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
        loggerInstance.addOutput(new FileWriter(outputFilename));
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