package logger.filters;

import logger.level.LevelDebug;
import logger.level.LogLevel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LevelFilterTest {

    private FormatFilterInterface levelFilter;
    private LogLevel level;

    @Before
    public void setUp() throws Exception {
        level = new LevelDebug();
        levelFilter = new LevelFilter();
    }

    @Test
    public void testFilter() throws Exception {
        String filterFormat = "%p";
        assertEquals(level.toString(), levelFilter.filter(filterFormat, level.toString()));
    }

    @Test
    public void testClear() throws Exception {
        String filterFormat = "%p";
        assertEquals("", levelFilter.clear(filterFormat));
    }
}