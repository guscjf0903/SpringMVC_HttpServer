package com.example.controller;

import com.example.entity.TextEntity;
import com.example.service.ImageService;
import com.example.service.TextService;
import com.example.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

import static com.example.entity.HttpResponseCode.creatImageResponse;
import static com.example.entity.HttpResponseCode.createTextResponse;

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
    @PostMapping("/text/{textId}")
    public ResponseEntity<String> postText(@PathVariable String textId, @RequestBody String text) {
        TextEntity textEntity = new TextEntity();
        textEntity.setTextId(textId);
        textEntity.setText(text);

        String responseMessage = textService.save(textEntity);
        if(responseMessage.equals(textId + " save")) {
            return createTextResponse(HttpStatus.CREATED, responseMessage);
        } else {
            return createTextResponse(HttpStatus.CONFLICT, responseMessage);
        }
    }
    @GetMapping("/text/{textId}")
    public ResponseEntity<String> getText(@PathVariable String textId) {
        String text = textService.getText(textId);
        if(Objects.equals(text, "text_id not found")) {
            return createTextResponse(HttpStatus.NOT_FOUND, text);
        } else {
            return createTextResponse(HttpStatus.OK, text);
        }
    }
    @DeleteMapping("/text/{textId}")
    public ResponseEntity<String> deleteText(@PathVariable String textId) {
        String responseMessage = textService.deleteText(textId);
        if(responseMessage.equals(textId + " delete")) {
            return createTextResponse(HttpStatus.OK, responseMessage);
        } else {
            return createTextResponse(HttpStatus.NOT_FOUND, responseMessage);
        }
    }
    @GetMapping("/textAll")
    public ResponseEntity<String> getAll() {
        String text = textService.getAll();
        if(text == "text_id not found") {
            return createTextResponse(HttpStatus.NOT_FOUND, text);
        } else {
            return createTextResponse(HttpStatus.OK, text);
        }
    }

    @GetMapping("/image/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable String imageId) {
        String imageType = imageId.substring(imageId.lastIndexOf(".")+1);
        try {
            byte[] image = imageService.findByImageId(imageId);
            if(image == null) {
                return createTextResponse(HttpStatus.NOT_FOUND,"image_id not found");
            } else {
                return creatImageResponse(imageType, image);
            }
        } catch (IOException e) {
            return createTextResponse(HttpStatus.BAD_REQUEST, "imageType is not supported");
        }
    }


}
