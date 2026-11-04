package safetydetector;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DetectionLogger {

    private static final String LOG_FILE = "detections.txt";

    private final KeywordFileHandler fileHandler;

    public DetectionLogger() {
        fileHandler = new KeywordFileHandler();
    }

    public void log(DetectionResult result) {

        // Don't log safe messages
        if (result.getRiskLevel() == RiskLevel.SAFE) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        StringBuilder logEntry = new StringBuilder();

        logEntry.append("====================================\n");
        logEntry.append("Date: ")
                .append(now.format(formatter))
                .append("\n\n");

        logEntry.append("Message:\n")
                .append(result.getMessage())
                .append("\n\n");

        logEntry.append("Risk Level:\n")
                .append(result.getRiskLevel())
                .append("\n\n");

        logEntry.append("Matched Keyword:\n")
                .append(result.getMatchedKeyword())
                .append("\n\n");

        logEntry.append("Reason:\n")
                .append(result.getReason())
                .append("\n");

        logEntry.append("====================================\n");

        fileHandler.write(LOG_FILE, logEntry.toString());
    }
}
