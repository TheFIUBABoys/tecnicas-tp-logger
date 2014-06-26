package logger.level;

import logger.exceptions.NotExistingLevelException;

/**
 * Created by Usuario on 11/05/2014.
 * This class' responsibility is to create instances of LogLevel (it's descendants).
 */
public class LogLevelFactory {

    private static LogLevelFactory instance = null;

    /**
     * Static method to obtain singleton instance.
     *
     * @return the LogLevelFactory instance.
     */
    public static LogLevelFactory getInstance() {
        if (instance == null) {
            instance = new LogLevelFactory();
        }
        return instance;
    }

    /**
     * Private constructor called by getInstance.
     */
    private LogLevelFactory() {
    }

    /**
     * Creates a log logger.level that matches the given string.
     *
     * @param level the string that represents the log logger.level to be created.
     * @return the LogLevel that was created.
     * @throws NotExistingLevelException if a string that does not match any LogLevel is provided.
     */
    public LogLevel createLogLevel(String level) throws NotExistingLevelException {

        if (level.equals(LogLevel.LEVEL_TRACE.toString())) {
            return LogLevel.LEVEL_TRACE;
        } else if (level.equals(LogLevel.LEVEL_DEBUG.toString())) {
            return LogLevel.LEVEL_DEBUG;
        } else if (level.equals(LogLevel.LEVEL_INFO.toString())) {
            return LogLevel.LEVEL_INFO;
        } else if (level.equals(LogLevel.LEVEL_WARN.toString())) {
            return LogLevel.LEVEL_WARN;
        } else if (level.equals(LogLevel.LEVEL_ERROR.toString())) {
            return LogLevel.LEVEL_ERROR;
        } else if (level.equals(LogLevel.LEVEL_FATAL.toString())) {
            return LogLevel.LEVEL_FATAL;
        } else if (level.equals(LogLevel.LEVEL_OFF.toString())) {
            return LogLevel.LEVEL_OFF;
        } else {
            throw new NotExistingLevelException();
        }

    }

}

