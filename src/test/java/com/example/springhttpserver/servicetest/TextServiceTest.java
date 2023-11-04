package com.example.springhttpserver.servicetest;

import com.example.repository.JpaTextRepository;
import com.example.service.TextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextServiceTest {
    @InjectMocks
    private TextService textService;

    @Mock
    private JpaTextRepository jpaTextRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void 딜리트_성공테스트() throws Exception {
        // given
        String textId = "test1";
        Mockito.when(jpaTextRepository.delete(textId)).thenReturn(1);

        // when
        ResponseEntity<String> response = textService.delete(textId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("test1 delete", response.getBody());
    }

}
