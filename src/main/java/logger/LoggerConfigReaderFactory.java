package logger;

/**
 * Created by Tomas on 30/05/2014.
 */
public class LoggerConfigReaderFactory {

    private static LoggerConfigReaderFactory instance = null ;

    private LoggerConfigReaderFactory() {

    }

    public static LoggerConfigReaderFactory getInstace () {
        if (instance == null){
            instance = new LoggerConfigReaderFactory();
        }
        return instance;
    }

    public LoggerConfigReader getReader (String filename) {
        return null;
    }
}
