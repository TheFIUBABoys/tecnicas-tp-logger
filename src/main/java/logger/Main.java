
import logger.Logger;
import logger.LoggerImpl;
import logger.exceptions.InvalidFormatException;
import logger.format.LogFormat;
import logger.format.LogFormatImpl;
import logger.level.LogLevel;
import logger.slf4jAdapter.LoggerSlf4jAdapter;
import logger.slf4jAdapter.LoggerSlf4jAdapterFactoryBinder;
import org.slf4j.ILoggerFactory;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Lucas on 6/25/2014.
 */
public class Main {

    private static int RAND_MAX = 100;

    public static void main(String[] args) {
        try {
//            slf4jTestApp();
            loggerTestApp();
//            twoLoggersTestApp();
        } catch (InvalidFormatException e) {
            System.out.println("Invalid format while testing the logger");
        }
    }

    private static void slf4jTestApp() {
        ILoggerFactory loggerFactory = LoggerSlf4jAdapterFactoryBinder.getSingleton().getLoggerFactory();
        org.slf4j.Logger logger = loggerFactory.getLogger(LoggerSlf4jAdapter.class.getName());
        logger.error("This is an error%n");
        logger.info("This is some info%n");
        logger.warn("Warning!%n");
        logger.trace("Tracing!%n");
        logger.debug("This is a debug message%n");

        logger.error("There have been {} errors with the message {}",100,"\"Wrong format property\".%n");

        logger.error("This is my exception:", new IOException("File Error"));
    }

    private static void loggerTestApp() throws InvalidFormatException {
        Logger logger = LoggerImpl.getLogger();
        logger.setLogLevel(LogLevel.LEVEL_TRACE);
        logger.setMessageFormat(new LogFormatImpl("%p - %d{dd-MM-yyyy hh:mm:ss} - %m%n"));
        logger.logMessage("This is an error", LogLevel.LEVEL_ERROR);
        logger.logMessage("This is some info", LogLevel.LEVEL_INFO);
        logger.logMessage("This is a warning", LogLevel.LEVEL_WARN);
        logger.logMessage("This is a trace", LogLevel.LEVEL_TRACE);
        logger.logMessage("I'm debugging like a champion",LogLevel.LEVEL_DEBUG);

        logger.logMessage("I'm logging an exception. Can this be better?", LogLevel.LEVEL_ERROR, new IOException("File Error"));
    }

    private static void twoLoggersTestApp() throws InvalidFormatException {
        Logger errorLogger = LoggerImpl.getLogger("error");
        errorLogger.setLogLevel(LogLevel.LEVEL_ERROR);
        errorLogger.setMessageFormat(new LogFormatImpl("%p - %m%n"));
        Logger offLogger = LoggerImpl.getLogger("off");
        offLogger.setLogLevel(LogLevel.LEVEL_OFF);
        offLogger.setMessageFormat(new LogFormatImpl("%p - %m%n"));

        for (int i = 0; i < 50; i++) {
            int random = new Random().nextInt(RAND_MAX + 1);
            offLogger.logMessage("Starting iteration " + i + ":", LogLevel.LEVEL_DEBUG);
            offLogger.logMessage("Random number selected is: " + random, LogLevel.LEVEL_DEBUG);

            if (random <= RAND_MAX/10) {
                errorLogger.logMessage("There's been a fatal error. Random is: " + random + ", but mast be less than " + RAND_MAX/10, LogLevel.LEVEL_FATAL);
                offLogger.logMessage("This is a fatal error with random number: " + random, LogLevel.LEVEL_FATAL);
            } else if (random <= RAND_MAX/3){
                errorLogger.logMessage("There's been an error. Random is " + random + ", but should be less than " + RAND_MAX/3, LogLevel.LEVEL_ERROR);
                offLogger.logMessage("This is an error with random number: " + random, LogLevel.LEVEL_DEBUG);
            }
            offLogger.logMessage("Finishing iteration " + i + ":", LogLevel.LEVEL_DEBUG);
        }

    }

}
