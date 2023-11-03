package com.example.service;

import com.example.entity.TextEntity;
import com.example.repository.JpaTextRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TextService {
    private final JpaTextRepository textRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public String save(TextEntity textEntity) {
        if (textEntity.getText() == null || textEntity.getTextId() == null) {
            return "text_id or text is null";
        }
        try {
            textRepository.save(textEntity);
        } catch (Exception e) {
            logger.trace("trace log = {}", e.getMessage());
            return "Duplicate text_id found";
        }
        return textEntity.getTextId() + " save";
    }

    @Transactional
    public String getText(String textId) {
        return textRepository.findTextByTextId(textId);
    }

}
