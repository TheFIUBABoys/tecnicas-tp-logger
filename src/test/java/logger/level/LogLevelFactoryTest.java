package logger.level;

import logger.exceptions.NotExistingLevelException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LogLevelFactoryTest {

    private LogLevelFactory factoryInstance;

    @Before
    public void setUp() throws Exception {
        factoryInstance = LogLevelFactory.getInstance();
    }

    @Test
    public void testGetInstance() throws Exception {
        assertNotNull(factoryInstance);
    }

    @Test
    public void testCreateLogLevelTrace() throws NotExistingLevelException {
        LogLevel levelTrace = new LevelTrace();
        LogLevel createdLevel = factoryInstance.createLogLevel(levelTrace.toString());
        assertEquals(createdLevel.toString(), levelTrace.toString());
    }

    @Test
    public void testCreateLogLevelDebug() throws NotExistingLevelException {
        LogLevel levelDebug = new LevelDebug();
        LogLevel createdLevel = factoryInstance.createLogLevel(levelDebug.toString());
        assertEquals(createdLevel.toString(), levelDebug.toString());
    }

    @Test
    public void testCreateLogLevelInfo() throws NotExistingLevelException {
        LogLevel levelInfo = new LevelInfo();
        LogLevel createdLevel = factoryInstance.createLogLevel(levelInfo.toString());
        assertEquals(createdLevel.toString(), levelInfo.toString());
    }

    @Test
    public void testCreateLogLevelWarn() throws NotExistingLevelException {
        LogLevel levelWarn = new LevelWarn();
        LogLevel createdLevel = factoryInstance.createLogLevel(levelWarn.toString());
        assertEquals(createdLevel.toString(), levelWarn.toString());
    }

    @Test
    public void testCreateLogLevelError() throws NotExistingLevelException {
        LogLevel levelError = new LevelError();
        LogLevel createdLevel = factoryInstance.createLogLevel(levelError.toString());
        assertEquals(createdLevel.toString(), levelError.toString());

    }

    @Test
    public void testCreateLogLevelFatal() throws NotExistingLevelException {
        LogLevel levelFatal = new LevelFatal();
        LogLevel createdLevel = factoryInstance.createLogLevel(levelFatal.toString());
        assertEquals(createdLevel.toString(), levelFatal.toString());
    }

    @Test
    public void testCreateLogLevelOff() throws NotExistingLevelException {
        LogLevel levelOff = new LevelOff();
        LogLevel createdLevel = factoryInstance.createLogLevel(levelOff.toString());
        assertEquals(createdLevel.toString(), levelOff.toString());
    }

    @Test(expected = NotExistingLevelException.class)
    public void testCreateLogLevelThrowsException() throws Exception, NotExistingLevelException {
        factoryInstance.createLogLevel("DOES NOT EXIST");
    }

}