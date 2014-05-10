import level.LogLevel;

import java.util.Date;

/**
 * Created by GonchuB on 09/05/2014.
 * FIUBA
 */
public class LogFormat {

    private String formatString;

    private final String dateRegex = "%d";
    private final String levelRegex = "%p";
    private final String threadRegex = "%t";
    private final String messageRegex = "%m";
    private final String percentRegex = "%%";
    private final String separatorRegex = "%n";
    private final String lineRegex = "%L";
    private final String filenameRegex = "%F";
    private final String methodRegex = "%M";

    public LogFormat(String format) {
        formatString = format;
    }

    public String formatLogMessage(String message, LogLevel level) {
        String replaced = formatString;

        // These should be received.
        replaced = replaced.replaceAll(levelRegex, level.toString());
        replaced = replaced.replaceAll(messageRegex, message);

        // These are independant.
        replaced = replaced.replaceAll(dateRegex, (new Date()).toString());
        replaced = replaced.replaceAll(separatorRegex, "\n");

        // TODO: All of these should be inferred in some way.
        replaced = replaced.replaceAll(threadRegex, "THREAD");
        replaced = replaced.replaceAll(lineRegex, "LINE_NUMBER");
        replaced = replaced.replaceAll(filenameRegex, "FILENAME");
        replaced = replaced.replaceAll(methodRegex, "METHOD");

        // This one has to be replaced at the end cause it could break another regex.
        // For example %%m could have undesired behaviors.
        replaced = replaced.replaceAll(percentRegex, "%");

        return replaced;
    }
}
