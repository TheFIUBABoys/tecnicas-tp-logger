package logger.filters;

/**
 * Created by gonchub on 13/05/14.
 * Filter for the EOL separator.
 */
public class SeparatorFilter implements FormatFilter {

    private final String separatorRegex = "%n";
    private String separatorField = "\n";

    /**
     * Override constructor to receive configurable separator field.
     *
     * @param field the field to use as separator.
     */
    public SeparatorFilter(String field) {
        separatorField = field;
    }

    /**
     * {@inheritDoc}
     */
    public String filter(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(separatorRegex, separatorField);
        return filtered;
    }

    /**
     * {@inheritDoc}
     */
    public String clear(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(separatorRegex, "");
        return filtered;
    }
}
