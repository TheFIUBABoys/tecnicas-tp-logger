package logger;

import level.LogLevel;
import loggerExceptions.InvalidFormatException;

import java.text.SimpleDateFormat;
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

    /**
     * Regex and patterns to interpolate the format string.
     */
    private final String dateRegex = "%d\\{[^\\}]*\\}";
    private final Pattern datePattern = Pattern.compile(dateRegex);
    private final String levelRegex = "%p";
    private final String threadRegex = "%t";
    private final String messageRegex = "%m";
    private final String percentRegex = "%%";
    private final String separatorRegex = "%n";
    private final String lineRegex = "%L";
    private final String filenameRegex = "%F";
    private final String methodRegex = "%M";

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
        copy = copy.replaceAll(levelRegex, "");
        copy = copy.replaceAll(dateRegex, "");
        copy = copy.replaceAll(threadRegex, "");
        copy = copy.replaceAll(messageRegex, "");
        copy = copy.replaceAll(separatorRegex, "");
        copy = copy.replaceAll(lineRegex, "");
        copy = copy.replaceAll(filenameRegex, "");
        copy = copy.replaceAll(methodRegex, "");
        copy = copy.replaceAll(percentRegex, "");
        return copy.replaceAll("%", "").equals(copy);
    }

    /**
     * {@inheritDoc}
     */
    public String formatLogMessage(String message, LogLevel level) {
        String replaced = formatString;

        // These should be received.
        replaced = replaced.replaceAll(levelRegex, level.toString());
        replaced = replaced.replaceAll(messageRegex, message);

        replaced = replaceInvocationIndependent(replaced);
        replaced = replaceInvocationDependant(replaced);

        // This one has to be replaced at the end cause it could break another regex.
        // For example %%m could have undesired behaviors.
        replaced = replaced.replaceAll(percentRegex, "%");

        return replaced;
    }

    /**
     * Replaces the received string with invocation independent values.
     * Independent values are the Date and Time and the Line separator.
     *
     * @param replaced the string to be replaced.
     * @return the string interpolated with the invocation independent values.
     */
    private String replaceInvocationIndependent(String replaced) {
        Matcher dateMatches = datePattern.matcher(replaced);
        if (dateMatches.find()) {
            String formatString = dateMatches.group(0);
            StringBuilder builder = new StringBuilder(formatString);
            // Delete last }
            builder.deleteCharAt(formatString.length() - 1);
            // Delete %, d, {
            builder.deleteCharAt(0);
            builder.deleteCharAt(0);
            builder.deleteCharAt(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat(builder.toString());
            replaced = replaced.replaceAll(dateRegex, dateFormat.format(new Date()));
        }
        replaced = replaced.replaceAll(separatorRegex, "\n");
        return replaced;
    }


    /**
     * Replaces the received string with invocation dependant values.
     * Dependent values are all those related with the execution thread.
     *
     * @param replaced the string to be replaced.
     * @return the string interpolated with the invocation dependant values.
     */
    private String replaceInvocationDependant(String replaced) {
        Thread current = Thread.currentThread();
        StackTraceElement stackTraceElement = current.getStackTrace()[2];

        replaced = replaced.replaceAll(threadRegex, current.getName());
        replaced = replaced.replaceAll(lineRegex, "" + stackTraceElement.getLineNumber());
        replaced = replaced.replaceAll(filenameRegex, stackTraceElement.getFileName());
        replaced = replaced.replaceAll(methodRegex, stackTraceElement.getMethodName());
        return replaced;
    }
}