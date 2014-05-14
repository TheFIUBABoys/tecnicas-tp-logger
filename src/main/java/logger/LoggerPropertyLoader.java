package logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import loggerExceptions.WrongPropertyFormatException;

/**
 * Created by Lucas
 * Any property that will be applied must use this interface.
 * (Java 'hack' to have a method dictionary)
 */
interface Command {
    void applyPropertyWithValue(String propertyKey, String value) throws Exception;
}

/**
 * Created by Lucas
 * <p/>
 * Class that will handle property loading, applying the actions through a delegate.
 * Current available properties are
 * consoleOutput: true/false
 * outputFile: list of files separated by commas, i.e. file1,file2,file3
 * logLevel: a logLevel according to levels in LogLevel interface
 * logFormat: format available in LogFormat interface
 */
public class LoggerPropertyLoader {

    private PropertyApplyingDelegate delegate;
    private static List<String> PROPERTY_CONFIG_KEYS = Arrays.asList("consoleOutput", "outputFile", "logLevel", "logFormat");

    //Action dict: key: propertyKey; value: action method corresponding to that property.
    Map<String, Command> methodMap = new HashMap<String, Command>();

    @SuppressWarnings("unused") //Wont be called
    private LoggerPropertyLoader() {

    }

    public LoggerPropertyLoader(PropertyApplyingDelegate aDelegate) {
        initMethodMap();
        delegate = aDelegate;
    }

    /**
     * Will load the method dictionary, one key:action for each available property.
     */
    private void initMethodMap() {
        methodMap.put("consoleOutput", new Command() {
            public void applyPropertyWithValue(String propertyKey, String value) throws WrongPropertyFormatException {
                applyConsoleOutputProperty(propertyKey, value);
            }
        });

        methodMap.put("outputFile", new Command() {
            public void applyPropertyWithValue(String propertyKey, String value) throws IOException {
                applyOutputFileProperty(propertyKey, value);
            }
        });

        methodMap.put("logLevel", new Command() {
            public void applyPropertyWithValue(String propertyKey, String value) throws WrongPropertyFormatException {
                applyLogLevelProperty(propertyKey, value);
            }
        });

        methodMap.put("logFormat", new Command() {
            public void applyPropertyWithValue(String propertyKey, String value) throws WrongPropertyFormatException {
                applyLogFormatProperty(propertyKey, value);
            }
        });
    }


    private void applyLogFormatProperty(String property, String fileValue) throws WrongPropertyFormatException {
        delegate.applyLogFormatProperty(property, fileValue);
    }

    private void applyLogLevelProperty(String property, String fileValue) throws WrongPropertyFormatException {
        delegate.applyLogLevelProperty(property, fileValue);
    }

    private void applyOutputFileProperty(String property, String fileValue) throws IOException {
        List<String> fileList = Arrays.asList(fileValue.split(","));
        for (String file : fileList)
            delegate.applyOutputFileProperty(property, file);
    }

    private void applyConsoleOutputProperty(String property, String fileValue) throws WrongPropertyFormatException {
        delegate.applyConsoleOutputProperty(property, fileValue);
    }

    /**
     * @param filename the filename to load the config from.
     * @throws Exception Will call the corresponding action method stored in method dictionary.
     */
    public void loadConfigFromFile(String filename) throws Exception {
        FileInputStream inputStream = new FileInputStream(filename);
        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();
        for (String propertyKey : PROPERTY_CONFIG_KEYS) {
            String propertyValue = properties.getProperty(propertyKey, "null");
            if (!propertyValue.equals("null"))
                methodMap.get(propertyKey).applyPropertyWithValue(propertyKey, propertyValue);
        }
    }
}
