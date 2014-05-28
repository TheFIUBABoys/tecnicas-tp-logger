package logger.filters;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeparatorFilterTest {

    private FormatFilter separatorFilter;
    private String separator;

    @Before
    public void setUp() throws Exception {
        separator = ",";
        separatorFilter = new SeparatorFilter(separator);
    }

    @Test
    public void testFilter() throws Exception {
        String filterFormat = "%n";
        Assert.assertEquals(separator, separatorFilter.filter(filterFormat));
    }

    @Test
    public void testClear() throws Exception {
        String filterFormat = "%n";
        Assert.assertEquals("", separatorFilter.clear(filterFormat));
    }
}