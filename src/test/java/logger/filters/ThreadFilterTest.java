package logger.filters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThreadFilterTest {

    private FormatFilterInterface threadFilter;

    @Before
    public void setUp() throws Exception {
        threadFilter = new ThreadFilter();
    }

    @Test
    public void testFilter() throws Exception {
        String filterFormat = "%t - %F";
        assertEquals("main - NativeMethodAccessorImpl.java", threadFilter.filter(filterFormat, ""));
    }

    @Test
    public void testClear() throws Exception {
        String filterFormat = "%t%F";
        assertEquals("", threadFilter.clear(filterFormat));
    }
}