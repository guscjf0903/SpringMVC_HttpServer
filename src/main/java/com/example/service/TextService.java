package com.example.service;

import com.example.entity.TextEntity;
import com.example.repository.JpaTextRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional
    public String deleteText(String textId) {
        if (textRepository.findTextByTextId(textId) == null) {
            return "text_id not found";
        }
        textRepository.deleteByTextId(textId);
        return textId + " delete";
    }
    @Transactional
    public String getAll() {
        List<Object> textList = textRepository.findAllText();
        StringBuilder stringBuilder = new StringBuilder();
        if(textList.isEmpty()) {
            return "text_id not found";
        } else {
            for(Object objectText : textList) {
                Object[] textArray = (Object[]) objectText;
                String text = (String) textArray[0];
                String textId = (String) textArray[1];
                stringBuilder.append(textId).append(" : ").append(text).append("\n");
            }
            return stringBuilder.toString();
        }
    }


}
