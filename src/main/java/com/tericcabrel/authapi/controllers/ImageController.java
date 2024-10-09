package com.tericcabrel.authapi.controllers;

import com.tericcabrel.authapi.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
@CrossOrigin
@RestController
@RequestMapping("/user/image")

public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /*@PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = imageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }*/

    @PostMapping("/{profileId}")
    public ResponseEntity<String> uploadImage(@PathVariable Integer profileId, @RequestParam("image") MultipartFile file) throws IOException {
        String response = imageService.uploadImage(file, profileId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> downloadImage(@PathVariable UUID uuid) {
        byte[] imageData = imageService.displayImage(uuid);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(imageData);
    }



}
