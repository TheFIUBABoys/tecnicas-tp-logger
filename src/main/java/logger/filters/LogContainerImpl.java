package logger.filters;

import com.google.gson.Gson;
import logger.level.LogLevel;

import java.util.Date;

/**
 * Created by GonchuB on 31/05/2014.
 * FIUBA
 */
public class LogContainerImpl implements LogContainer {

    private Date datetime;
    private String logger;
    private LogLevel level;
    private String message;


    @Override
    public void setDate(Date date) {
        this.datetime = date;
    }

    @Override
    public void setLoggerName(String name) {
        this.logger = name;
    }

    @Override
    public void setLogLevel(LogLevel logLevel) {
        this.level = logLevel;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
