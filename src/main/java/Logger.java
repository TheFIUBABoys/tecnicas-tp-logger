import level.*;

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
    private static LogFormat logFormat = new LogFormat();
    private static ArrayList<BufferedWriter> outputFiles = new ArrayList<BufferedWriter>();
    private static Boolean terminalOutput = true;

    public static LogLevel LEVEL_DEBUG = new LevelDebug();
    public static LogLevel LEVEL_INFO = new LevelInfo();
    public static LogLevel LEVEL_WARN = new LevelWarn();
    public static LogLevel LEVEL_ERROR = new LevelError();
    public static LogLevel LEVEL_FATAL = new LevelFatal();
    public static LogLevel LEVEL_OFF = new LevelOff();

    public static void setMessageFormat(LogFormat messageFormat) {
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

        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        outputFiles.add(bw);
    }

    public static void setLogLevel(LogLevel logLevel) {
        logLevelSet = logLevel;
    }

    public static void logMessage(String message, LogLevel logLevel) {
        // Check if log level is lower than the one set. If so, execute the logging.
        if (logLevel.compareTo(logLevelSet) < 0) {
            executeLog(message, logLevel);
        }
    }

    private static void writeInBuffer(BufferedWriter bw, String message) {
        try {
            bw.write(message);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void executeLog(String message, LogLevel logLevel) {
        String formattedMessage = logFormat.formatLogMessage(message, logLevel);
        if (terminalOutput) {
            System.out.print(formattedMessage);
        }
        for (BufferedWriter bw : outputFiles) {
            writeInBuffer(bw, formattedMessage);
        }
    }

}
