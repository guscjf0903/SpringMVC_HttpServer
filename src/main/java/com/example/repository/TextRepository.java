package com.example.repository;

import com.example.domain.TextSave;

import java.lang.annotation.Target;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface   TextRepository {
    TextSave save(TextSave textSave) throws SQLException;
    Optional<String> findById(String textId) throws SQLException;
    List<String> textAll() throws SQLException;
    Optional<String> deleteText(String textId) throws SQLException;
}
