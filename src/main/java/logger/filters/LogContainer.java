package logger.filters;

import logger.level.LogLevel;

import java.util.Date;

/**
 * Created by GonchuB on 31/05/2014.
 * FIUBA
 */
public interface LogContainer {

    public void setDate(Date date);

    public void setLoggerName(String name);

    public void setLogLevel(String logLevel);

    public void setMessage(String message);

    public String toJson();

}
