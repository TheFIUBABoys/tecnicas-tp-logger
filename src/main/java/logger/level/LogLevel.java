package logger.level;

/**
 * Created by GonchuB on 09/05/2014.
 * The responsibility of this class is to provide an interface
 * for the different types of logging levels.
 */


public interface LogLevel {

    /**
     * Easy access to debugging logger.level types.
     * Example of semantics: LogLevel.LEVEL_DEBUG
     */
    public static LogLevel LEVEL_TRACE = new LevelTrace();
    public static LogLevel LEVEL_DEBUG = new LevelDebug();
    public static LogLevel LEVEL_INFO = new LevelInfo();
    public static LogLevel LEVEL_WARN = new LevelWarn();
    public static LogLevel LEVEL_ERROR = new LevelError();
    public static LogLevel LEVEL_FATAL = new LevelFatal();
    public static LogLevel LEVEL_OFF = new LevelOff();

    /**
     * Implement compare to method.
     * thisOne.compareToLevel(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareToLevel(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareToLevel(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public LogLevelComparisonResult compareToLevel(LogLevel logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareToLevel(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareToLevel(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareToLevel(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public LogLevelComparisonResult compareToLevel(LevelTrace logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareToLevel(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareToLevel(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareToLevel(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public LogLevelComparisonResult compareToLevel(LevelDebug logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareToLevel(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareToLevel(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareToLevel(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public LogLevelComparisonResult compareToLevel(LevelInfo logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareToLevel(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareToLevel(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareToLevel(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public LogLevelComparisonResult compareToLevel(LevelWarn logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareToLevel(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareToLevel(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareToLevel(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public LogLevelComparisonResult compareToLevel(LevelError logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareToLevel(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareToLevel(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareToLevel(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public LogLevelComparisonResult compareToLevel(LevelFatal logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareToLevel(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareToLevel(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareToLevel(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public LogLevelComparisonResult compareToLevel(LevelOff logLevel);

    /**
     * String representation of the LogLevel.
     *
     * @return the string that represents the log logger.level.
     */
    public String toString();
}
