package logger.filters;

import junit.framework.Assert;
import level.LevelDebug;
import level.LogLevel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevelFilterTest {

    private FormatFilter levelFilter;
    private LogLevel level;

    @Before
    public void setUp() throws Exception {
        level = new LevelDebug();
        levelFilter = new LevelFilter(level.toString());
    }

    @Test
    public void testFilter() throws Exception {
        String filterFormat = "%p";
        Assert.assertEquals(level.toString(), levelFilter.filter(filterFormat));
    }

    @Test
    public void testClear() throws Exception {
        String filterFormat = "%p";
        Assert.assertEquals("", levelFilter.clear(filterFormat));
    }
}