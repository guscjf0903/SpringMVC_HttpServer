package com.example.controller;

import com.example.domain.TextSave;
import com.example.service.ImageService;
import com.example.service.TextService;
import com.example.service.TimeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@RestController
public class HttpController {
    private final TextService textService;
    private final TimeService timeService;
    private final ImageService imageService;
    @Autowired
    public HttpController(TextService textService,TimeService timeService, ImageService imageService) {
        this.textService = textService;
        this.timeService = timeService;
        this.imageService = imageService;
    }
    @GetMapping("/time")
    public String time() {
        return timeService.getTime();
    }
    @PostMapping("/text/{textID}")
    public ResponseEntity<String> postText(@PathVariable String textID, @RequestBody String text) throws SQLException {
        TextSave textSave = new TextSave(textID, text);
        if(textID == null || text == null) {
            return ResponseEntity.badRequest().body("fail");
        }
        return ResponseEntity.ok().body(textService.save(textSave));
    }
    @GetMapping("/text/{textID}")
    public String getText(@PathVariable String textID) throws SQLException {
        return textService.findByTextId(textID).orElse("no text");
    }
    @DeleteMapping("/text/{textID}")
    public String deleteText(@PathVariable String textID) throws SQLException {
        return textService.deleteText(textID).orElse("no text_id") + " delete";
    }
    @GetMapping("/textAll") //아얘 리스트에 값이 하나도 없다면 다시 예외처리
    public String getAllText() throws SQLException {
        return textService.textAll().toString();
    }
    @GetMapping(value = "/image/{imageId}", produces = {"image/png", "image/jpeg"})
    public byte[] getImage(@PathVariable String imageId) throws IOException {
        return imageService.findByImageId(imageId).orElse("no image".getBytes());
    }



}
