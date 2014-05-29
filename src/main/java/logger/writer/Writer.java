package logger.writer;

/**
 * Created by Tomas on 29/05/2014.
 */
public interface Writer {

    /**
     * Writes the message to a given output.
     *
     * @param message the message to be written.
     */
    public void write(String message);

}
