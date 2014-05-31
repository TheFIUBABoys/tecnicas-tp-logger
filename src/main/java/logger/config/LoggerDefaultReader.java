package logger.config;

import logger.level.LogLevel;

/**
 *  Created by Tomas on 31/05/2014.
 */
public class LoggerDefaultReader extends LoggerConfigReader {
    public LoggerDefaultReader(PropertyApplyingDelegate aDelegate) {
        super(aDelegate);
    }

    /**
     * {@inheritDoc}
     */
    public void loadConfig() throws Exception {
        methodMap.get("consoleOutput").applyPropertyWithValue("consoleOutput", "true");
        methodMap.get("logLevel").applyPropertyWithValue("logLevel", LogLevel.LEVEL_INFO.toString());
    }
}
