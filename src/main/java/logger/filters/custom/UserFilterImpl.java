package logger.filters.custom;

import logger.format.LogContainer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by GonchuB on 31/05/2014.
 * FIUBA
 */
public class UserFilterImpl implements UserFilter {

    private String regex;

    /**
     * Empty constructor.
     */
    public UserFilterImpl() {

    }

    /**
     * Sets the regex to test against the message.
     *
     * @param regex the regex to set.
     */
    public void setRegex(String regex) {
        this.regex = regex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean matchesFilter(LogContainer logContainer) {

        Boolean matches = false;

        if (!regex.isEmpty()) {
            Pattern userPattern = Pattern.compile(regex);
            Matcher messageMatches = userPattern.matcher(logContainer.getMessage());
            matches = messageMatches.find();
        }

        return matches;

    }
}
