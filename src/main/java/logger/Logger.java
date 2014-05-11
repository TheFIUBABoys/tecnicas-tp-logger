package logger;

import level.*;
import loggerExceptions.*;

import java.io.*;
import java.util.*;

/**
 * Created by GonchuB on 09/05/2014.
 * This class represents the Logger singleton. It's
 * responsibility is do the actual logging of the messages
 * into files and or terminal. The configuration of the
 * Logger can be loaded from a Properties file.
 */
public class Logger implements PropertyApplyingDelegate {

    /**
     * Logger singleton instance.
     */
    private static Logger loggerInstance = null;

    private LogLevel logLevelSet;
    private LogFormat logFormat;
    private ArrayList<BufferedWriter> outputFiles;
    private Boolean terminalOutput;

    private LoggerPropertyLoader loggerPropertyLoader;

    /**
     * Private constructor to be called from the getLogger method.
     */
    private Logger() {
        logLevelSet = new LevelDebug();
        logFormat = new LogFormat();
        outputFiles = new ArrayList<BufferedWriter>();
        terminalOutput = true;
        loggerPropertyLoader = new LoggerPropertyLoader(this);
    }

    /**
     * Get the instance of the logger. If it doesn't exist, creates one.
     *
     * @return the Logger instance.
     */
    public static Logger getLogger() {
        if (loggerInstance == null) {
            loggerInstance = new Logger();
        }
        return loggerInstance;
    }

    /**
     * Resets the logger to its default state.
     */
    public void resetLogger() {
        for (BufferedWriter bw : outputFiles) {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loggerInstance = new Logger();
    }

    /**
     * Sets the format of the messages.
     *
     * @param messageFormat the format to be set.
     */
    public void setMessageFormat(LogFormat messageFormat) {
        logFormat = messageFormat;
    }

    /**
     * Sets whether the logger will or won't log into terminal.
     *
     * @param value true if the logger will log into terminal, false if it won't.
     */
    public void setConsoleOutput(Boolean value) {
        terminalOutput = value;
    }

    /**
     * Adds an output file to which the Logger will log.
     * Creates the file if it doesn't exist.
     *
     * @param filename the path of the file to log into.
     * @throws IOException if an error occurs during the opening of the file.
     */
    public void addOutputFile(String filename) throws IOException {
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        outputFiles.add(bw);
    }

    /**
     * Sets the logging level, which determines which type of messages
     * will be logged.
     *
     * @param logLevel the loging level to set.
     */
    public void setLogLevel(LogLevel logLevel) {
        logLevelSet = logLevel;
    }

    /**
     * Logs the received message with the also received logging level
     * if the level is lower than the level set.
     *
     * @param message  the message that will be added after formatting.
     * @param logLevel the logging level of the message.
     */
    public void logMessage(String message, LogLevel logLevel) {
        // Check if log level is lower than the one set. If so, execute the logging.
        if (logLevel.compareTo(logLevelSet) < 0) {
            executeLog(message, logLevel);
        }
        //TODO: throw wrong level Exception
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
     * @param logLevel the logging level of the message.
     */
    private void executeLog(String message, LogLevel logLevel) {
        String formattedMessage = logFormat.formatLogMessage(message, logLevel);
        if (terminalOutput) {
            System.out.print(formattedMessage);
        }
        for (BufferedWriter bw : outputFiles) {
            writeInBuffer(bw, formattedMessage);
        }
    }

    public void applyLogFormatProperty(String property, String fileValue) throws WrongPropertyFormatException {
        try {
            logFormat = new LogFormat(fileValue);
        } catch (InvalidFormatException e) {
            throw new WrongPropertyFormatException(e.getMessage());
        }
    }

    public void applyLogLevelProperty(String property, String fileValue) throws WrongPropertyFormatException {
        LogLevelFactory logLevelFactory = LogLevelFactory.getInstance();
        try {
            logLevelSet = logLevelFactory.createLogLevel(fileValue);
        } catch (NotExistingLevelException e) {
            e.printStackTrace();
        }
    }

    public void applyOutputFileProperty(String property, String fileValue) throws IOException {
        addOutputFile(fileValue);
    }

    public void applyConsoleOutputProperty(String property, String fileValue) throws WrongPropertyFormatException {
        if (fileValue.equalsIgnoreCase("true") || fileValue.equalsIgnoreCase("false")) {
            setConsoleOutput(Boolean.valueOf(fileValue));
        } else {
            throw new WrongPropertyFormatException(fileValue + " is not a valid value for " + property);
        }
    }

    public void loadConfigFromFile(String filename) throws Exception {
        loggerPropertyLoader.loadConfigFromFile(filename);
    }
}
