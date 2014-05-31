package logger.level;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelDebugTest {

    @Test
    public void testCompareToTrace() throws Exception {
        assertTrue(LogLevel.LEVEL_TRACE.compareToLevel(LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToDebug() throws Exception {
        assertTrue(LogLevel.LEVEL_DEBUG.compareToLevel(LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultEqual);
    }

    @Test
    public void testCompareToInfo() throws Exception {
        assertTrue(LogLevel.LEVEL_INFO.compareToLevel(LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testCompareToWarn() throws Exception {
        assertTrue(LogLevel.LEVEL_WARN.compareToLevel(LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testCompareToError() throws Exception {
        assertTrue(LogLevel.LEVEL_ERROR.compareToLevel(LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testCompareToFatal() throws Exception {
        assertTrue(LogLevel.LEVEL_FATAL.compareToLevel(LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testCompareToOff() throws Exception {
        assertTrue(LogLevel.LEVEL_OFF.compareToLevel(LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("DEBUG", LogLevel.LEVEL_DEBUG.toString());
    }

}