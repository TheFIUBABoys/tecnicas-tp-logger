package logger.format;

import logger.filters.FormatFilterInterface;
import logger.level.LogLevel;

/**
 * Created by gonchub on 13/05/14.
 * Interface for the LogFormat.
 */
public interface LogFormat {

    public static String JSON_STRATEGY = "json";
    public static String STRING_STRATEGY = "string";

    /**
     * Formats the received log message.
     *
     * @param message    the message to be logged.
     * @param level      the logging logger.level of the message.
     * @param loggerName the name of the logger that executes this.
     * @return the string with the formatted message (parameters in the format interpolated).
     */
    public String formatLogMessage(String message, LogLevel level, String loggerName);

    /**
     * Configures the end of line separator.
     *
     * @param newEol the new separator.
     */
    public void setEndOfLineSeparator(String newEol);

    /**
     * Sets the strategy to be used in the formatting.
     *
     * @param strategy the strategy to use.
     */
    public void setFormatStrategy(String strategy);

    /**
     * Add a new filter to the filter array.
     *
     * @param filter the filter to add.
     */
    public void addFilter(FormatFilterInterface filter);

}
