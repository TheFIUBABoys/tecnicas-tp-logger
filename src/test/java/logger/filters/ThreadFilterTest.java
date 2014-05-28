package logger.filters;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class ThreadFilterTest {

    private FormatFilter threadFilter;

    @Before
    public void setUp() throws Exception {
        threadFilter = new ThreadFilter();
    }

    @Test
    public void testFilter() throws Exception {
        String filterFormat = "%t - %F";
        Assert.assertEquals("main - NativeMethodAccessorImpl.java", threadFilter.filter(filterFormat));
    }

    @Test
    public void testClear() throws Exception {
        String filterFormat = "%t%F";
        Assert.assertEquals("", threadFilter.clear(filterFormat));
    }
}