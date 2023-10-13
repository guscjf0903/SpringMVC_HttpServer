package com.example.repository;

import com.example.domain.TextSave;

import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;

public interface   TextRepository {
    TextSave save(TextSave textSave);
    Optional<String> findById(String textId);
    List<String> textAll();
    Optional<String> deleteText(String textId);
}
