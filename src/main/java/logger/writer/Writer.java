package logger.writer;

/**
 * @author Tomas
 *         Defines Writer interface to be implemented by every type of writer to be used by the logger.
 */
public interface Writer {

    /**
     * Writes the message to a given output.
     *
     * @param message the message to be written.
     */
    public void write(String message);

}
