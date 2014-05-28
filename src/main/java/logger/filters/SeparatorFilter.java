package logger.filters;

/**
 * Created by gonchub on 13/05/14.
 * Filter for the EOL separator.
 */
public class SeparatorFilter extends FormatFilter {

    /**
     * Override constructor to receive configurable separator field.
     *
     * @param separator the separator to override.
     */
    public SeparatorFilter(String separator) {
        toReplace = separator;
        regex = "%n";
    }

    /**
     * Override constructor to receive configurable separator field.
     */
    public SeparatorFilter() {
        toReplace = "\n";
        regex = "%n";
    }

}
