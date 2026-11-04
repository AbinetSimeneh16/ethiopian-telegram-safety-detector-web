package com.abinet.ethiopian_telegram_safety_detector.service;

import com.abinet.ethiopian_telegram_safety_detector.entity.AnalyzedMessage;
import com.abinet.ethiopian_telegram_safety_detector.repository.AnalyzedMessageRepository;

import safetydetector.MessageAnalyzer;
import safetydetector.DetectionResult;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyzedMessageService {

    private final AnalyzedMessageRepository repository;
    private final MessageAnalyzer messageAnalyzer;

    public AnalyzedMessageService(
            AnalyzedMessageRepository repository,
            MessageAnalyzer messageAnalyzer) {

        this.repository = repository;
        this.messageAnalyzer = messageAnalyzer;
    }

    public AnalyzedMessage analyzeAndSaveMessage(String messageText) {

        // 1. Analyze the submitted message
        DetectionResult result = messageAnalyzer.analyzeMessage(messageText);

        // 2. Create a database entity
        AnalyzedMessage message = new AnalyzedMessage();

        // 3. Transfer the detection results into the entity
        message.setMessageText(result.getMessage());
        message.setDetectionCategory(result.getDetectionCategory());
        message.setRiskLevel(result.getRiskLevel().name());
        message.setMatchedKeyword(result.getMatchedKeyword());
        message.setReason(result.getReason());

        // 4. Save the entity in PostgreSQL
        return repository.save(message);
    }

    public List<AnalyzedMessage> getAllAnalyzedMessages() {
        return repository.findAll();
    }
}