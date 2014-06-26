package logger.level;

/**
 * Created by GonchuB on 09/05/2014.
 * The responsibility of this class is to provide an interface
 * for the different types of logging levels.
 */


public enum LogLevel {

    /**
     * Easy access to debugging logger.level types.
     * Example of semantics: LogLevel.LEVEL_DEBUG
     */
    LEVEL_TRACE {
        @Override
        public String toString() {
            return "TRACE";
        }
    },

    LEVEL_DEBUG {
        @Override

        public String toString() {
            return "DEBUG";
        }
    },
    LEVEL_INFO {
        @Override

        public String toString() {
            return "INFO";
        }
    },
    LEVEL_WARN {
        @Override

        public String toString() {
            return "WARN";
        }
    },
    LEVEL_ERROR {
        @Override
        public String toString() {
            return "ERROR";
        }
    },
    LEVEL_FATAL {
        @Override
        public String toString() {
            return "FATAL";
        }
    },
    LEVEL_OFF {
        @Override
        public String toString() {
            return "OFF";
        }
    };
}
