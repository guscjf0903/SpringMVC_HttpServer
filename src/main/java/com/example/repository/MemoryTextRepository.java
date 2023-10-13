package com.example.repository;

import com.example.domain.TextSave;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MemoryTextRepository implements TextRepository{
    private static final Map<String, String> textMemory = new HashMap<>();
    @Override
    public TextSave save(TextSave textSave) {
        textMemory.put(textSave.getTextId(), textSave.getText());
        return textSave;
    }
    @Override
    public Optional<String> findById(String textId) {
        return Optional.ofNullable(textMemory.get(textId));
    }
    @Override
    public List<String> textAll() {
        return new ArrayList<>(textMemory.values());
    }
    @Override
    public Optional<String> deleteText(String textId) {
        return Optional.ofNullable(textMemory.remove(textId));
    }

}
