package logger.filters;

/**
 * Created by gonchub on 13/05/14.
 * Filter for the log message.
 */
public class MessageFilter implements FormatFilter {

    private String messageToReplace;
    private final String messageRegex = "%m";

    /**
     * Override default constructor to receive message to replace.
     *
     * @param message the message to replace.
     */
    public MessageFilter(String message) {
        messageToReplace = message;
    }

    /**
     * {@inheritDoc}
     */
    public String filter(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(messageRegex, messageToReplace);
        return filtered;
    }

    /**
     * {@inheritDoc}
     */
    public String clear(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(messageRegex, "");
        return filtered;
    }
}
