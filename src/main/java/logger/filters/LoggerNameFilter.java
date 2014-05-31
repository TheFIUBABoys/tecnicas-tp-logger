package logger.filters;

/**
 * Created by GonchuB on 30/05/2014.
 * FIUBA
 */
public class LoggerNameFilter extends FormatFilter {

    /**
     * Override default constructor to receive logger.level to replace.
     *
     * @param logger the logger.level to replace.
     */
    public LoggerNameFilter(String logger) {
        toReplace = logger;
        regex = "%g";
    }

}
