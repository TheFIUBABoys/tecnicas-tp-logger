package logger.filters;

/**
 * Created by GonchuB on 15/06/2014.
 * FIUBA
 */
public interface FilterReplaceContainer {

    /**
     * Get logger name
     * @return logger name set.
     */
    public String getLoggerName();

    /**
     * Get message.
     * @return the message set.
     */
    public String getMessage();

    /**
     * Get the log level.
     * @return the log level set.
     */
    public String getLogLevel();

    /**
     * Get the separator.
     * @return the separator set.
     */
    public String getSeparator();

    /**
     * Set the logger name.
     * @param name the name to set.
     */
    public void setLoggerName(String name);

    /**
     * Set the message.
     * @param message the message to set.
     */
    public void setMessage(String message);

    /**
     * Set the log level.
     * @param level the log level to set.
     */
    public void setLogLevel(String level);

    /**
     * Set the separator.
     * @param separator the separator to set.
     */
    public void setSeparator(String separator);

}
