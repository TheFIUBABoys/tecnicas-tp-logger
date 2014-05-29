package logger.level;

/**
 * Created by GonchuB on 09/05/2014.
 * The responsibility of this class is to provide an interface
 * for the different types of logging levels.
 */
public interface LogLevel extends Comparable<LogLevel> {

    /**
     * Easy access to debugging logger.level types.
     * Example of semantics: LogLevel.LEVEL_DEBUG
     */
    public static LogLevel LEVEL_DEBUG = new LevelDebug();
    public static LogLevel LEVEL_INFO = new LevelInfo();
    public static LogLevel LEVEL_WARN = new LevelWarn();
    public static LogLevel LEVEL_ERROR = new LevelError();
    public static LogLevel LEVEL_FATAL = new LevelFatal();
    public static LogLevel LEVEL_OFF = new LevelOff();

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareTo(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public int compareTo(LogLevel logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareTo(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public int compareTo(LevelDebug logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareTo(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public int compareTo(LevelInfo logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareTo(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public int compareTo(LevelWarn logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareTo(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public int compareTo(LevelError logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareTo(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public int compareTo(LevelFatal logLevel);

    /**
     * Implement compare to method.
     * thisOne.compareTo(otherOne) &gt; 0, thisOne &gt; otherOne
     * thisOne.compareTo(otherOne) &lt; 0, thisOne &lt; otherOne
     * thisOne.compareTo(otherOne) == 0, thisOne == otherOne
     *
     * @param logLevel the logLevel to compare to.
     * @return an integer that indicates &lt;, &gt; or ==.
     */
    public int compareTo(LevelOff logLevel);

    /**
     * String representation of the LogLevel.
     *
     * @return the string that represents the log logger.level.
     */
    public String toString();
}
