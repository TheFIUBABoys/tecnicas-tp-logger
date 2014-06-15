package logger.filters;

/**
 * Created by gonchub on 13/05/14.
 * Filter for the log message.
 */
public class MessageFilter extends FormatFilter {

    /**
     * Override default constructor to receive message to replace.
     */
    public MessageFilter() {
        regex = "%m";
    }

    public String filter(String message, FilterReplaceContainer replaceParams) {
        return this.filter(message, replaceParams.getMessage());
    }

}
