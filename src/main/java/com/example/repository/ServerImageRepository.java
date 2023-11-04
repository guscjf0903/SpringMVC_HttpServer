package com.example.repository;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Repository
public class ServerImageRepository {
    private static final String IMAGE_PATH = "/Users/hyunchuljung/Desktop/ServerFolder/";
    public byte[] findImageByID(String imageId) throws IOException {
        File file = new File(IMAGE_PATH + imageId);
        Path imagePath = file.toPath();
        if(!file.exists()){
            return null;
        } else {
            return Files.readAllBytes(imagePath);
        }
    }
}
