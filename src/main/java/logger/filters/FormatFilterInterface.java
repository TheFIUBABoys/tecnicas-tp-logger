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
     * @param replaceParams a parameter used by the filter.
     * @return a new string with the filter value.
     */
    public String filter(String message, FilterReplaceContainer replaceParams);

    /**
     * Filters the given message.
     *
     * @param message the message to filter.
     * @param param a parameter used by the filter.
     * @return a new string with the filter value.
     */
    public String filter(String message, String param);

    /**
     * Clears its part of the filter from the message. Used for validation.
     *
     * @param message the message to clear.
     * @return the cleared message.
     */
    public String clear(String message);

}
