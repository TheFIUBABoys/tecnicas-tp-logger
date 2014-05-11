package level;

/**
 * Created by GonchuB on 09/05/2014.
 * FIUBA
 */
public class LevelOff implements LogLevel {

    /**
     * {@inheritDoc}
     */
    public int compareTo(LogLevel logLevel) {
        return logLevel.compareTo(this);
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(LevelDebug logLevel) {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(LevelInfo logLevel) {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(LevelWarn logLevel) {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(LevelError logLevel) {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(LevelFatal logLevel) {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(LevelOff logLevel) {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "OFF";
    }
}
