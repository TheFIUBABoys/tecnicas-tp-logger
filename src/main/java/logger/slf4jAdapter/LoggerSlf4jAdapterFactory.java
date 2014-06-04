package logger.slf4jAdapter;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lucas on 5/31/2014.
 */
public class LoggerSlf4jAdapterFactory implements ILoggerFactory {
    private Map<String, Logger> loggerMap;

    public LoggerSlf4jAdapterFactory() {
        loggerMap = new HashMap<String, Logger>();
        loggerMap.put(LoggerSlf4jAdapter.class.getName(), new LoggerSlf4jAdapter());
    }

    @Override
    public org.slf4j.Logger getLogger(String s) {
        return loggerMap.get(s);
    }
}
