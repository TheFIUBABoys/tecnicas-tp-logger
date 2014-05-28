package logger;

import logger.level.LevelDebug;
import logger.level.LogLevel;
import logger.level.LogLevelFactory;
import loggerExceptions.InvalidFormatException;
import loggerExceptions.NotExistingLevelException;
import loggerExceptions.WrongPropertyFormatException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by GonchuB on 09/05/2014.
 * This class represents the Logger singleton. It's
 * responsibility is doing the actual logging of the messages
 * into files and or terminal. The configuration of the
 * LoggerImpl can be loaded from a Properties file.
 */
public class LoggerImpl implements Logger, PropertyApplyingDelegate {

    /**
     * LoggerImpl singleton instance.
     */
    private static Logger loggerInstance = null;

    private LogLevel logLevelSet;
    private LogFormat logFormat;
    private HashMap<String,BufferedWriter> outputFiles;
    private Boolean terminalOutput;

    private LoggerPropertyLoader loggerPropertyLoader;

    /**
     * Private constructor to be called from the getLogger method.
     */
    private LoggerImpl() {
        logLevelSet = new LevelDebug();
        logFormat = new LogFormatImpl();
        outputFiles = new HashMap<String,BufferedWriter>();
        terminalOutput = true;
        loggerPropertyLoader = new LoggerPropertyLoader(this);
    }

    /**
     * Get the instance of the logger. If it doesn't exist, creates one.
     *
     * @return the LoggerImpl instance.
     */
    public static Logger getLogger() {
        if (loggerInstance == null) {
            loggerInstance = new LoggerImpl();
        }
        return loggerInstance;
    }

    /**
     * {@inheritDoc}
     */
    public void setMessageFormat(LogFormat messageFormat) {
        logFormat = messageFormat;
    }

    /**
     * {@inheritDoc}
     */
    public void setConsoleOutput(Boolean value) {
        terminalOutput = value;
    }

    /**
     * {@inheritDoc}
     */
    public void addOutputFile(String filename) throws IOException {
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        if (!outputFiles.containsValue(filename))
            outputFiles.put(filename,bw);
    }

    /**
     * {@inheritDoc}
     */
    public void setLogLevel(LogLevel logLevel) {
        logLevelSet = logLevel;
    }

    /**
     * {@inheritDoc}
     */
    public void logMessage(String message, LogLevel logLevel) {
        // Check if log logger.level is lower than the one set. If so, execute the logging.
        if (logLevel.compareTo(logLevelSet) < 0) {
            executeLog(message, logLevel);
        }
    }

    /**
     * Writes the message in the received BufferedWriter and flushes it.
     *
     * @param bw      the buffered writer where the message will be written.
     * @param message the message to write.
     */
    private void writeInBuffer(BufferedWriter bw, String message) {
        try {
            bw.write(message);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes the logging of the message into terminal (if defined) and
     * all the output files.
     *
     * @param message  the message that will be added after formatting.
     * @param logLevel the logging logger.level of the message.
     */
    private void executeLog(String message, LogLevel logLevel) {
        String formattedMessage = logFormat.formatLogMessage(message, logLevel);
        if (terminalOutput) {
            System.out.print(formattedMessage);
        }
        for (BufferedWriter bw : outputFiles.values()) {
            writeInBuffer(bw, formattedMessage);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void applyLogFormatProperty(String property, String fileValue) throws WrongPropertyFormatException {
        try {
            logFormat = new LogFormatImpl(fileValue);
        } catch (InvalidFormatException e) {
            throw new WrongPropertyFormatException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void applyLogLevelProperty(String property, String fileValue) throws WrongPropertyFormatException {
        LogLevelFactory logLevelFactory = LogLevelFactory.getInstance();
        try {
            logLevelSet = logLevelFactory.createLogLevel(fileValue);
        } catch (NotExistingLevelException e) {
            throw new WrongPropertyFormatException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void applyOutputFileProperty(String property, String fileValue) throws IOException {
        addOutputFile(fileValue);
    }

    /**
     * {@inheritDoc}
     */
    public void applyConsoleOutputProperty(String property, String fileValue) throws WrongPropertyFormatException {
        if (fileValue.equalsIgnoreCase("true") || fileValue.equalsIgnoreCase("false")) {
            setConsoleOutput(Boolean.valueOf(fileValue));
        } else {
            throw new WrongPropertyFormatException(fileValue + " is not a valid value for " + property);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void loadConfigFromFile(String filename) throws Exception {
        loggerPropertyLoader.loadConfigFromFile(filename);
    }
}
