package logger.filters;

/**
 * Created by gonchub on 13/05/14.
 * Filter for the log message.
 */
public class MessageFilter extends FormatFilter {

    /**
     * Override default constructor to receive message to replace.
     *
     * @param message the message to replace.
     */
    public MessageFilter(String message) {
        toReplace = message;
        regex = "%m";
    }

}
