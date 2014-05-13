package logger;

import level.LogLevel;
import logger.filters.*;
import loggerExceptions.InvalidFormatException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by GonchuB on 09/05/2014.
 * This class' responsibility is to format a message, given the
 * message and a format expression.
 */
public class LogFormatImpl implements LogFormat {

    private String formatString;
    private String defaultFormat = "%p - %m";
    private String separatorField = "\n";
    private ArrayList<FormatFilter> filters;

    /**
     * Regex and patterns to interpolate the format string.
     */
    private final String levelRegex = "%p";
    private final String messageRegex = "%m";

    /**
     * Creates a new LogFormatImpl instance with a default format.
     */
    public LogFormatImpl() {
        formatString = defaultFormat;
    }

    /**
     * Creates a new LogFormatImpl instance with the specified format.
     *
     * @param format the format string that will be used to format the messages.
     */
    public LogFormatImpl(String format) throws InvalidFormatException {
        if (!validFormat(format)) {
            throw new InvalidFormatException("Invalid format: " + format);
        }
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

        FormatFilter dateFilter = new DateFilter();
        FormatFilter separatorFilter = new SeparatorFilter(separatorField);
        FormatFilter threadFilter = new ThreadFilter();
        FormatFilter percentFilter = new PercentFilter();

        copy = copy.replaceAll(levelRegex, "");
        copy = copy.replaceAll(messageRegex, "");

        copy = dateFilter.clear(copy);
        copy = separatorFilter.clear(copy);
        copy = threadFilter.clear(copy);
        copy = percentFilter.clear(copy);

        return copy.replaceAll("%", "").equals(copy);
    }

    /**
     * {@inheritDoc}
     */
    public String formatLogMessage(String message, LogLevel level) {

        FormatFilter dateFilter = new DateFilter();
        FormatFilter separatorFilter = new SeparatorFilter(separatorField);
        FormatFilter threadFilter = new ThreadFilter();
        FormatFilter percentFilter = new PercentFilter();

        String replaced = formatString;

        // These should be received.
        replaced = replaced.replaceAll(levelRegex, level.toString());
        replaced = replaced.replaceAll(messageRegex, message);

        replaced = dateFilter.filter(replaced);
        replaced = separatorFilter.filter(replaced);
        replaced = threadFilter.filter(replaced);
        replaced = percentFilter.filter(replaced);

        return replaced;
    }
}
