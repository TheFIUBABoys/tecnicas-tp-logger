package logger.filters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gonchub on 13/05/14.
 * Filter for the dates.
 */
public class DateFilter extends FormatFilter {

    private final String dateRegex = "%d\\{[^\\}]*\\}";
    private final Pattern datePattern = Pattern.compile(dateRegex);

    /**
     * {@inheritDoc}
     */
    public String filter(String message, String param) {
        String filtered = message;
        Matcher dateMatches = datePattern.matcher(message);
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
            filtered = message.replaceAll(dateRegex, dateFormat.format(new Date()));
        }
        return filtered;
    }

    /**
     * {@inheritDoc}
     */
    public String clear(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(dateRegex, "");
        return filtered;
    }

}
