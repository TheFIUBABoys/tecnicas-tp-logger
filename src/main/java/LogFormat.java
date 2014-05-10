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

    public String formatLogMessage(String message, LogLevel level, Integer line, String fileName, String method) {
        String replaced = formatString;

        replaced = replaced.replaceAll(dateRegex, (new Date()).toString());
        replaced = replaced.replaceAll(levelRegex, level.toString());
        replaced = replaced.replaceAll(messageRegex, message);
        replaced = replaced.replaceAll(threadRegex, "THREAD");
        replaced = replaced.replaceAll(separatorRegex, "\n");
        replaced = replaced.replaceAll(lineRegex, line.toString());
        replaced = replaced.replaceAll(filenameRegex, fileName);
        replaced = replaced.replaceAll(methodRegex, fileName);
        replaced = replaced.replaceAll(percentRegex, "%");

        return replaced;
    }
}
