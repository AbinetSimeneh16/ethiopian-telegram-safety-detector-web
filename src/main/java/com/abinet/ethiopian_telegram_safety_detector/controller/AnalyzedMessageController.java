
package com.abinet.ethiopian_telegram_safety_detector.controller;

import com.abinet.ethiopian_telegram_safety_detector.entity.AnalyzedMessage;
import com.abinet.ethiopian_telegram_safety_detector.service.AnalyzedMessageService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class AnalyzedMessageController {

    private final AnalyzedMessageService service;

    public AnalyzedMessageController(AnalyzedMessageService service) {
        this.service = service;
    }

    @GetMapping
    public List<AnalyzedMessage> getAllMessages() {
        return service.getAllAnalyzedMessages();
    }
}

