package logger;

import logger.level.LevelDebug;
import logger.level.LogLevel;
import logger.level.LogLevelFactory;
import logger.writer.ConsoleWriter;
import logger.writer.FileWriter;
import logger.writer.Writer;
import logger.exceptions.InvalidFormatException;
import logger.exceptions.NotExistingLevelException;
import logger.exceptions.WrongPropertyFormatException;

import java.io.IOException;
import java.util.HashMap;

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
    private static HashMap<String, Logger> loggers = new HashMap<String, Logger>();

    private LogLevel logLevelSet;
    private LogFormat logFormat;
    private HashMap<String, Writer> outputWriters;
    private LoggerConfigReader configReader;

    /**
     * Private constructor to be called from the getLogger method.
     */
    private LoggerImpl() {
        logLevelSet = new LevelDebug();
        logFormat = new LogFormatImpl();
        outputWriters = new HashMap<String, Writer>();
        configReader = new LoggerPropertyReader(this);
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
     * Get the instance of the logger required by name. If it doesn't exist, creates one.
     *
     * @return the LoggerImpl instance.
     */
    public static Logger getLogger(String loggerName) {
        if (loggers.get(loggerName) == null) {
            loggers.put(loggerName, new LoggerImpl());
        }
        return loggers.get(loggerName);
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
        if (!outputWriters.containsKey(ConsoleWriter.FILENAME)) {
            outputWriters.put(ConsoleWriter.FILENAME, new ConsoleWriter());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void addOutputFile(String filename) throws IOException {
        if (!outputWriters.containsKey(filename)) {
            outputWriters.put(filename, new FileWriter(filename));
        }
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
     * Executes the logging of the message into terminal (if defined) and
     * all the output files.
     *
     * @param message  the message that will be added after formatting.
     * @param logLevel the logging logger.level of the message.
     */
    private void executeLog(String message, LogLevel logLevel) {
        String formattedMessage = logFormat.formatLogMessage(message, logLevel);
        for (Writer w : outputWriters.values()) {
            w.write(formattedMessage);
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
        configReader.loadConfigFromFile(filename);
    }
}
