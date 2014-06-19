package logger;

public class TestApplication {

    public static void main(String args[]) {

        org.junit.runner.JUnitCore.main(
                "logger.LoggerTest",
                "logger.LoggerImplTest",
                "logger.LoggerLoadPropertiesTest",
                "logger.LogFormatTest",
                "logger.LoggerLoadXMLTest",
                "logger.LoggerLoadXMLTest",
                "logger.filters.DateFilterTest",
                "logger.filters.LevelFilterTest",
                "logger.filters.LoggerNameFilterTest",
                "logger.filters.MessageFilterTest",
                "logger.filters.PercentFilterTest",
                "logger.filters.SeparatorFilterTest",
                "logger.filters.ThreadFilterTest",
                "logger.filters.custom.UserFilterImplTest",
                "logger.format.LogContainerTest",
                "logger.level.LevelDebugTest",
                "logger.level.LevelErrorTest",
                "logger.level.LevelFatalTest",
                "logger.level.LevelInfoTest",
                "logger.level.LevelOffTest",
                "logger.level.LevelTraceTest",
                "logger.level.LevelWarnTest",
                "logger.level.LogLevelFactoryTest",
                "logger.slf4jAdapter.LoggerSlf4jAdapterTest");

    }

}
