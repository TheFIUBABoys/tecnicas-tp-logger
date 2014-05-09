package level;

import junit.framework.Assert;

public class LevelDebugTest {

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
        Assert.assertTrue(levelDebug.compareTo(levelDebug) < 0);
    }

    @org.junit.Test
    public void testCompareToInfo() throws Exception {
        Assert.assertTrue(levelInfo.compareTo(levelDebug) < 0);
    }

    @org.junit.Test
    public void testCompareToWarn() throws Exception {
        Assert.assertTrue(levelWarn.compareTo(levelDebug) < 0);
    }

    @org.junit.Test
    public void testCompareToError() throws Exception {
        Assert.assertTrue(levelError.compareTo(levelDebug) < 0);
    }

    @org.junit.Test
    public void testCompareToFatal() throws Exception {
        Assert.assertTrue(levelFatal.compareTo(levelDebug) < 0);
    }

    @org.junit.Test
    public void testCompareToOff() throws Exception {
        Assert.assertTrue(levelOff.compareTo(levelDebug) > 0);
    }
}