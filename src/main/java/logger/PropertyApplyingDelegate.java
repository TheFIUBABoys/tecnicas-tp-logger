package logger;

import java.io.IOException;

import loggerExceptions.WrongPropertyFormatException;

/**
 * 
 * @author Lucas
 * Create LoggerPropertyLoader & implement this delegate methods to load properties from property file.
 * 
 */
public interface PropertyApplyingDelegate{
	public void applyLogFormatProperty(String property, String fileValue) throws WrongPropertyFormatException;
	public void applyLogLevelProperty(String property, String fileValue) throws WrongPropertyFormatException;
	public void applyOutputFileProperty(String property, String fileValue) throws IOException;
	public void applyConsoleOutputProperty(String property, String fileValue) throws WrongPropertyFormatException;
}