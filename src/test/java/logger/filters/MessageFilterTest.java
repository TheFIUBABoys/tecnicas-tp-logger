package logger.filters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageFilterTest {

    private FormatFilter messageFilter;
    private String message;

    @Before
    public void setUp() throws Exception {
        message = "Message";
        messageFilter = new MessageFilter(message);
    }

    @Test
    public void testFilter() throws Exception {
        String filterFormat = "%m";
        assertEquals(message, messageFilter.filter(filterFormat));
    }

    @Test
    public void testClear() throws Exception {
        String filterFormat = "%m";
        assertEquals("", messageFilter.clear(filterFormat));
    }
}