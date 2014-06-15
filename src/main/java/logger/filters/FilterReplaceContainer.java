package logger.filters;

/**
 * Created by GonchuB on 15/06/2014.
 * FIUBA
 */
public interface FilterReplaceContainer {

    public String getLoggerName();

    public String getMessage();

    public String getLogLevel();

    public String getSeparator();

    public void setLoggerName(String name);

    public void setMessage(String message);

    public void setLogLevel(String level);

    public void setSeparator(String separator);

}
