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
        regex = "%%";
    }

    public String filter(String message, String param) {
        String filtered = message;
        filtered = filtered.replaceAll(regex, "%");
        return filtered;
    }

}
