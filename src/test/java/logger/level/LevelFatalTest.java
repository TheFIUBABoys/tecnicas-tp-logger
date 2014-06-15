package logger.level;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelFatalTest {
    private LevelComparator comparator = LevelComparator.getInstance();
    @Test
    public void testCompareToTrace() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_TRACE) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToDebug() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToInfo() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_INFO) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToWarn() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_WARN) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToError() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_ERROR) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void testCompareToFatal() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_FATAL) == LogLevelComparisonResult.resultEqual);
    }

    @Test
    public void testCompareToOff() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_OFF) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("FATAL", LogLevel.LEVEL_FATAL.toString());
    }

}