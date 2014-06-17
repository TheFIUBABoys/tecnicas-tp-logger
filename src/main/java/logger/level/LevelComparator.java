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
                //Regular
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

        private void addAntisymetricItemToTable(String k1, String k2, LogLevelComparisonResult item){
            comparisonTable.put(k1, k2, item);
            if (!k1.equals(k2)) {
                comparisonTable.put(k2, k1, item.getOpposite());
            }
        }

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
            String debugId = LogLevel.LEVEL_DEBUG.toString();
            addAntisymetricItemToTable(debugId, LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(debugId, LogLevel.LEVEL_FATAL.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(debugId, LogLevel.LEVEL_ERROR.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(debugId, LogLevel.LEVEL_WARN.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(debugId, LogLevel.LEVEL_TRACE.toString(),LogLevelComparisonResult.resultGreater);
            addAntisymetricItemToTable(debugId,debugId,LogLevelComparisonResult.resultEqual);
            addAntisymetricItemToTable(debugId, LogLevel.LEVEL_INFO.toString(),LogLevelComparisonResult.resultLesser);
        }

        private void initLevelInfoComparison() {
            String infoId = LogLevel.LEVEL_INFO.toString();
            addAntisymetricItemToTable(infoId, LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(infoId, LogLevel.LEVEL_FATAL.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(infoId, LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(infoId, LogLevel.LEVEL_ERROR.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(infoId, LogLevel.LEVEL_WARN.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(infoId, LogLevel.LEVEL_TRACE.toString(),LogLevelComparisonResult.resultGreater);
            addAntisymetricItemToTable(infoId,infoId,LogLevelComparisonResult.resultEqual);
        }

        private void initLevelTraceComparison() {
            String traceId = LogLevel.LEVEL_TRACE.toString();
            addAntisymetricItemToTable(traceId, LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(traceId, LogLevel.LEVEL_FATAL.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(traceId, LogLevel.LEVEL_ERROR.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(traceId, LogLevel.LEVEL_WARN.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(traceId, traceId,LogLevelComparisonResult.resultEqual);

        }

        private void initLevelErrorComparison() {
            String errorId = LogLevel.LEVEL_ERROR.toString();
            addAntisymetricItemToTable(errorId, LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(errorId, LogLevel.LEVEL_FATAL.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(errorId, errorId,LogLevelComparisonResult.resultEqual);
            addAntisymetricItemToTable(errorId, LogLevel.LEVEL_WARN.toString(),LogLevelComparisonResult.resultGreater);
        }

        private void initLevelWarnComparison() {
            String warnId = LogLevel.LEVEL_WARN.toString();
            addAntisymetricItemToTable(warnId, LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(warnId, LogLevel.LEVEL_FATAL.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(warnId, warnId,LogLevelComparisonResult.resultEqual);
        }

        private void initLevelFatalComparison() {
            String fatalId = LogLevel.LEVEL_FATAL.toString();
            addAntisymetricItemToTable(fatalId, LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultLesser);
            addAntisymetricItemToTable(fatalId, fatalId,LogLevelComparisonResult.resultEqual);
        }

        private void initLevelOffComparison() {
            addAntisymetricItemToTable(LogLevel.LEVEL_OFF.toString(), LogLevel.LEVEL_OFF.toString(),LogLevelComparisonResult.resultEqual);
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

        /**
         * Compares the first level to the second one
         * @return LevelComparisonResult, from the perspective of levelOne
         */
        public LogLevelComparisonResult compareLevelToLevel(LogLevel levelOne, LogLevel levelTwo){
            return comparisonTable.get(levelOne.toString(),levelTwo.toString());
        }


    }
