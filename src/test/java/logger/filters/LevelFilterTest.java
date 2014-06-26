package logger.filters;


import logger.level.LogLevel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LevelFilterTest {

    private FormatFilterInterface levelFilter;
    private LogLevel level;

    @Before
    public void setUp() throws Exception {
        level = LogLevel.LEVEL_DEBUG;
        levelFilter = new LevelFilter();
    }

    @Test
    public void filter() throws Exception {
        String filterFormat = "%p";
        assertEquals(level.toString(), levelFilter.filter(filterFormat, level.toString()));
    }

    @Test
    public void clear() throws Exception {
        String filterFormat = "%p";
        assertEquals("", levelFilter.clear(filterFormat));
    }
}