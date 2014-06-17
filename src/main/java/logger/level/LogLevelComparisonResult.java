package logger.level;

/**
 * Created by Lucas on 5/31/2014.
 * Comparison result constants.
 */
public enum LogLevelComparisonResult {
    resultEqual{
        @Override
        public LogLevelComparisonResult getOpposite() {
            return resultEqual;
        }
    },
    resultLesser{
        @Override
        public LogLevelComparisonResult getOpposite() {
            return resultGreater;
        }
    },
    resultGreater{
        @Override
        public LogLevelComparisonResult getOpposite() {
            return resultLesser;
        }
    };

    public abstract LogLevelComparisonResult getOpposite();
}
