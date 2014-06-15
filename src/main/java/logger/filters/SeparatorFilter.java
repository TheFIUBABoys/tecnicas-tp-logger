package logger.filters;

/**
 * Created by gonchub on 13/05/14.
 * Filter for the EOL separator.
 */
public class SeparatorFilter extends FormatFilter {

    /**
     * Override constructor to receive configurable separator field.
     */
    public SeparatorFilter() {
        regex = "%n";
    }

    public String filter(String message, FilterReplaceContainer replaceParams) {
        return this.filter(message, replaceParams.getSeparator());
    }
}
