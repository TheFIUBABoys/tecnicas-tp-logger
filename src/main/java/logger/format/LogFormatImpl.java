package logger.format;

import logger.exceptions.InvalidFormatException;
import logger.filters.*;
import logger.format.strategy.FormatMessageStrategy;
import logger.format.strategy.JsonFormatStrategy;
import logger.format.strategy.StringFormatStrategy;
import logger.level.LogLevel;

import java.util.ArrayList;

/**
 * Created by GonchuB on 09/05/2014.
 * This class' responsibility is to format a message, given the
 * message and a format expression.
 */
public class LogFormatImpl implements LogFormat {

    private String formatString;
    private String separatorField = "\n";
    private String strategySet;
    private ArrayList<FormatFilterInterface> filters;

    /**
     * Creates a new LogFormatImpl instance with a default format.
     */
    public LogFormatImpl() {
        strategySet = LogFormat.STRING_STRATEGY;
        formatString = "%p - %m";
        generateFilters();
    }


    /**
     * {@inheritDoc}
     */
    public void addFilter(FormatFilterInterface filter) {
        filters.add(0, filter);
    }

    /**
     * Generate the defined filters. Add them to the filters list.
     */
    private void generateFilters() {
        filters = new ArrayList<FormatFilterInterface>();
        addFilter(new PercentFilter());
        addFilter(new ThreadFilter());
        addFilter(new DateFilter());
        addFilter(new SeparatorFilter());
        addFilter(new LevelFilter());
        addFilter(new LoggerNameFilter());
        addFilter(new MessageFilter());
    }

    /**
     * Creates a new LogFormatImpl instance with the specified format.
     *
     * @param format the format string that will be used to format the messages.
     * @throws InvalidFormatException if the format received is not valid
     */
    public LogFormatImpl(String format) throws InvalidFormatException {
        generateFilters();
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

        for (FormatFilterInterface filter : filters) {
            copy = filter.clear(copy);
        }

        return copy.replaceAll("%", "").equals(copy);
    }

    /**
     * {@inheritDoc}
     */
    public String formatLogMessage(String message, LogLevel level, String loggerName) {
        String formattedMessage;
        FormatMessageStrategy strategy = new StringFormatStrategy(formatString, separatorField);

        if (strategySet.equals(LogFormat.STRING_STRATEGY)) {
            strategy = new StringFormatStrategy(formatString, separatorField);
        } else if (strategySet.equals(LogFormat.JSON_STRATEGY)) {
            strategy = new JsonFormatStrategy();
        }
        formattedMessage = strategy.formatMessage(filters, message, level, loggerName);
        return formattedMessage;
    }

    /**
     * {@inheritDoc}
     */
    public void setEndOfLineSeparator(String newEol) {
        separatorField = newEol;
    }

    /**
     * {@inheritDoc}
     */
    public void setFormatStrategy(String strategy) {
        strategySet = strategy;
    }

}
