package logger.format.strategy;

import logger.filters.*;
import logger.level.LogLevel;

/**
 * Created by GonchuB on 01/06/2014.
 * FIUBA
 */
public class StringFormatStrategy implements FormatMessageStrategy {

    private String formatString;
    private String separatorField;

    /**
     * Constructor needed to set formatting variables.
     *
     * @param formatString   the format string to interpolate.
     * @param separatorField the eol separator.
     */
    public StringFormatStrategy(String formatString, String separatorField) {
        this.formatString = formatString;
        this.separatorField = separatorField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String formatMessage(String message, LogLevel logLevel, String loggerName) {
        FormatFilterInterface messageFilter = new MessageFilter(message);
        FormatFilterInterface loggerNameFilter = new LoggerNameFilter(loggerName);
        FormatFilterInterface levelFilter = new LevelFilter(logLevel.toString());
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
}
