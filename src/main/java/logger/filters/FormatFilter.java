package logger.filters;

/**
 * Created by gonchub on 28/05/14.
 * Reuse code between filters.
 */
public abstract class FormatFilter implements FormatFilterInterface {

    protected String regex;

    @Override
    public String filter(String message, FilterReplaceContainer replaceParams) {
        return filter(message, "");
    }

    @Override
    public String filter(String message, String param) {
        String filtered = message;
        filtered = filtered.replaceAll(regex, param);
        return filtered;
    }

    @Override
    public String clear(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(regex, "");
        return filtered;
    }

}
