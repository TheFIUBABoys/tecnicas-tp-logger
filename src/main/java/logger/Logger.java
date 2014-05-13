package logger;

import level.LogLevel;

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
     * Logs the received message with the also received logging level
     * if the level is lower than the level set.
     *
     * @param message  the message that will be added after formatting.
     * @param logLevel the logging level of the message.
     */
    public void logMessage(String message, LogLevel logLevel);

    /**
     * Sets the logging level, which determines which type of messages
     * will be logged.
     *
     * @param logLevel the loging level to set.
     */
    public void setLogLevel(LogLevel logLevel);

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
     * @param filename the path of the file to log into.
     * @throws IOException if an error occurs during the opening of the file.
     */
    public void addOutputFile(String filename) throws IOException;

    /**
     * Loads the configuration from the given file.
     *
     * @param filename the filename of the configuration.
     * @throws Exception if an error occurred when configuring.
     */
    public void loadConfigFromFile(String filename) throws Exception;

}
