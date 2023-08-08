package com.example.restaurant.services;

import com.example.restaurant.models.Image;
import com.example.restaurant.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public Image addImage(Image image) {
        return imageRepository.save(image);
    }
}
