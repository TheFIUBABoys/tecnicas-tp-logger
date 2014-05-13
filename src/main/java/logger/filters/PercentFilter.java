package logger.filters;

/**
 * Created by gonchub on 13/05/14.
 * Filter for the percent symbol.
 */
public class PercentFilter implements FormatFilter {

    private final String percentRegex = "%%";

    /**
     * {@inheritDoc}
     */
    public String filter(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(percentRegex, "%");
        return filtered;
    }

    /**
     * {@inheritDoc}
     */
    public String clear(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(percentRegex, "");
        return filtered;
    }

}
