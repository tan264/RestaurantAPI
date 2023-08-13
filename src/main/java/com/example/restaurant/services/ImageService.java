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

    public Iterable<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image deleteById(int id) {
        Image foundedImage = imageRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(
                        String.format("Image with id = %d does not exist", id)));

        imageRepository.deleteById(id);
        return foundedImage;
    }
}
