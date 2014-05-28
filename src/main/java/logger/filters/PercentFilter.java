package logger.filters;

/**
 * Created by gonchub on 13/05/14.
 * Filter for the percent symbol.
 */
public class PercentFilter extends FormatFilter {

    /**
     * Override default constructor to receive message to replace.
     */
    public PercentFilter() {
        toReplace = "%";
        regex = "%%";
    }

}
