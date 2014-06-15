package logger.level;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelDebugTest {
    private LevelComparator comparator = LevelComparator.getInstance();
    @Test
    public void testCompareToTrace() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_TRACE) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToDebug() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultEqual);
    }

    @Test
    public void testCompareToInfo() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_INFO) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testCompareToWarn() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_WARN) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testCompareToError() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_ERROR) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testCompareToFatal() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_FATAL) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testCompareToOff() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_OFF) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("DEBUG", LogLevel.LEVEL_DEBUG.toString());
    }

}