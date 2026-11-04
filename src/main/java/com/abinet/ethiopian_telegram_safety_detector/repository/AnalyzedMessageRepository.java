package com.abinet.ethiopian_telegram_safety_detector.repository;

import com.abinet.ethiopian_telegram_safety_detector.entity.AnalyzedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyzedMessageRepository extends JpaRepository<AnalyzedMessage, Long> {

}
