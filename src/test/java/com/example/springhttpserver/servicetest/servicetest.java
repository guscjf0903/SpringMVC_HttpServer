package com.example.springhttpserver.servicetest;

import com.example.entity.TextSave;
import com.example.repository.JdbcTextRepository;
import com.example.service.TextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class servicetest {
    private JdbcTextRepository textRepository;
    TextService textService;
    @BeforeEach
    void setUp() {
        textRepository = mock(JdbcTextRepository.class);
        textService = new TextService(textRepository);
    }
    @Test
    void save_확인테스트() throws SQLException {
        TextSave textSave = new TextSave("1", "test");
        ResponseEntity<String> response = textService.save(textSave);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("1 save", response.getBody());
    }

    @Test
    void save_textNull테스트() throws SQLException {
        TextSave textSave = new TextSave("2", null);
        ResponseEntity<String> response = textService.save(textSave);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("text_id or text is null", response.getBody());
    }
}
