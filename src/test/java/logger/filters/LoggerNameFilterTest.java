package logger.filters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoggerNameFilterTest {

    private FormatFilterInterface messageFilter;
    private String loggerName;

    @Before
    public void setUp() throws Exception {
        loggerName = "LOGGER1";
        messageFilter = new LoggerNameFilter(loggerName);
    }

    @Test
    public void testFilter() throws Exception {
        String filterFormat = "%g";
        assertEquals(loggerName, messageFilter.filter(filterFormat));
    }

    @Test
    public void testClear() throws Exception {
        String filterFormat = "%g";
        assertEquals("", messageFilter.clear(filterFormat));
    }

}