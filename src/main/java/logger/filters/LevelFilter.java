package logger.filters;

/**
 * Created by gonchub on 13/05/14.
 * Filter for the log logger.level.
 */
public class LevelFilter extends FormatFilter {

    /**
     * Override default constructor to receive logger.level to replace.
     *
     */
    public LevelFilter() {
        regex = "%p";
    }

    public String filter(String message, FilterReplaceContainer replaceParams) {
        return this.filter(message, replaceParams.getLogLevel());
    }

}
