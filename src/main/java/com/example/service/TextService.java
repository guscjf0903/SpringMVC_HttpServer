package com.example.service;

import com.example.domain.TextSave;
import com.example.repository.MemoryTextRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TextService {
    private final MemoryTextRepository memoryTextRepository = new MemoryTextRepository();
    public String save(TextSave textSave) {
        memoryTextRepository.save(textSave);
        return textSave.getTextId() + " success";
    }
    public Optional<String> findByTextId(String textId) {
        return memoryTextRepository.findById(textId);
    }
    public Optional<String> deleteText(String textId) {
        return memoryTextRepository.deleteText(textId);
    }
    public List<String> textAll() {
        return memoryTextRepository.textAll();
    }

}
