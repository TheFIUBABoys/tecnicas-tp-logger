package logger.level;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelErrorTest {
    private LevelComparator comparator = LevelComparator.getInstance();
    @Test
    public void compareToTrace() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_TRACE) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void compareToDebug() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_DEBUG) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void compareToInfo() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_INFO) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void compareToWarn() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_WARN) == LogLevelComparisonResult.resultGreater);
    }

    @Test
    public void compareToError() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_ERROR) == LogLevelComparisonResult.resultEqual);
    }

    @Test
    public void compareToFatal() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_FATAL) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void compareToOff() throws Exception {
        assertTrue(comparator.compareLevelToLevel(LogLevel.LEVEL_ERROR,LogLevel.LEVEL_OFF) == LogLevelComparisonResult.resultLesser);
    }

    @Test
    public void toStringShouldReturnERROR() throws Exception {
        assertEquals("ERROR", LogLevel.LEVEL_ERROR.toString());
    }

}