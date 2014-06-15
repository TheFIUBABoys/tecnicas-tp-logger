package logger.format.strategy;

import logger.filters.FormatFilterInterface;
import logger.format.LogContainer;
import logger.format.LogContainerImpl;
import logger.level.LogLevel;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by GonchuB on 01/06/2014.
 * FIUBA
 */
public class JsonFormatStrategy implements FormatMessageStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public String formatMessage(ArrayList<FormatFilterInterface> filters, String message, LogLevel logLevel, String loggerName) {
        LogContainer logMessage = new LogContainerImpl();
        logMessage.setDate(new Date());
        logMessage.setLoggerName(loggerName);
        logMessage.setLogLevel(logLevel.toString());
        logMessage.setMessage(message);
        return logMessage.toJson();
    }

}
