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

    private static Logger loggerInstance = null;

    private LogLevel logLevelSet;
    private LogFormat logFormat;
    private ArrayList<BufferedWriter> outputFiles;
    private Boolean terminalOutput;

    public static LogLevel LEVEL_DEBUG = new LevelDebug();
    public static LogLevel LEVEL_INFO = new LevelInfo();
    public static LogLevel LEVEL_WARN = new LevelWarn();
    public static LogLevel LEVEL_ERROR = new LevelError();
    public static LogLevel LEVEL_FATAL = new LevelFatal();
    public static LogLevel LEVEL_OFF = new LevelOff();

    private Logger() {
        logLevelSet = new LevelDebug();
        logFormat = new LogFormat();
        outputFiles = new ArrayList<BufferedWriter>();
        terminalOutput = true;
    }

    public static Logger getLogger() {
        if (loggerInstance == null) {
            loggerInstance = new Logger();
        }
        return loggerInstance;
    }

    public void setMessageFormat(LogFormat messageFormat) {
        logFormat = messageFormat;
    }

    public void setConsoleOutput(Boolean value) {
        terminalOutput = value;
    }

    public void addOutputFile(String filename) throws IOException {
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        outputFiles.add(bw);
    }

    public void setLogLevel(LogLevel logLevel) {
        logLevelSet = logLevel;
    }

    public void logMessage(String message, LogLevel logLevel) {
        // Check if log level is lower than the one set. If so, execute the logging.
        if (logLevel.compareTo(logLevelSet) < 0) {
            executeLog(message, logLevel);
        }
    }

    private void writeInBuffer(BufferedWriter bw, String message) {
        try {
            bw.write(message);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeLog(String message, LogLevel logLevel) {
        String formattedMessage = logFormat.formatLogMessage(message, logLevel);
        if (terminalOutput) {
            System.out.print(formattedMessage);
        }
        for (BufferedWriter bw : outputFiles) {
            writeInBuffer(bw, formattedMessage);
        }
    }

}
