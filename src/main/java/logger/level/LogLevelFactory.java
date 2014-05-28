package logger.level;

import loggerExceptions.NotExistingLevelException;

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

        LogLevel debugInstance = new LevelDebug();
        LogLevel infoInstance = new LevelInfo();
        LogLevel warnInstance = new LevelWarn();
        LogLevel errorInstance = new LevelError();
        LogLevel fatalInstance = new LevelFatal();
        LogLevel offInstance = new LevelOff();

        if (level.equals(debugInstance.toString())) {
            return debugInstance;
        } else if (level.equals(infoInstance.toString())) {
            return infoInstance;
        } else if (level.equals(warnInstance.toString())) {
            return warnInstance;
        } else if (level.equals(errorInstance.toString())) {
            return errorInstance;
        } else if (level.equals(fatalInstance.toString())) {
            return fatalInstance;
        } else if (level.equals(offInstance.toString())) {
            return offInstance;
        } else {
            throw new NotExistingLevelException();
        }

    }

}
