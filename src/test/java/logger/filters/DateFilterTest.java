package logger.filters;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateFilterTest {

    private FormatFilter dateFilter;

    @Before
    public void setUp() {
        dateFilter = new DateFilter();
    }

    @Test
    public void testFilter() throws Exception {
        String dateFormat = "dd-MM-yyyy";
        String filterFormat = "%d{" + dateFormat + "}";
        assertEquals((new SimpleDateFormat(dateFormat)).format(new Date()), dateFilter.filter(filterFormat));
    }

    @Test
    public void testClear() throws Exception {
        String dateFormat = "dd-MM-yyyy";
        String filterFormat = "%d{" + dateFormat + "}";
        assertEquals("", dateFilter.clear(filterFormat));
    }
}