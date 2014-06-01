package logger.format;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by GonchuB on 31/05/2014.
 * FIUBA
 */
public class LogContainerImpl implements LogContainer {

    private String datetime;
    private String logger;
    private String level;
    private String message;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDate(Date date) {
        this.datetime = date.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoggerName(String name) {
        this.logger = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLogLevel(String logLevel) {
        this.level = logLevel;
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
    public String getDate() {
        return datetime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLoggerName() {
        return logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLogLevel() {
        return level;
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
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
