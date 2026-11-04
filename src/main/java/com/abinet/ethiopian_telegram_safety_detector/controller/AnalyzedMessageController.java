
package com.abinet.ethiopian_telegram_safety_detector.controller;

import com.abinet.ethiopian_telegram_safety_detector.entity.AnalyzedMessage;
import com.abinet.ethiopian_telegram_safety_detector.service.AnalyzedMessageService;
import com.abinet.ethiopian_telegram_safety_detector.dto.MessageAnalysisRequest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class AnalyzedMessageController {

    private final AnalyzedMessageService service;

    public AnalyzedMessageController(AnalyzedMessageService service) {
        this.service = service;
    }

    // Retrieve all previously analyzed messages
    @GetMapping
    public List<AnalyzedMessage> getAllMessages() {
        return service.getAllAnalyzedMessages();
    }

    // Analyze a new message and save the result
    @PostMapping("/analyze")
    public AnalyzedMessage analyzeMessage(
            @RequestBody MessageAnalysisRequest request) {

        return service.analyzeAndSaveMessage(request.getMessage());
    }
}