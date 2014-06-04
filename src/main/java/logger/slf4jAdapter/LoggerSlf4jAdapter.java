package logger.slf4jAdapter;

import logger.Logger;
import logger.LoggerImpl;
import logger.level.LogLevel;
import logger.level.LogLevelComparisonResult;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * Created by Lucas on 5/30/2014.
 */
public class LoggerSlf4jAdapter implements org.slf4j.Logger {
    private Logger logger;

    private void logFormattedMessage(String message, LogLevel logLevel, Object... objects) {
        FormattingTuple formatted = MessageFormatter.arrayFormat(message, objects);
        logger.logMessage(formatted.getMessage(),logLevel);
    }

    public LoggerSlf4jAdapter() {
        logger = LoggerImpl.getLogger();
        logger.setConsoleOutput(true);
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_TRACE)) == LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void trace(String s) {
        this.logger.setLogLevel(LogLevel.LEVEL_TRACE);
        this.logger.logMessage(s, LogLevel.LEVEL_TRACE);
    }

    @Override
    public void trace(String s, Object o) {
        this.logger.setLogLevel(LogLevel.LEVEL_TRACE);
        logFormattedMessage(s, LogLevel.LEVEL_TRACE, o);
    }

    @Override
    public void trace(String s, Object o, Object o2) {
        this.logger.setLogLevel(LogLevel.LEVEL_TRACE);
        logFormattedMessage(s, LogLevel.LEVEL_TRACE, o, o2);
    }

    @Override
    public void trace(String s, Object... objects) {
        this.logger.setLogLevel(LogLevel.LEVEL_TRACE);
        logFormattedMessage(s, LogLevel.LEVEL_TRACE, objects);
    }

    @Override
    public void trace(String s, Throwable throwable) {
        this.logger.setLogLevel(LogLevel.LEVEL_TRACE);
        this.logger.logMessage(s, LogLevel.LEVEL_TRACE, throwable);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_TRACE)) == LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void trace(Marker marker, String s) {
        this.trace(s, LogLevel.LEVEL_TRACE);
    }

    @Override
    public void trace(Marker marker, String s, Object o) {
        this.trace(s, o);
    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o2) {
        this.trace(s, o, o2);
    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {
        this.trace(s, objects);
    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {
        this.trace(s, throwable);
    }

    @Override
    public boolean isDebugEnabled() {
        return ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_DEBUG)) == LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void debug(String s) {
        this.logger.setLogLevel(LogLevel.LEVEL_DEBUG);
        this.logger.logMessage(s, LogLevel.LEVEL_DEBUG);
    }

    @Override
    public void debug(String s, Object o) {
        this.logger.setLogLevel(LogLevel.LEVEL_DEBUG);
        logFormattedMessage(s, LogLevel.LEVEL_DEBUG, o);
    }

    @Override
    public void debug(String s, Object o, Object o2) {
        this.logger.setLogLevel(LogLevel.LEVEL_DEBUG);
        logFormattedMessage(s, LogLevel.LEVEL_DEBUG, o, o2);
    }

    @Override
    public void debug(String s, Object... objects) {
        this.logger.setLogLevel(LogLevel.LEVEL_DEBUG);
        logFormattedMessage(s, LogLevel.LEVEL_DEBUG, objects);
    }

    @Override
    public void debug(String s, Throwable throwable) {
        this.logger.setLogLevel(LogLevel.LEVEL_DEBUG);
        this.logger.logMessage(s, LogLevel.LEVEL_DEBUG, throwable);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_DEBUG)) == LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void debug(Marker marker, String s) {
        this.debug(s, LogLevel.LEVEL_DEBUG);
    }

    @Override
    public void debug(Marker marker, String s, Object o) {
        this.debug(s, o);
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o2) {
        this.debug(s, o, o2);
    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {
        this.debug(s, objects);
    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {
        this.debug(s, throwable);
    }

    @Override
    public boolean isInfoEnabled() {
        return ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_INFO)) == LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void info(String s) {
        this.logger.setLogLevel(LogLevel.LEVEL_INFO);
        this.logger.logMessage(s, LogLevel.LEVEL_INFO);
    }

    @Override
    public void info(String s, Object o) {
        this.logger.setLogLevel(LogLevel.LEVEL_INFO);
        logFormattedMessage(s, LogLevel.LEVEL_INFO, o);
    }

    @Override
    public void info(String s, Object o, Object o2) {
        this.logger.setLogLevel(LogLevel.LEVEL_INFO);
        logFormattedMessage(s, LogLevel.LEVEL_INFO, o, o2);
    }

    @Override
    public void info(String s, Object... objects) {
        this.logger.setLogLevel(LogLevel.LEVEL_INFO);
        logFormattedMessage(s, LogLevel.LEVEL_INFO, objects);
    }

    @Override
    public void info(String s, Throwable throwable) {
        this.logger.setLogLevel(LogLevel.LEVEL_INFO);
        this.logger.logMessage(s, LogLevel.LEVEL_INFO, throwable);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_INFO)) == LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void info(Marker marker, String s) {
        this.info(s, LogLevel.LEVEL_INFO);
    }

    @Override
    public void info(Marker marker, String s, Object o) {
        this.info(s, o);
    }

    @Override
    public void info(Marker marker, String s, Object o, Object o2) {
        this.info(s, o, o2);
    }

    @Override
    public void info(Marker marker, String s, Object... objects) {
        this.info(s, objects);
    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {
        this.info(s, throwable);
    }

    @Override
    public boolean isWarnEnabled() {
        return ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_WARN)) == LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void warn(String s) {
        this.logger.setLogLevel(LogLevel.LEVEL_WARN);
        this.logger.logMessage(s, LogLevel.LEVEL_WARN);
    }

    @Override
    public void warn(String s, Object o) {
        this.logger.setLogLevel(LogLevel.LEVEL_WARN);
        logFormattedMessage(s, LogLevel.LEVEL_WARN, o);
    }

    @Override
    public void warn(String s, Object... objects) {
        this.logger.setLogLevel(LogLevel.LEVEL_WARN);
        logFormattedMessage(s, LogLevel.LEVEL_WARN, objects);
    }

    @Override
    public void warn(String s, Object o, Object o2) {
        this.logger.setLogLevel(LogLevel.LEVEL_WARN);
        logFormattedMessage(s, LogLevel.LEVEL_WARN, o, o2);
    }

    @Override
    public void warn(String s, Throwable throwable) {
        this.logger.setLogLevel(LogLevel.LEVEL_WARN);
        this.logger.logMessage(s, LogLevel.LEVEL_WARN, throwable);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_WARN)) == LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void warn(Marker marker, String s) {
        this.warn(s);
    }

    @Override
    public void warn(Marker marker, String s, Object o) {
        this.warn(s, o);
    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o2) {
        this.warn(s, o, o2);
    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {
        this.warn(s, objects);
    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {
        this.warn(s, throwable);
    }

    @Override
    public boolean isErrorEnabled() {
        return ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_ERROR)) == LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void error(String s) {
        this.logger.setLogLevel(LogLevel.LEVEL_ERROR);
        this.logger.logMessage(s, LogLevel.LEVEL_ERROR);
    }

    @Override
    public void error(String s, Object o) {
        this.logger.setLogLevel(LogLevel.LEVEL_ERROR);
        logFormattedMessage(s, LogLevel.LEVEL_ERROR, o);
    }

    @Override
    public void error(String s, Object o, Object o2) {
        this.logger.setLogLevel(LogLevel.LEVEL_ERROR);
        logFormattedMessage(s, LogLevel.LEVEL_ERROR, o, o2);
    }

    @Override
    public void error(String s, Object... objects) {
        this.logger.setLogLevel(LogLevel.LEVEL_ERROR);
        logFormattedMessage(s, LogLevel.LEVEL_ERROR, objects);
    }

    @Override
    public void error(String s, Throwable throwable) {
        this.logger.setLogLevel(LogLevel.LEVEL_ERROR);
        this.logger.logMessage(s, LogLevel.LEVEL_ERROR, throwable);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_ERROR)) == LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void error(Marker marker, String s) {
        this.error(s, LogLevel.LEVEL_ERROR);
    }

    @Override
    public void error(Marker marker, String s, Object o) {
        this.error(s, o);
    }

    @Override
    public void error(Marker marker, String s, Object o, Object o2) {
        this.error(s, o, o2);
    }

    @Override
    public void error(Marker marker, String s, Object... objects) {
        this.error(s, objects);
    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {
        this.error(s, LogLevel.LEVEL_ERROR);
    }
}
