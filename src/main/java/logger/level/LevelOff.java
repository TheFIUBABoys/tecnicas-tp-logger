package logger.level;

/**
 * Created by GonchuB on 09/05/2014.
 * This class represents the OFF logging logger.level. It
 * implements all comparing methods defined by the LogLevel
 * interface.
 */
public class LevelOff implements LogLevel {

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LogLevel logLevel) {
        return logLevel.compareToLevel(this);
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelTrace logLevel) {
        return LogLevelComparisonResult.resultGreater;
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelDebug logLevel) {
        return LogLevelComparisonResult.resultGreater;
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelInfo logLevel) {
        return LogLevelComparisonResult.resultGreater;
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelWarn logLevel) {
        return LogLevelComparisonResult.resultGreater;
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelError logLevel) {
        return LogLevelComparisonResult.resultGreater;
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelFatal logLevel) {
        return LogLevelComparisonResult.resultGreater;
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelOff logLevel) {
        return LogLevelComparisonResult.resultEqual;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "OFF";
    }
}
