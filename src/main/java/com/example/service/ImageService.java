package com.example.service;

import com.example.repository.ServerImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ServerImageRepository serverImageRepository;
    public byte[] findByImageId(String imageId) throws IOException {
        if(imageId == null) {
            return null;
        }
        return serverImageRepository.findImageByID(imageId);
    }
}
