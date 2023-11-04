package com.example.entity;

import jdk.jfr.ContentType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.springframework.web.servlet.function.RequestPredicates.contentType;

public class HttpResponseCode {
    public static ResponseEntity<String> createTextResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(message);
    }

    public static ResponseEntity<?> creatImageResponse(String imageType, byte[] image) {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = determineMediaType(imageType);
        if(mediaType != null) {
            headers.setContentType(mediaType);
            headers.setContentLength(image.length);
            return new ResponseEntity<byte[]>(image, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("imageType is not supported");
        }
    }

    public static MediaType determineMediaType(String imageType) {
        if(imageType.equals("jpg") || imageType.equals("jpeg")) {
            return MediaType.IMAGE_JPEG;
        } else if(imageType.equals("png")) {
            return MediaType.IMAGE_PNG;
        } else if(imageType.equals("gif")) {
            return MediaType.IMAGE_GIF;
        } else {
            return null;
        }
    }

}
