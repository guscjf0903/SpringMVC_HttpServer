package com.example.service;

import com.example.repository.ServerImageRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
    private final ServerImageRepository serverImageRepository = new ServerImageRepository();
    public Optional<byte[]> findByImageId(String imageId) throws IOException {
        return serverImageRepository.findImageByID(imageId);
    }

}
