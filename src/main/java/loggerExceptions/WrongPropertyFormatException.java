package loggerExceptions;

public class WrongPropertyFormatException extends Exception {
    /**
	 *  Will get thrown when the properties file is malformed
	 */
	private static final long serialVersionUID = -159770524138620881L;

	public WrongPropertyFormatException(String message) {
        super(message);
    }

}
