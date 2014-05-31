package logger.config;



import java.io.File;

/**
 * Created by Tomas on 30/05/2014.
 *
 */
public class LoggerConfigReaderFactory {

    private static LoggerConfigReaderFactory instance = null ;

    private static String propertiesConfigFileName = "logger­config.properties";
    private static String xmlConfigFileName = "logger-config.xml";



    private LoggerConfigReaderFactory() {

    }

    public static LoggerConfigReaderFactory getInstace () {
        if (instance == null){
            instance = new LoggerConfigReaderFactory();
        }
        return instance;
    }

    private boolean existsFile (String filename) {
        File f = new File(filename);
        return (f.exists() && !f.isDirectory());
    }

    public LoggerConfigReader getReaderFor (PropertyApplyingDelegate aDelegate) {

        if (existsFile(propertiesConfigFileName)){
            return new LoggerPropertyReader(aDelegate, propertiesConfigFileName);
        } else if (existsFile(xmlConfigFileName) ) {
            return new LoggerXMLReader(aDelegate, xmlConfigFileName);
        } else { // NOT CONFIG FILE FOUND, USE DEFAULT CONFIG READER
            return new LoggerDefaultReader(aDelegate);
        }
    }
}
