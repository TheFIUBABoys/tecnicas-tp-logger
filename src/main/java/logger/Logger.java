package logger;

import logger.filters.custom.UserFilter;
import logger.format.LogFormat;
import logger.level.LogLevel;
import logger.writer.Writer;

import java.io.IOException;

/**
 * Created by gonchub on 13/05/14.
 * FIUBA.
 * Interface for the Logger.
 */
public interface Logger {

    /**
     * Sets the format of the messages.
     *
     * @param messageFormat the format to be set.
     */
    public void setMessageFormat(LogFormat messageFormat);

    /**
     * Logs the received message with the also received logging logger.level
     * if the logger.level is lower than the logger.level set.
     *
     * @param message  the message that will be added after formatting.
     * @param logLevel the logging logger.level of the message.
     */
    public void logMessage(String message, LogLevel logLevel);

    /**
     * Logs the received message with the also received logging logger.level
     * if the logger.level is lower than the logger.level set. After logging,
     * throws the exception.
     *
     * @param message   the message that will be added after formatting.
     * @param logLevel  the logging logger.level of the message.
     * @param exception logs and throws this exception.
     */
    public void logMessage(String message, LogLevel logLevel, Throwable exception);

    /**
     * Logs a message with a given logging logger.level
     * The message supports formatting of objects passed through ..objects
     *
     * @param message  the message that will be added after formatting.
     * @param logLevel the logging logger.level of the message.
     * @param objects  the objects to be formatted.
     */
    public void logMessage(String message, LogLevel logLevel, Object... objects);

    /**
     * Sets the logging logger.level, which determines which type of messages
     * will be logged.
     *
     * @param logLevel the loging logger.level to set.
     */
    public void setLogLevel(LogLevel logLevel);


    /**
     * Gets the logging logger.level, which determines which type of messages
     * will be logged.
     */
    public LogLevel getLogLevel();


    /**
     * Sets whether the logger will or won't log into terminal.
     *
     * @param value true if the logger will log into terminal, false if it won't.
     */
    public void setConsoleOutput(Boolean value);

    /**
     * Adds an output file to which the LoggerImpl will log.
     * Creates the file if it doesn't exist.
     *
     * @param writer the path of the file to log into.
     * @throws IOException if an error occurs during the opening of the file.
     */
    public void addOutput(Writer writer) throws IOException;

    /**
     * Adds a user defined filter.
     *
     * @param filter the filter ot be added.
     */
    public void addFilter(UserFilter filter);

    /**
     * Configures the logger.
     *
     * @throws Exception if something goes wrong while reading configuration files.
     */
    public void loadConfig() throws Exception;

}
