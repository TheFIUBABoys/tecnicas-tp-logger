package loggerExceptions;

/**
 * Created by GonchuB on 11/05/2014.
 * Exception thrown when LogFormat receives an invalid format string on it's constructor.
 */
public class InvalidFormatException extends Exception {

    public InvalidFormatException(String message) {
        super(message);
    }

}
