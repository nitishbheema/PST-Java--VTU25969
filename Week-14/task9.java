import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class LoggingDemo {

    private static final Logger logger = LogManager.getLogger(LoggingDemo.class);

    public void demoLogs() {
        logger.info("Info message");
        logger.debug("Debug message");
        logger.error("Error message");
    }
}
