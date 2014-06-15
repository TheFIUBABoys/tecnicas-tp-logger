package logger.level;


import java.util.HashMap;
import java.util.Map;



    /**
     * Created by Lucas on 6/15/2014.
     * Knows how to compare 2 log levels
    */
    public class LevelComparator {
        /**
         * Created by Lucas on 6/15/2014.
         * Just a Map of maps facade to use it as a matrix.
         */
        private class Matrix<K,Value>{
            Map<K,Map<K,Value>> baseMap;
            public Matrix(){
                baseMap = new HashMap<K,Map<K,Value>>();
            }
            public void put(K key1, K key2, Value value){
                Map<K,Value> subMap;
                if (baseMap.containsKey(key1)){
                    subMap = baseMap.get(key1);
                    subMap.put(key2,value);
                }else {
                    subMap = new HashMap <K,Value>();
                    subMap.put(key2,value);
                    baseMap.put(key1,subMap);
                }


            }

            public Value get(K key1, K key2){
                return baseMap.get(key1).get(key2);
            }
        }

        Matrix<String,LogLevelComparisonResult> comparisonTable = new Matrix<String, LogLevelComparisonResult>();;
        private static LevelComparator levelComparator = null;

        private void initComparisonTable(){
            initLevelOffComparison();
            initLevelFatalComparison();
            initLevelErrorComparison();
            initLevelWarnComparison();
            initLevelTraceComparison();
            initLevelDebugComparison();
            initLevelInfoComparison();

        }

        private void initLevelDebugComparison() {
            comparisonTable.put(LogLevel.LEVEL_DEBUG.toString(), LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_DEBUG.toString(), LogLevel.LEVEL_FATAL.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_DEBUG.toString(), LogLevel.LEVEL_ERROR.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_DEBUG.toString(), LogLevel.LEVEL_WARN.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_DEBUG.toString(), LogLevel.LEVEL_TRACE.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_DEBUG.toString(), LogLevel.LEVEL_DEBUG.toString(),LogLevelComparisonResult.resultEqual);
            comparisonTable.put(LogLevel.LEVEL_DEBUG.toString(), LogLevel.LEVEL_INFO.toString(),LogLevelComparisonResult.resultLesser);
        }

        private void initLevelInfoComparison() {
            comparisonTable.put(LogLevel.LEVEL_INFO.toString(), LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_INFO.toString(), LogLevel.LEVEL_FATAL.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_INFO.toString(), LogLevel.LEVEL_ERROR.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_INFO.toString(), LogLevel.LEVEL_WARN.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_INFO.toString(), LogLevel.LEVEL_TRACE.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_INFO.toString(), LogLevel.LEVEL_DEBUG.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_INFO.toString(), LogLevel.LEVEL_INFO.toString(),LogLevelComparisonResult.resultEqual);
        }

        private void initLevelTraceComparison() {
            comparisonTable.put(LogLevel.LEVEL_TRACE.toString(), LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_TRACE.toString(), LogLevel.LEVEL_FATAL.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_TRACE.toString(), LogLevel.LEVEL_ERROR.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_TRACE.toString(), LogLevel.LEVEL_WARN.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_TRACE.toString(), LogLevel.LEVEL_TRACE.toString(),LogLevelComparisonResult.resultEqual);
            comparisonTable.put(LogLevel.LEVEL_TRACE.toString(), LogLevel.LEVEL_DEBUG.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_TRACE.toString(), LogLevel.LEVEL_INFO.toString(),LogLevelComparisonResult.resultLesser);
        }

        private void initLevelErrorComparison() {
            comparisonTable.put(LogLevel.LEVEL_ERROR.toString(), LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_ERROR.toString(), LogLevel.LEVEL_FATAL.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_ERROR.toString(), LogLevel.LEVEL_ERROR.toString(),LogLevelComparisonResult.resultEqual);
            comparisonTable.put(LogLevel.LEVEL_ERROR.toString(), LogLevel.LEVEL_WARN.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_ERROR.toString(), LogLevel.LEVEL_TRACE.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_ERROR.toString(), LogLevel.LEVEL_DEBUG.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_ERROR.toString(), LogLevel.LEVEL_INFO.toString(),LogLevelComparisonResult.resultGreater);
        }

        private void initLevelWarnComparison() {
            comparisonTable.put(LogLevel.LEVEL_WARN.toString(), LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_WARN.toString(), LogLevel.LEVEL_FATAL.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_WARN.toString(), LogLevel.LEVEL_ERROR.toString(),LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_WARN.toString(), LogLevel.LEVEL_WARN.toString(),LogLevelComparisonResult.resultEqual);
            comparisonTable.put(LogLevel.LEVEL_WARN.toString(), LogLevel.LEVEL_TRACE.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_WARN.toString(), LogLevel.LEVEL_DEBUG.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_WARN.toString(), LogLevel.LEVEL_INFO.toString(),LogLevelComparisonResult.resultGreater);
        }

        private void initLevelFatalComparison() {
            comparisonTable.put(LogLevel.LEVEL_FATAL.toString(), LogLevel.LEVEL_OFF.toString(), LogLevelComparisonResult.resultLesser);
            comparisonTable.put(LogLevel.LEVEL_FATAL.toString(), LogLevel.LEVEL_FATAL.toString(), LogLevelComparisonResult.resultEqual);
            comparisonTable.put(LogLevel.LEVEL_FATAL.toString(), LogLevel.LEVEL_ERROR.toString(), LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_FATAL.toString(), LogLevel.LEVEL_WARN.toString(), LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_FATAL.toString(), LogLevel.LEVEL_TRACE.toString(), LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_FATAL.toString(), LogLevel.LEVEL_DEBUG.toString(), LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_FATAL.toString(), LogLevel.LEVEL_INFO.toString(), LogLevelComparisonResult.resultGreater);
        }

        private void initLevelOffComparison() {
            comparisonTable.put(LogLevel.LEVEL_OFF.toString(), LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultEqual);
            comparisonTable.put(LogLevel.LEVEL_OFF.toString(), LogLevel.LEVEL_FATAL.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_OFF.toString(), LogLevel.LEVEL_ERROR.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_OFF.toString(), LogLevel.LEVEL_WARN.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_OFF.toString(), LogLevel.LEVEL_TRACE.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_OFF.toString(), LogLevel.LEVEL_DEBUG.toString(),LogLevelComparisonResult.resultGreater);
            comparisonTable.put(LogLevel.LEVEL_OFF.toString(), LogLevel.LEVEL_INFO.toString(),LogLevelComparisonResult.resultGreater);
        }

        public static LevelComparator getInstance(){
            if (LevelComparator.levelComparator == null){
                levelComparator = new LevelComparator();
            }
            return  levelComparator;
        }

        private LevelComparator(){
            initComparisonTable();
        }

        public LogLevelComparisonResult compareLevelToLevel(LogLevel levelOne, LogLevel levelTwo){
            return comparisonTable.get(levelOne.toString(),levelTwo.toString());
        }


    }
