ALTER TABLE analyzed_messages
    ADD COLUMN matched_keyword VARCHAR(255),
ADD COLUMN reason TEXT;