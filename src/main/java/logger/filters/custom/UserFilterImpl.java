package logger.filters.custom;

import logger.exceptions.NotExistingLevelException;
import logger.format.LogContainer;
import logger.level.LogLevel;
import logger.level.LogLevelComparisonResult;
import logger.level.LogLevelFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by GonchuB on 31/05/2014.
 * FIUBA.
 * Example implementation of a User Defined Filter.
 * This filters out messages with higher Log Level
 * than INFO. Also has the ability to match a regex
 * in order to filter.
 */
public class UserFilterImpl implements UserFilter {

    private String regex;

    /**
     * Constructor with regex.
     *
     * @param regex regex value
     */
    public UserFilterImpl(String regex) {
        this.regex = regex;
    }

    /**
     * Empty constructor.
     */
    public UserFilterImpl() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean matchesFilter(LogContainer logContainer) {

        Boolean matches = false;

        if (regex != null && !regex.isEmpty()) {
            Pattern userPattern = Pattern.compile(regex);
            Matcher messageMatches = userPattern.matcher(logContainer.getMessage());
            matches = messageMatches.find();
        }

        if (!matches) {
            matches = filterMoreThanInfoLevels(logContainer);
        }

        return matches;

    }

    /**
     * Arbitrary filter.
     *
     * @param logContainer the log container to compare to.
     * @return True if log level is more than info.
     */
    private Boolean filterMoreThanInfoLevels(LogContainer logContainer) {
        LogLevelFactory factory = LogLevelFactory.getInstance();
        LogLevel level;
        try {
            level = factory.createLogLevel(logContainer.getLogLevel());
        } catch (NotExistingLevelException e) {
            return false;
        }
        return level.compareToLevel(LogLevel.LEVEL_INFO) == LogLevelComparisonResult.resultGreater;
    }

}
