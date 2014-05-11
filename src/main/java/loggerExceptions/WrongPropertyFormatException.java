package loggerExceptions;

public class WrongPropertyFormatException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -159770524138620881L;

	public WrongPropertyFormatException(String message) {
        super(message);
    }

    public WrongPropertyFormatException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
