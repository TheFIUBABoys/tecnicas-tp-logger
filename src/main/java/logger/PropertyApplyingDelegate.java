package logger;

import loggerExceptions.WrongPropertyFormatException;

import java.io.IOException;

/**
 * @author Lucas
 *         Create LoggerPropertyLoader and implement this delegate methods to load properties from property file.
 */
public interface PropertyApplyingDelegate {

    /**
     * Applies the LogFormat property.
     *
     * @param property  the name of the property.
     * @param fileValue the value of the log format property.
     * @throws WrongPropertyFormatException if the log format is invalid.
     */
    public void applyLogFormatProperty(String property, String fileValue) throws WrongPropertyFormatException;

    /**
     * Applies the LogLevel property.
     *
     * @param property  the name of the property.
     * @param fileValue the value of the log logger.level property.
     * @throws WrongPropertyFormatException if the log logger.level is not a valid one.
     */
    public void applyLogLevelProperty(String property, String fileValue) throws WrongPropertyFormatException;

    /**
     * Applies the output file property.
     *
     * @param property  the name of the property.
     * @param fileValue the name of the filename.
     * @throws IOException if there was an error adding the file as output.
     */
    public void applyOutputFileProperty(String property, String fileValue) throws IOException;

    /**
     * Applies the console output property.
     *
     * @param property  the name of the property.
     * @param fileValue the boolean value of the console output property.
     * @throws WrongPropertyFormatException if the value is not a valid one (true/false).
     */
    public void applyConsoleOutputProperty(String property, String fileValue) throws WrongPropertyFormatException;
}