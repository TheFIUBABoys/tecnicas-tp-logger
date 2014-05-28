package logger.filters;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class PercentFilterTest {

    private FormatFilter percentFilter;

    @Before
    public void setUp() throws Exception {
        percentFilter = new PercentFilter();
    }

    @Test
    public void testFilter() throws Exception {
        String filterFormat = "%%";
        Assert.assertEquals("%", percentFilter.filter(filterFormat));
    }

    @Test
    public void testClear() throws Exception {
        String filterFormat = "%%";
        Assert.assertEquals("", percentFilter.clear(filterFormat));
    }
}