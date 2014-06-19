package logger.filters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PercentFilterTest {

    private FormatFilterInterface percentFilter;

    @Before
    public void setUp() throws Exception {
        percentFilter = new PercentFilter();
    }

    @Test
    public void filter() throws Exception {
        String filterFormat = "%%";
        assertEquals("%", percentFilter.filter(filterFormat, ""));
    }

    @Test
    public void clear() throws Exception {
        String filterFormat = "%%";
        assertEquals("", percentFilter.clear(filterFormat));
    }
}