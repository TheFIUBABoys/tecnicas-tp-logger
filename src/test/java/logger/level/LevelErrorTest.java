package logger.level;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelErrorTest {
    private LevelComparator comparator = LevelComparator.getInstance();
    @Test
    public void testCompareToTrace() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_TRACE) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToDebug() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToInfo() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_INFO) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToWarn() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_WARN) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToError() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_ERROR) == LogLevelComparisonResult.resultEqual);
    }

    @Test
    public void testCompareToFatal() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_FATAL) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testCompareToOff() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_OFF) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("ERROR", LogLevel.LEVEL_ERROR.toString());
    }

}