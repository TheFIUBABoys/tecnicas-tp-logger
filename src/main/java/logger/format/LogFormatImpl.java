package logger.format;

import logger.filters.*;
import logger.level.LogLevel;
import logger.exceptions.InvalidFormatException;

import java.util.Date;

/**
 * Created by GonchuB on 09/05/2014.
 * This class' responsibility is to format a message, given the
 * message and a format expression.
 */
public class LogFormatImpl implements LogFormat {

    private String formatString;
    private String separatorField = "\n";
    private String strategySet;

    /**
     * Creates a new LogFormatImpl instance with a default format.
     */
    public LogFormatImpl() {
        strategySet = LogFormat.STRING_STRATEGY;
        formatString = "%p - %m";
    }

    /**
     * Creates a new LogFormatImpl instance with the specified format.
     *
     * @param format the format string that will be used to format the messages.
     * @throws InvalidFormatException if the format received is not valid
     */
    public LogFormatImpl(String format) throws InvalidFormatException {
        if (!validFormat(format)) {
            throw new InvalidFormatException("Invalid format: " + format);
        }
        strategySet = LogFormat.STRING_STRATEGY;
        formatString = format;
    }

    /**
     * Validates the given format string.
     *
     * @param format the format to validate.
     * @return true if the format is valid, false if it isn't.
     */
    private Boolean validFormat(String format) {
        String copy = format.concat("");

        FormatFilterInterface messageFilter = new MessageFilter("");
        FormatFilterInterface loggerNameFilter = new LoggerNameFilter("");
        FormatFilterInterface levelFilter = new LevelFilter("");
        FormatFilterInterface separatorFilter = new SeparatorFilter();
        FormatFilterInterface dateFilter = new DateFilter();
        FormatFilterInterface threadFilter = new ThreadFilter();
        FormatFilterInterface percentFilter = new PercentFilter();

        copy = messageFilter.clear(copy);
        copy = loggerNameFilter.clear(copy);
        copy = levelFilter.clear(copy);
        copy = separatorFilter.clear(copy);
        copy = dateFilter.clear(copy);
        copy = threadFilter.clear(copy);
        copy = percentFilter.clear(copy);

        return copy.replaceAll("%", "").equals(copy);
    }

    /**
     * {@inheritDoc}
     */
    public String formatLogMessage(String message, LogLevel level) {
        String formattedMessage = null;
        if (strategySet.equals(LogFormat.STRING_STRATEGY)) {
            formattedMessage = formatLogMessageString(message, level);
        } else if (strategySet.equals(LogFormat.JSON_STRATEGY)) {
            formattedMessage = formatLogMessageJson(message, level, message);
        }
        return formattedMessage;
    }

    public String formatLogMessageString(String message, LogLevel level) {
        FormatFilterInterface messageFilter = new MessageFilter(message);
        FormatFilterInterface loggerNameFilter = new LoggerNameFilter(message);
        FormatFilterInterface levelFilter = new LevelFilter(level.toString());
        FormatFilterInterface separatorFilter = new SeparatorFilter(separatorField);
        FormatFilterInterface dateFilter = new DateFilter();
        FormatFilterInterface threadFilter = new ThreadFilter();
        FormatFilterInterface percentFilter = new PercentFilter();

        String replaced = formatString;

        replaced = messageFilter.filter(replaced);
        replaced = loggerNameFilter.filter(replaced);
        replaced = levelFilter.filter(replaced);
        replaced = separatorFilter.filter(replaced);
        replaced = dateFilter.filter(replaced);
        replaced = threadFilter.filter(replaced);
        replaced = percentFilter.filter(replaced);

        return replaced;
    }

    /**
     * {@inheritDoc}
     */
    public void setEndOfLineSeparator(String newEol) {
        separatorField = newEol;
    }

    public void setFormatStrategy(String strategy) {
        strategySet = strategy;
    }

    public String formatLogMessageJson(String message, LogLevel level, String loggerName) {
        LogContainer logMessage = new LogContainerImpl();
        logMessage.setDate(new Date());
        logMessage.setLoggerName(loggerName);
        logMessage.setLogLevel(level.toString());
        logMessage.setMessage(message);
        return logMessage.toJson();
    }

}
