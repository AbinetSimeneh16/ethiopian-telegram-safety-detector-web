package safetydetector;

import java.util.Arrays;
import java.util.List;
import safetydetector.KeywordFileHandler;
public class KeywordRepository {
    public List<String> getHarmfulKeywords() {
        return KeywordFileHandler.read(KeywordFileHandler.HARMFUL);

       /* return Arrays.asList(
                "nude",
                "private video",
                "private photo",
                "sex tape",
                "i will leak",
                "i will post your video",
                "send me your picture"
        );*/
    }
    public List<String> getScamKeywords() {
        return KeywordFileHandler.read(KeywordFileHandler.SCAM);
        /*return Arrays.asList(
                "send money",
                "send me money",
                "urgent payment",
                "click this link",
                "account blocked",
                "pay now",
                "bank transfer"
        );*/
    }
    public List<String> getHarassmentKeywords() {
        return KeywordFileHandler.read(KeywordFileHandler.HARASSMENT);
       /* return Arrays.asList(
                "idiot",
                "stupid",
                "i will expose you",
                "i will ruin your life",
                "you will regret this"
        );*/
    }
    public List<String> getHarmfulPatterns()
    {
        return KeywordFileHandler.read(KeywordFileHandler.HARMFUL_PATTERN);
    }
    public List<String> getHarassmentPatterns()
    {
        return KeywordFileHandler.read(KeywordFileHandler.HARASSMENT_PATTERN);
    }
    public List<String> getScamPatterns()
    {
        return KeywordFileHandler.read(KeywordFileHandler.SCAM_PATTERN);
    }

}

