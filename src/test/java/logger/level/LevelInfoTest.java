package logger.level;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelInfoTest {
    private LevelComparator comparator = LevelComparator.getInstance();
    @Test
    public void compareToTrace() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_TRACE) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void compareToDebug() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void compareToInfo() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_INFO) == LogLevelComparisonResult.resultEqual);
    }

    @Test
    public void compareToWarn() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_WARN) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void compareToError() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_ERROR) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void compareToFatal() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_FATAL) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void compareToOff() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_INFO,LogLevel.LEVEL_OFF) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void toStringShouldReturnINFO() throws Exception {
        assertEquals("INFO", LogLevel.LEVEL_INFO.toString());
    }
}