package logger.slf4jAdapter;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * Created by Lucas on 5/31/2014.
 */


public class LoggerSlf4jAdapterFactoryBinder implements LoggerFactoryBinder {

    /**
     * The unique instance of this class.
     */

    private static final LoggerSlf4jAdapterFactoryBinder SINGLETON = new LoggerSlf4jAdapterFactoryBinder();


    /**
     * Return the singleton of this class.
     *
     * @return the StaticLoggerBinder singleton
     */


    public static final LoggerSlf4jAdapterFactoryBinder getSingleton() {
        return SINGLETON;
    }


    /**
     * Declare the version of the SLF4J API this implementation is
     * compiled against. The value of this field is usually modified
     * with each release.
     */

    public static String REQUESTED_API_VERSION = "1.6";


    private static final String loggerFactoryClassStr = LoggerSlf4jAdapterFactory.class.getName();


    /**
     * The ILoggerFactory instance returned by the
     * {@link #getLoggerFactory} method should always be the same
     * object.
     */

    private final ILoggerFactory loggerFactory;


    private LoggerSlf4jAdapterFactoryBinder() {
        loggerFactory = new LoggerSlf4jAdapterFactory();
    }


    public ILoggerFactory getLoggerFactory() {
        return loggerFactory;
    }


    public String getLoggerFactoryClassStr() {
        return loggerFactoryClassStr;
    }
}
