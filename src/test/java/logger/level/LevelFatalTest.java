package logger.level;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelFatalTest {
    private LevelComparator comparator = LevelComparator.getInstance();
    @Test
    public void compareToTrace() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_TRACE) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void compareToDebug() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void compareToInfo() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_INFO) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void compareToWarn() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_WARN) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void compareToError() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_ERROR) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void compareToFatal() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_FATAL) == LogLevelComparisonResult.resultEqual);
    }

    @Test
    public void compareToOff() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_FATAL,LogLevel.LEVEL_OFF) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void toStringShouldReturnFATAL() throws Exception {
        assertEquals("FATAL", LogLevel.LEVEL_FATAL.toString());
    }

}