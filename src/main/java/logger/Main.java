package logger;

import logger.slf4jAdapter.LoggerSlf4jAdapter;
import logger.slf4jAdapter.LoggerSlf4jAdapterFactoryBinder;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * Created by Lucas on 6/25/2014.
 */
public class Main {
    public static void main(String[] args) {
        ILoggerFactory loggerFactory = LoggerSlf4jAdapterFactoryBinder.getSingleton().getLoggerFactory();
        Logger logger = loggerFactory.getLogger(LoggerSlf4jAdapter.class.getName());
        logger.error("This is an error%n");
        logger.info("This is some info%n");
        logger.warn("Warning!%n");
        logger.trace("Tracing!%n");
        logger.debug("This is a debug message%n");

        logger.error("There have been {} errors with the message {}",100,"\"Wrong format property\".%n");

        logger.error("This is my exception:", new IOException("File Error"));
    }

}
