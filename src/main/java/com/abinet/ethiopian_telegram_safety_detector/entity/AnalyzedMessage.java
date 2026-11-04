
package com.abinet.ethiopian_telegram_safety_detector.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "analyzed_messages")
public class AnalyzedMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_text", nullable = false)
    private String messageText;

    @Column(name = "detection_category", nullable = false, length = 30)
    private String detectionCategory;

    @Column(name = "risk_level", nullable = false, length = 20)
    private String riskLevel;

    @Column(name = "analyzed_at", nullable = false)
    private LocalDateTime analyzedAt;
    @Column(name = "matched_keyword")
    private String matchedKeyword;

    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;

    // Required by JPA
    public AnalyzedMessage() {
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getDetectionCategory() {
        return detectionCategory;
    }

    public void setDetectionCategory(String detectionCategory) {
        this.detectionCategory = detectionCategory;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
    public String getMatchedKeyword() {
        return matchedKeyword;
    }

    public void setMatchedKeyword(String matchedKeyword) {
        this.matchedKeyword = matchedKeyword;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getAnalyzedAt() {
        return analyzedAt;
    }

    public void setAnalyzedAt(LocalDateTime analyzedAt) {
        this.analyzedAt = analyzedAt;
    }
    @PrePersist
    protected void onCreate() {
        if (analyzedAt == null) {
            analyzedAt = LocalDateTime.now();
        }
    }
}
