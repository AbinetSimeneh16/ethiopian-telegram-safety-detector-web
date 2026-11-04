CREATE TABLE analyzed_messages (
                                   id BIGSERIAL PRIMARY KEY,
                                   message_text TEXT NOT NULL,
                                   detection_category VARCHAR(30) NOT NULL,
                                   risk_level VARCHAR(20) NOT NULL,
                                   analyzed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);