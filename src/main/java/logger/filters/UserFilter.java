package logger.filters;

import logger.format.LogContainer;

/**
 * Created by GonchuB on 31/05/2014.
 * FIUBA
 */
public interface UserFilter {

    /**
     * If this methods returns true, the message should not be logged.
     *
     * @param logContainer contains the log message information.
     * @return True if it shouldn't be logged, False otherwise.
     */
    public Boolean matchesFilter(LogContainer logContainer);

}
