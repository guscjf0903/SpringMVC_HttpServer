package com.example.controller;

import com.example.entity.TextEntity;
import com.example.service.TextService;
import com.example.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.entity.HttpResponseCode.createResponse;

@RestController
@RequiredArgsConstructor
public class HttpController {
    private final TextService textService;
    private final TimeService timeService;
    //private final ImageService imageService;

    @GetMapping("/time")
    public String time() {
        return timeService.getTime();
    }
    @PostMapping("/text/{textID}")
    public ResponseEntity<String> postText(@PathVariable String textID, @RequestBody String text) {
        TextEntity textEntity = new TextEntity();
        textEntity.setTextId(textID);
        textEntity.setText(text);

        String responseMessage = textService.save(textEntity);
        if(responseMessage.equals(textID + " save")) {
            return createResponse(HttpStatus.CREATED, responseMessage);
        } else {
            return createResponse(HttpStatus.CONFLICT, responseMessage);
        }
    }

    @GetMapping("/text/{textID}")
    public ResponseEntity<String> getText(@PathVariable String textID) {
        String text = textService.getText(textID);
        if(text == null) {
            return createResponse(HttpStatus.NOT_FOUND, "text_id not found");
        } else {
            return createResponse(HttpStatus.OK, text);
        }
    }


}
