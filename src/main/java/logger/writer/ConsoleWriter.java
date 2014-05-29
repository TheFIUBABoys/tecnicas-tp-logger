package logger.writer;

/**
 * Created by Tomas on 29/05/2014.
 */
public class ConsoleWriter implements Writer{

    public static String FILENAME = "Console";

    @Override
    public void write(String message) {
        System.out.print(message);
    }
}
