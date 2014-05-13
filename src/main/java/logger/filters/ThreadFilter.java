package logger.filters;

import java.util.ArrayList;

/**
 * Created by gonchub on 13/05/14.
 * Filter for thread dependant strings.
 */
public class ThreadFilter implements FormatFilter {

    private final String threadRegex = "%t";
    private final String lineRegex = "%L";
    private final String filenameRegex = "%F";
    private final String methodRegex = "%M";

    /**
     * {@inheritDoc}
     */
    public String filter(String message) {
        String filtered = message;
        Thread current = Thread.currentThread();
        StackTraceElement[] stackTrace = current.getStackTrace();
        StackTraceElement stackTraceElement = current.getStackTrace()[2];
        for (Integer i = 0; i < stackTrace.length; i++) {
            if (stackTrace[i].getClassName().equals(getClass().getName())) {
                stackTraceElement = stackTrace[i + 2];
                break;
            }
        }

        filtered = filtered.replaceAll(threadRegex, current.getName());
        filtered = filtered.replaceAll(lineRegex, "" + stackTraceElement.getLineNumber());
        filtered = filtered.replaceAll(filenameRegex, stackTraceElement.getFileName());
        filtered = filtered.replaceAll(methodRegex, stackTraceElement.getMethodName());
        return filtered;
    }

    /**
     * {@inheritDoc}
     */
    public String clear(String message) {
        String filtered = message;
        filtered = filtered.replaceAll(threadRegex, "");
        filtered = filtered.replaceAll(lineRegex, "");
        filtered = filtered.replaceAll(filenameRegex, "");
        filtered = filtered.replaceAll(methodRegex, "");
        return filtered;
    }

}
