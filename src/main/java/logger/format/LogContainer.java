package logger.format;

import java.util.Date;

/**
 * Created by GonchuB on 31/05/2014.
 * FIUBA
 */
public interface LogContainer {

    /**
     * Sets the date and time of the logging.
     *
     * @param date the date to set.
     */
    public void setDate(Date date);

    /**
     * Sets the name of the logger.
     *
     * @param name the name of the logger to set.
     */
    public void setLoggerName(String name);

    /**
     * Sets the log level.
     *
     * @param logLevel the log level to set.
     */
    public void setLogLevel(String logLevel);

    /**
     * Sets the message.
     *
     * @param message the message to set.
     */
    public void setMessage(String message);

    /**
     * Gets the date that was set.
     *
     * @return the set date.
     */
    public String getDate();

    /**
     * Gets the logger name that was set.
     *
     * @return the set logger name.
     */
    public String getLoggerName();

    /**
     * Gets the log level that was set.
     *
     * @return the set log level.
     */
    public String getLogLevel();

    /**
     * Gets the message that was set.
     *
     * @return the set message.
     */
    public String getMessage();

    /**
     * Returns a JSON string representation of the object.
     *
     * @return a JSON string.
     */
    public String toJson();

}
