package logger;

import level.*;
import loggerExceptions.*;

import java.io.*;
import java.util.*;

/**
 * Created by GonchuB on 09/05/2014.
 * FIUBA
 */

public class Logger implements PropertyApplyingDelegate{

    private static Logger loggerInstance = null;
    
    private LogLevel logLevelSet;
    private LogFormat logFormat;
    private ArrayList<BufferedWriter> outputFiles;
    private Boolean terminalOutput;
    
	private LoggerPropertyLoader loggerPropertyLoader;

    private Logger() {
        logLevelSet = new LevelDebug();
        logFormat = new LogFormat();
        outputFiles = new ArrayList<BufferedWriter>();
        terminalOutput = true;
        loggerPropertyLoader = new LoggerPropertyLoader(this);

    }

    public static Logger getLogger() {
        if (loggerInstance == null) {
            loggerInstance = new Logger();
        }
        return loggerInstance;
    }
    public void resetLogger() { 
    	loggerInstance = null;
    }

    public void setMessageFormat(LogFormat messageFormat) {
        logFormat = messageFormat;
    }

    public void setConsoleOutput(Boolean value) {
        terminalOutput = value;
    }

    public void addOutputFile(String filename) throws IOException {
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        outputFiles.add(bw);
    }

    public void setLogLevel(LogLevel logLevel) {
        logLevelSet = logLevel;
    }

    public void logMessage(String message, LogLevel logLevel) {
        // Check if log level is lower than the one set. If so, execute the logging.
        if (logLevel.compareTo(logLevelSet) < 0) {
            executeLog(message, logLevel);
        }
        //TODO: throw wrong level Exception
    }

    private void writeInBuffer(BufferedWriter bw, String message) {
        try {
            bw.write(message);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeLog(String message, LogLevel logLevel) {
        String formattedMessage = logFormat.formatLogMessage(message, logLevel);
        if (terminalOutput) {
            System.out.print(formattedMessage);
        }
        for (BufferedWriter bw : outputFiles) {
            writeInBuffer(bw, formattedMessage);
        }
    }
    
    public void applyLogFormatProperty(String property, String fileValue) throws WrongPropertyFormatException{
    	try{
    		//TODO: implement exception in log format class
    		logFormat = new LogFormat(fileValue);
    	}catch(Exception e){
    		throw new WrongPropertyFormatException(e.getMessage());
    	}
    }
    
    public void applyLogLevelProperty(String property, String fileValue) throws WrongPropertyFormatException{
    	//TODO: log level factory probably needed here.
    }

    public void applyOutputFileProperty(String property, String fileValue) throws IOException{
    	addOutputFile(fileValue);
    }
         
    public void applyConsoleOutputProperty(String property, String fileValue) throws WrongPropertyFormatException{
    	if (fileValue.equalsIgnoreCase("true") || fileValue.equalsIgnoreCase("false")) {
    	    setConsoleOutput(Boolean.valueOf(fileValue));
    	} else {
    	   throw new WrongPropertyFormatException(fileValue + " is not a valid value for "+ property);
    	}
    }
    
	public void loadConfigFromFile(String filename) throws Exception {
		loggerPropertyLoader.loadConfigFromFile(filename);
	}
}
