package logger.filters;

/**
 * Created by gonchub on 13/05/14.
 * Interface for the format filters.
 */
public interface FormatFilterInterface {

    /**
     * Filters the given message.
     *
     * @param message the message to filter.
     * @return a new string with the filter value.
     */
    public String filter(String message);

    /**
     * Clears its part of the filter from the message. Used for validation.
     *
     * @param message the message to clear.
     * @return the cleared message.
     */
    public String clear(String message);

}
