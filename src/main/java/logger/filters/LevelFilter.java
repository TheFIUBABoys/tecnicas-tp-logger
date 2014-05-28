package logger.filters;

/**
 * Created by gonchub on 13/05/14.
 * Filter for the log logger.level.
 */
public class LevelFilter extends FormatFilter {

    /**
     * Override default constructor to receive logger.level to replace.
     *
     * @param level the logger.level to replace.
     */
    public LevelFilter(String level) {
        toReplace = level;
        regex = "%p";
    }

}
