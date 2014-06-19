package logger.filters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageFilterTest {

    private FormatFilterInterface messageFilter;
    private String message;

    @Before
    public void setUp() throws Exception {
        message = "Message";
        messageFilter = new MessageFilter();
    }

    @Test
    public void filter() throws Exception {
        String filterFormat = "%m";
        assertEquals(message, messageFilter.filter(filterFormat, message));
    }

    @Test
    public void clear() throws Exception {
        String filterFormat = "%m";
        assertEquals("", messageFilter.clear(filterFormat));
    }
}