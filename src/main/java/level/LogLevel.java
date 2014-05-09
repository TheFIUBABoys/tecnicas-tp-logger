package level;

import com.sun.istack.internal.NotNull;

/**
 * Created by GonchuB on 09/05/2014.
 */
public abstract class LogLevel implements Comparable<LogLevel> {

    public abstract int compareTo(LogLevel logLevel);

    public abstract int compareTo(LevelDebug logLevel);

    public abstract int compareTo(LevelInfo logLevel);

    public abstract int compareTo(LevelWarn logLevel);

    public abstract int compareTo(LevelError logLevel);

    public abstract int compareTo(LevelFatal logLevel);

    public abstract int compareTo(LevelOff logLevel);
}
