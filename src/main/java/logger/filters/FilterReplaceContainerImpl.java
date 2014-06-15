package logger.filters;

import logger.filters.FilterReplaceContainer;

/**
 * Created by GonchuB on 15/06/2014.
 * FIUBA
 */
public class FilterReplaceContainerImpl implements FilterReplaceContainer {

    public String message;
    public String loggerName;
    public String logLevel;
    public String separator;

    @Override
    public String getLoggerName() {
        return loggerName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getLogLevel() {
        return logLevel;
    }

    @Override
    public String getSeparator() {
        return separator;
    }

    @Override
    public void setLoggerName(String name) {
        this.loggerName = name;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setLogLevel(String level) {
        this.logLevel = level;
    }

    @Override
    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
