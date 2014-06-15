package logger;

import logger.config.LoggerConfigReader;
import logger.config.LoggerConfigReaderFactory;
import logger.config.PropertyApplyingDelegate;
import logger.exceptions.InvalidFormatException;
import logger.exceptions.NotExistingLevelException;
import logger.exceptions.WrongPropertyFormatException;
import logger.filters.custom.UserFilter;
import logger.format.LogContainer;
import logger.format.LogContainerImpl;
import logger.format.LogFormat;
import logger.format.LogFormatImpl;
import logger.level.*;
import logger.writer.ConsoleWriter;
import logger.writer.FileWriter;
import logger.writer.Writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    private LevelComparator comparator = LevelComparator.getInstance();
    private LogLevel logLevelSet;
    private LogFormat logFormat;
    private HashMap<String, Writer> outputWriters;
    private static Integer outputCount = 0;
    private LoggerConfigReader configReader;
    private ArrayList<UserFilter> filters;
    private String loggerName;

    /**
     * Private constructor to be called from the getLogger method.
     */
    private LoggerImpl() {
        logLevelSet = new LevelDebug();
        logFormat = new LogFormatImpl();
        outputWriters = new HashMap<String, Writer>();
        filters = new ArrayList<UserFilter>();
        LoggerConfigReaderFactory factory = LoggerConfigReaderFactory.getInstance();
        configReader = factory.getReaderFor(this);
        try {
            configReader.loadConfig();
        } catch (Exception e) {
            configReader = factory.getDefaultReaderFor(this);
            try {
                configReader.loadConfig();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private LoggerImpl(String name) {
        this();
        this.loggerName = name;
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
     * @param loggerName name of the required logger
     * @return the LoggerImpl instance.
     */
    public static Logger getLogger(String loggerName) {
        if (loggers.get(loggerName) == null) {
            loggers.put(loggerName, new LoggerImpl(loggerName));
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
     *
     * @param writer is the Object that represents an Output
     */
    public void addOutput(Writer writer) throws IOException {
        outputWriters.put(outputCount.toString(), writer);
        outputCount += 1;
    }


    /**
     * {@inheritDoc}
     */
    public void addFilter(UserFilter filter) {
        filters.add(filter);
    }

    /**
     * Checks if the log matches any of the user set filters.
     *
     * @param log the log to test against the filters.
     * @return True if it matches any of them, False otherwise.
     */
    private Boolean matchesAnyFilter(LogContainer log) {
        for (UserFilter filter : filters) {
            if (filter.matchesFilter(log)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void loadConfig() throws Exception {
        configReader.loadConfig();
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
    @Override
    public LogLevel getLogLevel() {
        return this.logLevelSet;
    }

    /**
     * If the log meets the requiriements returns True, otherwise return false.
     *
     * @param message  the message to log.
     * @param logLevel the log level of the messge.
     * @return True if the logging was done, False otherwise.
     */
    private Boolean shouldBeLogged(String message, LogLevel logLevel) {
        // Check if log logger.level is lower than the one set. If so, execute the logging.
        if (comparator.compareLevelToLevel(logLevelSet,logLevel)!=LogLevelComparisonResult.resultGreater){
            LogContainer log = new LogContainerImpl();
            log.setMessage(message);
            log.setDate(new Date());
            log.setLogLevel(logLevel.toString());
            return !matchesAnyFilter(log);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void logMessage(String message, LogLevel logLevel) {
        if (shouldBeLogged(message, logLevel)) {
            executeLog(message, logLevel);
        }
    }


    /**
     * {@inheritDoc}
     */
    public void logMessage(String message, LogLevel logLevel, Throwable exception) {
        message = message + ". Exception: " + exception.getMessage() + ". Due to: " + exception.getCause();
        logMessage(message, logLevel);
    }

    /**
     * Executes the logging of the message into terminal (if defined) and
     * all the output files.
     *
     * @param message  the message that will be added after formatting.
     * @param logLevel the logging logger.level of the message.
     */
    private void executeLog(String message, LogLevel logLevel) {
        String formattedMessage = logFormat.formatLogMessage(message, logLevel, loggerName);
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
        addOutput(new FileWriter(fileValue));
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

}
