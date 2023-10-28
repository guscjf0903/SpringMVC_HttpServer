package com.example.service;

import com.example.entity.TextSave;
import com.example.repository.JdbcTextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.example.entity.HttpResponseCode.createResponse;

@Service
@RequiredArgsConstructor
public class TextService {
    private final JdbcTextRepository textRepository;

    public ResponseEntity<String> save(TextSave textSave) {
        if (textSave.getText() == null || textSave.getTextId() == null) {
            return createResponse(HttpStatus.BAD_REQUEST, "text_id or text is null");
        }
        try {
            textRepository.save(textSave);
        } catch (SQLException e) {
            return createResponse(HttpStatus.CONFLICT, "Duplicate text_id found");
        }
        return createResponse(HttpStatus.CREATED, textSave.getTextId() + " save");
    }

    public ResponseEntity<String> delete(String text_id) throws SQLException {
        Integer deleteRowCount = textRepository.delete(text_id);
        if (deleteRowCount == 0) {
            return createResponse(HttpStatus.NOT_FOUND, "text_id not found");
        } else {
            return createResponse(HttpStatus.OK, text_id + " delete");
        }
    }

    public ResponseEntity<String> findByTextId(String text_id) throws SQLException {
        String text = textRepository.findByTextId(text_id);
        if (text == null) {
            return createResponse(HttpStatus.NOT_FOUND, "text_id not found");
        } else {
            return createResponse(HttpStatus.OK, text_id + " : " + text);
        }
    }

    public ResponseEntity<String> textAll() throws SQLException{
        HashMap<String, String> textAll = textRepository.textAll();
        if (textAll == null) {
            return createResponse(HttpStatus.NOT_FOUND, "text_id not found");
        } else {
            String hashToString = hashMapToString(textAll);
            return createResponse(HttpStatus.OK, hashToString);
        }
    }


    public String hashMapToString(HashMap<String, String> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();

        for(Map.Entry<String, String> entry : hashMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" : ").append(entry.getValue()).append(", ");
        }
        if(!stringBuilder.isEmpty()){
            stringBuilder.setLength(stringBuilder.length() - 2);
        }
        return stringBuilder.toString();
    }

}
