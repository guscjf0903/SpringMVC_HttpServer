package com.example.service;

import com.example.domain.TextSave;
import com.example.repository.JdbcTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TextService {
    private final JdbcTextRepository textRepository;
    @Autowired
    public TextService(JdbcTextRepository textRepository) {
        this.textRepository = textRepository;
    }


    public String save(TextSave textSave) throws SQLException {
        textRepository.save(textSave);
        return textSave.getTextId() + " success";
    }
    public Optional<String> findByTextId(String textId) throws SQLException {
        return textRepository.findById(textId);
    }
    public Optional<String> deleteText(String textId) throws SQLException {
        return textRepository.deleteText(textId);
    }
    public List<String> textAll() throws SQLException {
        return textRepository.textAll();
    }
}
