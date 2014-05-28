package logger.level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelInfoTest {
    private LogLevel levelDebug;
    private LogLevel levelInfo;
    private LogLevel levelWarn;
    private LogLevel levelError;
    private LogLevel levelFatal;
    private LogLevel levelOff;

    @org.junit.Before
    public void setUp() throws Exception {
        levelDebug = new LevelDebug();
        levelInfo = new LevelInfo();
        levelWarn = new LevelWarn();
        levelError = new LevelError();
        levelFatal = new LevelFatal();
        levelOff = new LevelOff();
    }

    @org.junit.Test
    public void testCompareToDebug() throws Exception {
        assertTrue(levelDebug.compareTo(levelInfo) > 0);
    }

    @org.junit.Test
    public void testCompareToInfo() throws Exception {
        assertTrue(levelInfo.compareTo(levelInfo) < 0);
    }

    @org.junit.Test
    public void testCompareToWarn() throws Exception {
        assertTrue(levelWarn.compareTo(levelInfo) < 0);
    }

    @org.junit.Test
    public void testCompareToError() throws Exception {
        assertTrue(levelError.compareTo(levelInfo) < 0);
    }

    @org.junit.Test
    public void testCompareToFatal() throws Exception {
        assertTrue(levelFatal.compareTo(levelInfo) < 0);
    }

    @org.junit.Test
    public void testCompareToOff() throws Exception {
        assertTrue(levelOff.compareTo(levelInfo) > 0);
    }

    @org.junit.Test
    public void testToString() throws Exception {
        assertEquals("INFO", levelInfo.toString());
    }
}