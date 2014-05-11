package level;

import loggerExceptions.NotExistingLevelException;

/**
 * Created by Usuario on 11/05/2014.
 */
public class LogLevelFactory {

    private static LogLevelFactory instance = null;

    public static LogLevelFactory getInstance() {
        if (instance == null){
            instance = new LogLevelFactory();
        }
        return instance;
    }

    private LogLevelFactory() {};

    public LogLevel createLogLevel(String level) throws NotExistingLevelException {
        if (level.equals("DEBUG")) {
            return new LevelDebug();
        } else if (level.equals("INFO")) {
            return new LevelInfo();
        } else if (level.equals("WARN")) {
            return new LevelWarn();
        } else if (level.equals("ERROR")) {
            return new LevelError();
        } else if (level.equals("FATAL")) {
            return new LevelFatal();
        } else if (level.equals("OFF")) {
            return new LevelOff();
        } else {
            throw new NotExistingLevelException();
        }

    }

}
