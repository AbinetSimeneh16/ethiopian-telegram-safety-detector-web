package safetydetector;

import java.util.List;

public class MessageAnalyzer {
    private KeywordRepository keywordRepository;
    private DetectionLogger DetectionLogger;

    public MessageAnalyzer() {
        this.keywordRepository = new KeywordRepository();
        this.DetectionLogger=new DetectionLogger();
    }

    public DetectionResult analyzeMessage(String message) {
        String lowerCaseMessage = message.toLowerCase();

        // Check harmful keywords first
        List<String> harmfulKeywords = keywordRepository.getHarmfulKeywords();
        for (String keyword : harmfulKeywords) {
            if (lowerCaseMessage.contains(keyword.toLowerCase())) {

                return new DetectionResult(
                        message,
                        "HARMFUL",
                        RiskLevel.HARMFUL,
                        keyword,
                        "Possible harmful or exploitative content detected."
                );
            }
        }
        // 2. Harmful pattern matching
        /*if (containsAllWords(lowerCaseMessage, "private", "video") ||
                containsAllWords(lowerCaseMessage, "private", "photo") ||
                containsAllWords(lowerCaseMessage, "leak", "video") ||
                containsAllWords(lowerCaseMessage, "leak", "photo") ||
                containsAllWords(lowerCaseMessage, "post", "video") ||
                containsAllWords(lowerCaseMessage, "post", "photo")) {

            return new DetectionResult(
                    message,
                    RiskLevel.HARMFUL,
                    "pattern match",
                    "Possible blackmail, privacy violation, or exploitative content detected."
            );
        }*/
        List<String> patterns1 = keywordRepository.getHarmfulPatterns();

        for (String pattern : patterns1) {
            String[] words = pattern.split(",");

            if (containsAllWords(lowerCaseMessage, words[0], words[1])) {
                return new DetectionResult(
                        message,
                        "HARMFUL",
                        RiskLevel.HARMFUL,
                        "pattern match",
                        "possible blackmail, privacy violation, or exploitative content detected."
                );
            }
        }

        // Check scam keywords
        List<String> scamKeywords = keywordRepository.getScamKeywords();
        for (String keyword : scamKeywords) {
            if (lowerCaseMessage.contains(keyword.toLowerCase())) {
                return new DetectionResult(
                        message,
                        "SCAM",
                        RiskLevel.SUSPICIOUS,
                        keyword,
                        "Possible scam or financial fraud content detected."
                );
            }
        }

        // 4. Scam pattern matching
        /*if (containsAllWords(lowerCaseMessage, "send", "money") ||
                containsAllWords(lowerCaseMessage, "pay", "now") ||
                containsAllWords(lowerCaseMessage, "click", "link") ||
                containsAllWords(lowerCaseMessage, "bank", "transfer") ||
                containsAllWords(lowerCaseMessage, "account", "blocked")) {

            return new DetectionResult(
                    message,
                    RiskLevel.SUSPICIOUS,
                    "pattern match",
                    "Possible scam, phishing, or financial fraud content detected."
            );
        }*/
        List<String> patterns2 = keywordRepository.getScamPatterns();
        for (String pattern : patterns2) {
            String[] words = pattern.split(",");

            if (containsAllWords(lowerCaseMessage, words[0], words[1])) {
                return new DetectionResult(
                        message,
                        "SCAM",
                        RiskLevel.SUSPICIOUS,
                        "pattern match",
                        "Possible scam, phishing, or financial fraud content detected."
                );
            }
        }




        // Check harassment keywords
        List<String> harassmentKeywords = keywordRepository.getHarassmentKeywords();
        for (String keyword : harassmentKeywords) {
            if (lowerCaseMessage.contains(keyword.toLowerCase())) {
                return new DetectionResult(
                        message,
                        "HARASSMENT",
                        RiskLevel.SUSPICIOUS,
                        keyword,
                        "Possible harassment or threatening language detected."
                );
            }
        }

        // 6. Harassment pattern matching
       /* if (containsAllWords(lowerCaseMessage, "ruin", "life") ||
                containsAllWords(lowerCaseMessage, "regret", "this") ||
                containsAllWords(lowerCaseMessage, "expose", "you")) {

            return new DetectionResult(
                    message,
                    RiskLevel.SUSPICIOUS,
                    "pattern match",
                    "Possible harassment, intimidation, or threatening language detected."
            );
        }*/
        List<String> patterns3 = keywordRepository.getHarassmentPatterns();
        for (String pattern : patterns3) {
            String[] words = pattern.split(",");

            if (containsAllWords(lowerCaseMessage, words[0], words[1])) {
                return new DetectionResult(
                        message,
                        "HARASSMENT",
                        RiskLevel.SUSPICIOUS,
                        "pattern match",
                        "Possible harassment, intimidation, or threatening language detected."
                );
            }
        }

        // If nothing matched
        return new DetectionResult(
                message,
                "NONE",
                RiskLevel.SAFE,
                "None",
                "No harmful content detected."
        );
    }
    private boolean containsAllWords(String message, String word1, String word2) {
        return message.contains(word1) && message.contains(word2);
    }


}

