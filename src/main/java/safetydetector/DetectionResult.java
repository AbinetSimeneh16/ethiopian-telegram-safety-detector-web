package safetydetector;

public class DetectionResult {

    private String message;
    private String detectionCategory;
    private RiskLevel riskLevel;
    private String matchedKeyword;
    private String reason;

    public DetectionResult(
            String message,
            String detectionCategory,
            RiskLevel riskLevel,
            String matchedKeyword,
            String reason) {

        this.message = message;
        this.detectionCategory = detectionCategory;
        this.riskLevel = riskLevel;
        this.matchedKeyword = matchedKeyword;
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public String getDetectionCategory() {
        return detectionCategory;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public String getMatchedKeyword() {
        return matchedKeyword;
    }

    public String getReason() {
        return reason;
    }
}
