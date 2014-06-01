package logger.format.strategy;

import logger.level.LogLevel;

/**
 * Created by GonchuB on 01/06/2014.
 * FIUBA
 */
public interface FormatMessageStrategy {

    /**
     * Formats the log message and returns a string representation of it.
     *
     * @param message    the message of the log.
     * @param logLevel   the level of logging.
     * @param loggerName the name of the logger.
     * @return a string representation of the formatted log message.
     */
    public String formatMessage(String message, LogLevel logLevel, String loggerName);

}
