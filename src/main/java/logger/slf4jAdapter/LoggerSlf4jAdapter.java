package logger.slf4jAdapter;

import logger.Logger;
import logger.LoggerImpl;
import logger.level.LogLevel;
import logger.level.LogLevelComparisonResult;
import org.slf4j.Marker;

/**
 * Created by Lucas on 5/30/2014.
 */
public class LoggerSlf4jAdapter implements org.slf4j.Logger {
    private Logger logger;

    public LoggerSlf4jAdapter(){
        logger =  LoggerImpl.getLogger();
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isTraceEnabled() {
       return  ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_TRACE))== LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void trace(String s) {
        this.logger.logMessage(s, LogLevel.LEVEL_TRACE);
    }

    @Override
    public void trace(String s, Object o) {

    }

    @Override
    public void trace(String s, Object o, Object o2) {

    }

    @Override
    public void trace(String s, Object... objects) {

    }

    @Override
    public void trace(String s, Throwable throwable) {

    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return  ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_TRACE))== LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void trace(Marker marker, String s) {
        this.logger.logMessage(s, LogLevel.LEVEL_TRACE);
    }

    @Override
    public void trace(Marker marker, String s, Object o) {

    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o2) {

    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {

    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isDebugEnabled() {
        return  ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_DEBUG))== LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void debug(String s) {
        this.logger.logMessage(s, LogLevel.LEVEL_DEBUG);
    }

    @Override
    public void debug(String s, Object o) {

    }

    @Override
    public void debug(String s, Object o, Object o2) {

    }

    @Override
    public void debug(String s, Object... objects) {

    }

    @Override
    public void debug(String s, Throwable throwable) {

    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return  ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_DEBUG))== LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void debug(Marker marker, String s) {
        this.logger.logMessage(s, LogLevel.LEVEL_DEBUG);
    }

    @Override
    public void debug(Marker marker, String s, Object o) {

    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o2) {

    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {

    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isInfoEnabled() {
        return  ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_INFO))== LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void info(String s) {
        this.logger.logMessage(s, LogLevel.LEVEL_INFO);
    }

    @Override
    public void info(String s, Object o) {

    }

    @Override
    public void info(String s, Object o, Object o2) {

    }

    @Override
    public void info(String s, Object... objects) {

    }

    @Override
    public void info(String s, Throwable throwable) {

    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return  ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_INFO))== LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void info(Marker marker, String s) {
        this.logger.logMessage(s, LogLevel.LEVEL_INFO);
    }

    @Override
    public void info(Marker marker, String s, Object o) {

    }

    @Override
    public void info(Marker marker, String s, Object o, Object o2) {

    }

    @Override
    public void info(Marker marker, String s, Object... objects) {

    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isWarnEnabled() {
        return  ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_WARN))== LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void warn(String s) {
        this.logger.logMessage(s, LogLevel.LEVEL_WARN);
    }

    @Override
    public void warn(String s, Object o) {

    }

    @Override
    public void warn(String s, Object... objects) {

    }

    @Override
    public void warn(String s, Object o, Object o2) {

    }

    @Override
    public void warn(String s, Throwable throwable) {

    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return  ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_WARN))== LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void warn(Marker marker, String s) {
        this.logger.logMessage(s, LogLevel.LEVEL_WARN);
    }

    @Override
    public void warn(Marker marker, String s, Object o) {

    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o2) {

    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {

    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isErrorEnabled() {
        return  ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_ERROR))== LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void error(String s) {
        this.logger.logMessage(s, LogLevel.LEVEL_ERROR);
    }

    @Override
    public void error(String s, Object o) {

    }

    @Override
    public void error(String s, Object o, Object o2) {

    }

    @Override
    public void error(String s, Object... objects) {

    }

    @Override
    public void error(String s, Throwable throwable) {

    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return  ((logger.getLogLevel().compareToLevel(LogLevel.LEVEL_ERROR))== LogLevelComparisonResult.resultEqual);
    }

    @Override
    public void error(Marker marker, String s) {
        this.logger.logMessage(s, LogLevel.LEVEL_ERROR);
    }

    @Override
    public void error(Marker marker, String s, Object o) {

    }

    @Override
    public void error(Marker marker, String s, Object o, Object o2) {

    }

    @Override
    public void error(Marker marker, String s, Object... objects) {

    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {
        this.logger.logMessage(s, LogLevel.LEVEL_ERROR);
    }
}
