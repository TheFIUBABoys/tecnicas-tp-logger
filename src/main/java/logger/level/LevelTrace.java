package logger.level;

/**
 * Created by GonchuB on 30/05/2014.
 * This class represents the TRACE logging logger.level. It
 * implements all comparing methods defined by the LogLevel
 * interface.
 */
public class LevelTrace implements LogLevel {

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
        return LogLevelComparisonResult.resultEqual;
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelDebug logLevel) {
        return LogLevelComparisonResult.resultLesser;
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelInfo logLevel) {
        return LogLevelComparisonResult.resultLesser;
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelWarn logLevel) {
        return LogLevelComparisonResult.resultLesser;
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelError logLevel) {
        return LogLevelComparisonResult.resultLesser;
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelFatal logLevel) {
        return LogLevelComparisonResult.resultLesser;
    }

    /**
     * {@inheritDoc}
     */
    public LogLevelComparisonResult compareToLevel(LevelOff logLevel) {
        return LogLevelComparisonResult.resultLesser;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "TRACE";
    }

}