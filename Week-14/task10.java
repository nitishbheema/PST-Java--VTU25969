import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;

class FileProcessing {

    private static final Logger logger = LogManager.getLogger(FileProcessing.class);

    public void processFile(String filename) {

        if (filename == null || filename.isBlank()) {
            logger.error("Invalid filename");
            return;
        }

        File file = new File(filename);

        if (!file.exists()) {
            logger.warn("File not found");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.readLine() != null) {
                logger.debug("Reading line");
            }
            logger.info("File processed");
        } catch (IOException e) {
            logger.error("Error reading file");
        }
    }
}
