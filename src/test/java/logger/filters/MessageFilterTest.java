package logger.filters;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        Assert.assertEquals(message, messageFilter.filter(filterFormat));
    }

    @Test
    public void testClear() throws Exception {
        String filterFormat = "%m";
        Assert.assertEquals("", messageFilter.clear(filterFormat));
    }
}