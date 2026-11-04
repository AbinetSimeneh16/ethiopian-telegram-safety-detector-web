package com.abinet.ethiopian_telegram_safety_detector.config;

import safetydetector.MessageAnalyzer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DetectorConfig {

    @Bean
    public MessageAnalyzer messageAnalyzer() {
        return new MessageAnalyzer();
    }
}