package com.example.controller;

import com.example.entity.TextSave;
import com.example.service.ImageService;
import com.example.service.TextService;
import com.example.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class HttpController {
    private final TextService textService;
    private final TimeService timeService;
    private final ImageService imageService;

    @GetMapping("/time")
    public String time() {
        return timeService.getTime();
    }
    @PostMapping("/text/{textID}")
    public ResponseEntity<String> postText(@PathVariable String textID, @RequestBody String text) {
        TextSave textSave = new TextSave(textID, text);
        return textService.save(textSave);
    }
    @DeleteMapping("/text/{textID}")
    public ResponseEntity<String> deleteText(@PathVariable String textID) throws SQLException {
        return textService.delete(textID);
    }

    @GetMapping("/text/{textID}")
    public ResponseEntity<String> getText(@PathVariable String textID) throws SQLException {
        return textService.findByTextId(textID);
    }

    @GetMapping("/textAll") //아얘 리스트에 값이 하나도 없다면 다시 예외처리
    public ResponseEntity<String> getAllText() throws SQLException {
        return textService.textAll();
    }
    @GetMapping(value = "/image/{imageId}", produces = {"image/png", "image/jpeg"})
    public byte[] getImage(@PathVariable String imageId) throws IOException {
        return imageService.findByImageId(imageId).orElse("no image".getBytes());
    }


}
