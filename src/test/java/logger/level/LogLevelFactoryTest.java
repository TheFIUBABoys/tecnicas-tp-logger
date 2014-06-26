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
    public void getInstance() throws Exception {
        assertNotNull(factoryInstance);
    }

    @Test
    public void createLogLevelTrace() throws NotExistingLevelException {
        LogLevel levelTrace = LogLevel.LEVEL_TRACE;
        LogLevel createdLevel = factoryInstance.createLogLevel(levelTrace.toString());
        assertEquals(createdLevel.toString(), levelTrace.toString());
    }

    @Test
    public void createLogLevelDebug() throws NotExistingLevelException {
        LogLevel levelDebug = LogLevel.LEVEL_DEBUG;
        LogLevel createdLevel = factoryInstance.createLogLevel(levelDebug.toString());
        assertEquals(createdLevel.toString(), levelDebug.toString());
    }

    @Test
    public void createLogLevelInfo() throws NotExistingLevelException {
        LogLevel levelInfo = LogLevel.LEVEL_INFO;
        LogLevel createdLevel = factoryInstance.createLogLevel(levelInfo.toString());
        assertEquals(createdLevel.toString(), levelInfo.toString());
    }

    @Test
    public void createLogLevelWarn() throws NotExistingLevelException {
        LogLevel levelWarn = LogLevel.LEVEL_WARN;
        LogLevel createdLevel = factoryInstance.createLogLevel(levelWarn.toString());
        assertEquals(createdLevel.toString(), levelWarn.toString());
    }

    @Test
    public void createLogLevelError() throws NotExistingLevelException {
        LogLevel levelError = LogLevel.LEVEL_ERROR;
        LogLevel createdLevel = factoryInstance.createLogLevel(levelError.toString());
        assertEquals(createdLevel.toString(), levelError.toString());

    }

    @Test
    public void createLogLevelFatal() throws NotExistingLevelException {
        LogLevel levelFatal = LogLevel.LEVEL_FATAL;
        LogLevel createdLevel = factoryInstance.createLogLevel(levelFatal.toString());
        assertEquals(createdLevel.toString(), levelFatal.toString());
    }

    @Test
    public void createLogLevelOff() throws NotExistingLevelException {
        LogLevel levelOff = LogLevel.LEVEL_OFF;
        LogLevel createdLevel = factoryInstance.createLogLevel(levelOff.toString());
        assertEquals(createdLevel.toString(), levelOff.toString());
    }

    @Test(expected = NotExistingLevelException.class)
    public void createLogLevelThrowsException() throws Exception, NotExistingLevelException {
        factoryInstance.createLogLevel("DOES NOT EXIST");
    }

}