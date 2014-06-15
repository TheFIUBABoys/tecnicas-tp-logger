package logger.level;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelInfoTest {
    private LevelComparator comparator = LevelComparator.getInstance();
    @Test
    public void testCompareToTrace() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_TRACE) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToDebug() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToInfo() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_INFO) == LogLevelComparisonResult.resultEqual);
    }

    @Test
    public void testCompareToWarn() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_WARN) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testCompareToError() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_ERROR) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testCompareToFatal() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_FATAL) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testCompareToOff() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_OFF) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("INFO", LogLevel.LEVEL_INFO.toString());
    }
}