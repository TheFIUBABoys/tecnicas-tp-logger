package logger.filters;

/**
 * Created by GonchuB on 30/05/2014.
 * FIUBA
 */
public class LoggerNameFilter extends FormatFilter {

    /**
     * Override default constructor to receive logger.level to replace.
     *
     */
    public LoggerNameFilter() {
        regex = "%g";
    }

    public String filter(String message, FilterReplaceContainer replaceParams) {
        return this.filter(message, replaceParams.getLoggerName());
    }

}
