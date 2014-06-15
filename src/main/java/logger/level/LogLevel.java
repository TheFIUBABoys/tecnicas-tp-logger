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

    public String toString();
}
