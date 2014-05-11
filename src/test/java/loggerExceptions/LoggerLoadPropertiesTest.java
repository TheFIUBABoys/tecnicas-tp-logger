package loggerExceptions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import level.LogLevel;
import logger.LogFormat;
import logger.Logger;

import org.junit.*;

import static org.junit.Assert.*;


public class LoggerLoadPropertiesTest {
	Logger loggerInstance;
	String filename = "appProperties.txt";
	String outputFilename = "outputFilename.txt";

	
    @Before
    public void setUp() throws Exception {
        loggerInstance = Logger.getLogger();
    }

	private void setUpConsoleOutputProperty() throws Exception{
    	File f = new File(filename);
    	f.delete();
    	loggerInstance.resetLogger();
    	loggerInstance = Logger.getLogger();
		Properties applicationProps = new Properties();
    	applicationProps.setProperty("consoleOutput","true");
    	FileOutputStream outputFile = new FileOutputStream(filename);
    	applicationProps.store(outputFile, "---No Comment---");
    	outputFile.close();
	}
	
    @Test
    public void testLoadConsoleOutput() throws Exception {
    	setUpConsoleOutputProperty();
    	loggerInstance.loadConfigFromFile(filename);
    	loggerInstance.setMessageFormat(new LogFormat("%m"));
    	loggerInstance.setLogLevel(LogLevel.LEVEL_FATAL);
        loggerInstance.logMessage("Fatal Message%n", LogLevel.LEVEL_FATAL);
        loggerInstance.setLogLevel(LogLevel.LEVEL_ERROR);
        loggerInstance.logMessage("Error Message%n", LogLevel.LEVEL_ERROR);
       
       //TODO: add console reading to test.
        assertTrue(false);
    }
    
	private void setUpOutputFileProperty() throws Exception{
    	File f = new File(outputFilename);
    	f.delete();
    	File f1 = new File(filename);
    	f1.delete();
    	loggerInstance.resetLogger();
    	loggerInstance = Logger.getLogger();
    	loggerInstance.setConsoleOutput(false);
		Properties applicationProps = new Properties();
    	applicationProps.setProperty("outputFile",outputFilename);
    	FileOutputStream outputFile = new FileOutputStream(filename);
    	applicationProps.store(outputFile, "---No Comment---");
    	outputFile.close();
	}
	
    @Test
    public void testLoadOutputFile() throws Exception {
    	setUpOutputFileProperty();
    	loggerInstance.loadConfigFromFile(filename);
    	loggerInstance.setMessageFormat(new LogFormat("%m"));
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
    	//TODO
        assertTrue(false);
    }
    
    @Test
    public void testLoadLogFormat() throws Exception {
    	//TODO: Logformatfactory.
        assertTrue(false);
    }
    

}