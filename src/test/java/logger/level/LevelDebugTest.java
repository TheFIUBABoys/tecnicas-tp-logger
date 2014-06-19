package logger.level;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelDebugTest {
    private LevelComparator comparator = LevelComparator.getInstance();
    @Test
    public void compareToTrace() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_TRACE) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void compareToDebug() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultEqual);
    }

    @Test
    public void compareToInfo() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_INFO) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void compareToWarn() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_WARN) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void compareToError() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_ERROR) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void compareToFatal() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_FATAL) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void compareToOff() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_DEBUG,LogLevel.LEVEL_OFF) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void toStringShouldReturnDEBUG() throws Exception {
        assertEquals("DEBUG", LogLevel.LEVEL_DEBUG.toString());
    }

}