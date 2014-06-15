package logger.filters;

/**
 * Created by GonchuB on 15/06/2014.
 * FIUBA
 */
public class FilterReplaceContainerImpl implements FilterReplaceContainer {

    public String message;
    public String loggerName;
    public String logLevel;
    public String separator;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLoggerName() {
        return loggerName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLogLevel() {
        return logLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSeparator() {
        return separator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoggerName(String name) {
        this.loggerName = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogLevel(String level) {
        this.logLevel = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
