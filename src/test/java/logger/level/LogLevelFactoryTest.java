package logger.level;

import junit.framework.Assert;
import loggerExceptions.NotExistingLevelException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class LogLevelFactoryTest {

    private LogLevelFactory factoryInstance;

    @Before
    public void setUp() throws Exception {
        factoryInstance = LogLevelFactory.getInstance();
    }

    @Test
    public void testGetInstance() throws Exception {
        Assert.assertNotNull(factoryInstance);
    }

    @Test
    public void testCreateLogLevelDebug() throws Exception {
        LogLevel levelDebug = new LevelDebug();
        try {
            LogLevel createdLevel = factoryInstance.createLogLevel(levelDebug.toString());
            Assert.assertEquals(createdLevel.toString(), levelDebug.toString());
        } catch (NotExistingLevelException e) {
            fail("Should not get here.");
        }
    }

    @Test
    public void testCreateLogLevelInfo() throws Exception {
        LogLevel levelInfo = new LevelInfo();
        try {
            LogLevel createdLevel = factoryInstance.createLogLevel(levelInfo.toString());
            Assert.assertEquals(createdLevel.toString(), levelInfo.toString());
        } catch (NotExistingLevelException e) {
            fail("Should not get here.");
        }
    }

    @Test
    public void testCreateLogLevelWarn() throws Exception {
        LogLevel levelWarn = new LevelWarn();
        try {
            LogLevel createdLevel = factoryInstance.createLogLevel(levelWarn.toString());
            Assert.assertEquals(createdLevel.toString(), levelWarn.toString());
        } catch (NotExistingLevelException e) {
            fail("Should not get here.");
        }
    }

    @Test
    public void testCreateLogLevelError() throws Exception {
        LogLevel levelError = new LevelError();
        try {
            LogLevel createdLevel = factoryInstance.createLogLevel(levelError.toString());
            Assert.assertEquals(createdLevel.toString(), levelError.toString());
        } catch (NotExistingLevelException e) {
            fail("Should not get here.");
        }
    }

    @Test
    public void testCreateLogLevelFatal() throws Exception {
        LogLevel levelFatal = new LevelFatal();
        try {
            LogLevel createdLevel = factoryInstance.createLogLevel(levelFatal.toString());
            Assert.assertEquals(createdLevel.toString(), levelFatal.toString());
        } catch (NotExistingLevelException e) {
            fail("Should not get here.");
        }
    }

    @Test
    public void testCreateLogLevelOff() throws Exception {
        LogLevel levelOff = new LevelOff();
        try {
            LogLevel createdLevel = factoryInstance.createLogLevel(levelOff.toString());
            Assert.assertEquals(createdLevel.toString(), levelOff.toString());
        } catch (NotExistingLevelException e) {
            fail("Should not get here.");
        }
    }

    @Test(expected = NotExistingLevelException.class)
    public void testCreateLogLevelThrowsException() throws Exception, NotExistingLevelException {
        LogLevel createdLevel = factoryInstance.createLogLevel("DOES NOT EXIST");
        fail("Did not throw exception.");
    }

}