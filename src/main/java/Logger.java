import level.LevelDebug;
import level.LogLevel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by GonchuB on 09/05/2014.
 * FIUBA
 */
public class Logger {
    private static LogLevel logLevelSet = new LevelDebug();
    // TODO: LogMessageFormat class.
    private static String logFormat = "DEFAULT";
    private static ArrayList<BufferedWriter> outputFiles = new ArrayList<BufferedWriter>();
    private static Boolean terminalOutput = true;

    public static void setMessageFormat(String messageFormat) {
        logFormat = messageFormat;
    }

    public static void setConsoleOutput(Boolean value) {
        terminalOutput = value;
    }

    public static void addOutputFile(String filename) throws IOException {
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        outputFiles.add(bw);
    }

    public static void setLogLevel(LogLevel logLevel) {
        logLevelSet = logLevel;
    }

    public static void logMessage(String message, LogLevel logLevel) {
        // Check if log level is lower than the one set. If so, execute the logging.
        if (logLevel.compareTo(logLevelSet) < 0) {
            executeLog(message);
        }
    }

    private static void executeLog(String message) {
        //TODO: do the actual logging.
        if (terminalOutput && logFormat.equals("DEFAULT")) {
            System.out.println(message);
        }
    }

}
