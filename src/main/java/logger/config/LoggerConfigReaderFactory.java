package logger.config;

import java.io.File;

/**
 * Created by Tomas on 30/05/2014.
 * FIUBA.
 */
public class LoggerConfigReaderFactory {

    private static LoggerConfigReaderFactory instance = null;

    /**
     * Private constructor. Called by singleton getInstance method.
     */
    private LoggerConfigReaderFactory() {

    }

    /**
     * Singleton getInstance method. Creates the Config Reader Factory if it doesn't yet exist.
     *
     * @return the config reader factory.
     */
    public static LoggerConfigReaderFactory getInstance() {
        if (instance == null) {
            instance = new LoggerConfigReaderFactory();
        }
        return instance;
    }

    /**
     * Checks if a file exists with the given filename.
     *
     * @param filename the filename to check.
     * @return True if exists, False otherwise.
     */
    private boolean existsFile(String filename) {
        File f = new File(filename);
        return (f.exists() && !f.isDirectory());
    }

    /**
     * Returns a config reader based on the delegate.
     *
     * @param aDelegate the delegate received.
     * @return the config reader for the received delegate.
     */
    public LoggerConfigReader getReaderFor(PropertyApplyingDelegate aDelegate) {
        String propertiesConfigFileName = "logger­config.properties";
        String xmlConfigFileName = "logger-config.xml";

        if (existsFile(propertiesConfigFileName)) {
            return new LoggerPropertyReader(aDelegate, propertiesConfigFileName);
        } else if (existsFile(xmlConfigFileName)) {
            return new LoggerXMLReader(aDelegate, xmlConfigFileName);
        } else {
            // CONFIG FILE NOT FOUND, USE DEFAULT CONFIG READER
            return new LoggerDefaultReader(aDelegate);
        }
    }
}
