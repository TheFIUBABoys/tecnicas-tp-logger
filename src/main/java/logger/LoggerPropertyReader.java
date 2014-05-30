package logger;


import java.io.FileInputStream;
import java.util.*;

/**
 * Created by Lucas
 * Class that extends LoggerConfigReader and reads config form Properties File
 */
public class LoggerPropertyReader extends LoggerConfigReader{

    public LoggerPropertyReader(PropertyApplyingDelegate aDelegate) {
        super(aDelegate);
    }

    /**
     * {@inheritDoc}
     */
    public void loadConfigFromFile(String filename) throws Exception {
        FileInputStream inputStream = new FileInputStream(filename);
        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();
        for (String propertyKey : LoggerConfigReader.PROPERTY_CONFIG_KEYS) {
            String propertyValue = properties.getProperty(propertyKey, "null");
            if (!propertyValue.equals("null")) {
                methodMap.get(propertyKey).applyPropertyWithValue(propertyKey, propertyValue);
            }
        }
    }
}
