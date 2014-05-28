package logger.filters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SeparatorFilterTest {

    private FormatFilterInterface separatorFilter;
    private String separator;

    @Before
    public void setUp() throws Exception {
        separator = ",";
        separatorFilter = new SeparatorFilter(separator);
    }

    @Test
    public void testFilter() throws Exception {
        String filterFormat = "%n";
        assertEquals(separator, separatorFilter.filter(filterFormat));
    }

    @Test
    public void testClear() throws Exception {
        String filterFormat = "%n";
        assertEquals("", separatorFilter.clear(filterFormat));
    }
}