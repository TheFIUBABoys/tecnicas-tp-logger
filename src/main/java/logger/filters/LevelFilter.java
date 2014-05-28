package logger.filters;

/**
 * Created by gonchub on 13/05/14.
 * Filter for the log logger.level.
 */
public class LevelFilter implements FormatFilter {

    private final String levelRegex = "%p";
    private String levelToReplace;

    /**
     * Override default constructor to receive logger.level to replace.
     *
     * @param level the logger.level to replace.
     */
    public LevelFilter(String level) {
        levelToReplace = level;
    }

    /**
     * {@inheritDoc}
     */
    public String filter(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(levelRegex, levelToReplace);
        return filtered;
    }

    /**
     * {@inheritDoc}
     */
    public String clear(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(levelRegex, "");
        return filtered;
    }
}
