package logger.filters;

/**
 * Created by gonchub on 28/05/14.
 * Reuse code between filters.
 */
public abstract class FormatFilter implements FormatFilterInterface {

    protected String regex;
    protected String toReplace;

    @Override
    public String filter(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(regex, toReplace);
        return filtered;
    }

    @Override
    public String clear(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(regex, "");
        return filtered;
    }

}
