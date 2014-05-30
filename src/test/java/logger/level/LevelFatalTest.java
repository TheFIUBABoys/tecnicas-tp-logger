package logger.level;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelFatalTest {

    @Test
    public void testCompareToTrace() throws Exception {
        assertTrue(LogLevel.LEVEL_TRACE.compareTo(LogLevel.LEVEL_FATAL) > 0);
    }

    @Test
    public void testCompareToDebug() throws Exception {
        assertTrue(LogLevel.LEVEL_DEBUG.compareTo(LogLevel.LEVEL_FATAL) > 0);
    }

    @Test
    public void testCompareToInfo() throws Exception {
        assertTrue(LogLevel.LEVEL_INFO.compareTo(LogLevel.LEVEL_FATAL) > 0);
    }

    @Test
    public void testCompareToWarn() throws Exception {
        assertTrue(LogLevel.LEVEL_WARN.compareTo(LogLevel.LEVEL_FATAL) > 0);
    }

    @Test
    public void testCompareToError() throws Exception {
        assertTrue(LogLevel.LEVEL_ERROR.compareTo(LogLevel.LEVEL_FATAL) > 0);
    }

    @Test
    public void testCompareToFatal() throws Exception {
        assertTrue(LogLevel.LEVEL_FATAL.compareTo(LogLevel.LEVEL_FATAL) < 0);
    }

    @Test
    public void testCompareToOff() throws Exception {
        assertTrue(LogLevel.LEVEL_OFF.compareTo(LogLevel.LEVEL_FATAL) > 0);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("FATAL", LogLevel.LEVEL_FATAL.toString());
    }

}