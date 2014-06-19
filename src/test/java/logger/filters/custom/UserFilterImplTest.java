package logger.filters.custom;

import logger.format.LogContainer;
import logger.format.LogContainerImpl;
import logger.level.LogLevel;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserFilterImplTest {

    private UserFilter userFilter;
    private LogContainer logContainer;
    private Date date;
    private String message;
    private String loggerName;

    @Before
    public void setUp() {
        userFilter = new UserFilterImpl();
        logContainer = new LogContainerImpl();
        date = new Date();
        message = "LOGGING";
        loggerName = "DEFAULT";

        logContainer.setLogLevel(LogLevel.LEVEL_ERROR.toString());
        logContainer.setDate(date);
        logContainer.setMessage(message);
        logContainer.setLoggerName(loggerName);
    }

    @Test
    public void matchesFilter() throws Exception {
        assertFalse(userFilter.matchesFilter(logContainer));
    }

    @Test
    public void setRegex() throws Exception {
        userFilter = new UserFilterImpl(message);
        assertTrue(userFilter.matchesFilter(logContainer));
    }

    @Test
    public void setLogLevel() throws Exception {
        logContainer.setLogLevel(LogLevel.LEVEL_INFO.toString());
        assertFalse(userFilter.matchesFilter(logContainer));
    }

    @Test
    public void invalidLogLevel() {
        logContainer.setLogLevel("not important");
        assertFalse(userFilter.matchesFilter(logContainer));
    }
}