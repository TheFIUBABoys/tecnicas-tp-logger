package logger.filters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoggerNameFilterTest {

    private FormatFilterInterface messageFilter;
    private String loggerName;

    @Before
    public void setUp() throws Exception {
        loggerName = "LOGGER1";
        messageFilter = new LoggerNameFilter();
    }

    @Test
    public void testFilter() throws Exception {
        String filterFormat = "%g";
        assertEquals(loggerName, messageFilter.filter(filterFormat, loggerName));
    }

    @Test
    public void testClear() throws Exception {
        String filterFormat = "%g";
        assertEquals("", messageFilter.clear(filterFormat));
    }

}