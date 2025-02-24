package com.booleanuk.simpleapi.controllers;

import com.booleanuk.simpleapi.models.Image;
import com.booleanuk.simpleapi.models.ImageManager;
import com.booleanuk.simpleapi.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping
    private ResponseEntity<?> createImage(@RequestBody ImageManager imageManager) {
        imageManager.writeImage(imageManager.makeImage());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
