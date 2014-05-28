package logger;

import logger.level.LogLevel;

/**
 * Created by gonchub on 13/05/14.
 * Interface for the LogFormat.
 */
public interface LogFormat {

    /**
     * Formats the received log message.
     *
     * @param message the message to be logged.
     * @param level   the logging logger.level of the message.
     * @return the string with the formatted message (parameters in the format interpolated).
     */
    public String formatLogMessage(String message, LogLevel level);

    /**
     * Configures the end of line separator.
     *
     * @param newEol the new separator.
     */
    public void setEndOfLineSeparator(String newEol);


}
