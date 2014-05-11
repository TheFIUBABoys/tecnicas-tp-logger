package level;

/**
 * Created by GonchuB on 09/05/2014.
 * FIUBA
 */
public abstract class LogLevel implements Comparable<LogLevel> {

    public static LogLevel LEVEL_DEBUG = new LevelDebug();
    public static LogLevel LEVEL_INFO = new LevelInfo();
    public static LogLevel LEVEL_WARN = new LevelWarn();
    public static LogLevel LEVEL_ERROR = new LevelError();
    public static LogLevel LEVEL_FATAL = new LevelFatal();
    public static LogLevel LEVEL_OFF = new LevelOff();

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) > 0, thisOne > otherOne
     * thisOne.compareTo(otherOne) < 0, thisOne < otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates <, > or ==.
     */
    public abstract int compareTo(LogLevel logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) > 0, thisOne > otherOne
     * thisOne.compareTo(otherOne) < 0, thisOne < otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates <, > or ==.
     */
    public abstract int compareTo(LevelDebug logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) > 0, thisOne > otherOne
     * thisOne.compareTo(otherOne) < 0, thisOne < otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates <, > or ==.
     */
    public abstract int compareTo(LevelInfo logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) > 0, thisOne > otherOne
     * thisOne.compareTo(otherOne) < 0, thisOne < otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates <, > or ==.
     */
    public abstract int compareTo(LevelWarn logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) > 0, thisOne > otherOne
     * thisOne.compareTo(otherOne) < 0, thisOne < otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates <, > or ==.
     */
    public abstract int compareTo(LevelError logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) > 0, thisOne > otherOne
     * thisOne.compareTo(otherOne) < 0, thisOne < otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates <, > or ==.
     */
    public abstract int compareTo(LevelFatal logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) > 0, thisOne > otherOne
     * thisOne.compareTo(otherOne) < 0, thisOne < otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates <, > or ==.
     */
    public abstract int compareTo(LevelOff logLevel);

    /**
     * String representation of the LogLevel.
     *
     * @return the string that represents the log level.
     */
    public abstract String toString();
}
