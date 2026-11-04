
package com.abinet.ethiopian_telegram_safety_detector.service;

import com.abinet.ethiopian_telegram_safety_detector.entity.AnalyzedMessage;
import com.abinet.ethiopian_telegram_safety_detector.repository.AnalyzedMessageRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyzedMessageService {

    private final AnalyzedMessageRepository repository;

    public AnalyzedMessageService(AnalyzedMessageRepository repository) {
        this.repository = repository;
    }

    public AnalyzedMessage saveAnalyzedMessage(
            String messageText,
            String detectionCategory,
            String riskLevel) {

        AnalyzedMessage message = new AnalyzedMessage();

        message.setMessageText(messageText);
        message.setDetectionCategory(detectionCategory);
        message.setRiskLevel(riskLevel);

        return repository.save(message);
    }

    public List<AnalyzedMessage> getAllAnalyzedMessages() {
        return repository.findAll();
    }
}
