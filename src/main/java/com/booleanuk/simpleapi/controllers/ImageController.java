package com.booleanuk.simpleapi.controllers;

import com.booleanuk.simpleapi.models.Image;
import com.booleanuk.simpleapi.models.ImageManager;
import com.booleanuk.simpleapi.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping
    private ResponseEntity<List<Image>> getAllImages() {
        return new ResponseEntity<>(this.imageRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    private ResponseEntity<Image> getOneImage(@PathVariable(name = "id") int id) {
        Image image = this.imageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Image> createImage(@RequestBody ImageManager imageManager) {
        Image image = imageManager.makeImage();
        System.out.println("Image getting saved");
        return new ResponseEntity<>(this.imageRepository.save(image), HttpStatus.CREATED);
    }

}
