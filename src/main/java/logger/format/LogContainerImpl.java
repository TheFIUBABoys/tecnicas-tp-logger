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


    @Override
    public void setDate(Date date) {
        this.datetime = date.toString();
    }

    @Override
    public void setLoggerName(String name) {
        this.logger = name;
    }

    @Override
    public void setLogLevel(String logLevel) {
        this.level = logLevel;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getDate() {
        return datetime;
    }

    @Override
    public String getLoggerName() {
        return logger;
    }

    @Override
    public String getLogLevel() {
        return level;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
