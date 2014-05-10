package level;

/**
 * Created by GonchuB on 09/05/2014.
 * FIUBA
 */
public class LevelOff extends LogLevel {

    public int compareTo(LogLevel logLevel) {
        return logLevel.compareTo(this);
    }

    public int compareTo(LevelDebug logLevel) {
        return 1;
    }

    public int compareTo(LevelInfo logLevel) {
        return 1;
    }

    public int compareTo(LevelWarn logLevel) {
        return 1;
    }

    public int compareTo(LevelError logLevel) {
        return 1;
    }

    public int compareTo(LevelFatal logLevel) {
        return 1;
    }

    public int compareTo(LevelOff logLevel) {
        return 1;
    }

    public String toString() {
        return "OFF";
    }
}
