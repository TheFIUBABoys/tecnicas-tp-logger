package logger.level;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevelOffTest {
    private LogLevel levelDebug;
    private LogLevel levelInfo;
    private LogLevel levelWarn;
    private LogLevel levelError;
    private LogLevel levelFatal;
    private LogLevel levelOff;

    @Before
    public void setUp() throws Exception {
        levelDebug = new LevelDebug();
        levelInfo = new LevelInfo();
        levelWarn = new LevelWarn();
        levelError = new LevelError();
        levelFatal = new LevelFatal();
        levelOff = new LevelOff();
    }

    @Test
    public void testCompareToDebug() throws Exception {
        assertTrue(levelDebug.compareTo(levelOff) > 0);
    }

    @Test
    public void testCompareToInfo() throws Exception {
        assertTrue(levelInfo.compareTo(levelOff) > 0);
    }

    @Test
    public void testCompareToWarn() throws Exception {
        assertTrue(levelWarn.compareTo(levelOff) > 0);
    }

    @Test
    public void testCompareToError() throws Exception {
        assertTrue(levelError.compareTo(levelOff) > 0);
    }

    @Test
    public void testCompareToFatal() throws Exception {
        assertTrue(levelFatal.compareTo(levelOff) > 0);
    }

    @Test
    public void testCompareToOff() throws Exception {
        assertTrue(levelOff.compareTo(levelOff) > 0);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("OFF", levelOff.toString());
    }

}