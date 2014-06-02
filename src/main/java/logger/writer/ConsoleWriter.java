package logger.writer;

/**
 * @author Tomas
 *         Creates ConsoleWriter that implements Writer to write to standar o|utput.
 */
public class ConsoleWriter implements Writer {

    public static String FILENAME = "Console";

    /**
     * {@inheritDoc}
     */
    public void write(String message) {
        System.out.print(message);
    }
}
