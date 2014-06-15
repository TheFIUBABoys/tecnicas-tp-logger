package logger.format.strategy;

import logger.filters.FilterReplaceContainer;
import logger.filters.FilterReplaceContainerImpl;
import logger.filters.FormatFilterInterface;
import logger.level.LogLevel;

import java.util.ArrayList;

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
    public String formatMessage(ArrayList<FormatFilterInterface> filters, String message, LogLevel logLevel, String loggerName) {

        FilterReplaceContainer container = new FilterReplaceContainerImpl();
        container.setMessage(message);
        container.setLogLevel(logLevel.toString());
        container.setLoggerName(loggerName);
        container.setSeparator(separatorField);

        String replaced = formatString;

        for (FormatFilterInterface filter : filters) {
            replaced = filter.filter(replaced, container);
        }

        return replaced;
    }
}
