package com.tericcabrel.authapi.services;

import com.tericcabrel.authapi.entities.profile.Image;
import com.tericcabrel.authapi.entities.profile.Profile;
import com.tericcabrel.authapi.repositories.ImageRepository;
import com.tericcabrel.authapi.repositories.ProfileRepository;
import com.tericcabrel.authapi.utils.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Autowired
    private final ImageRepository imageRepository;
    @Autowired
    private  final ProfileRepository profileRepository;

    /*public String uploadImage(MultipartFile imageFile) throws IOException {
        var imageToSave = Image.builder()
                .name(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .imageData(ImageUtils.compressImage(imageFile.getBytes()))
                .build();
        imageRepository.save(imageToSave);
        return "file uploaded successfully : " + imageFile.getOriginalFilename();
    }*/
    @Transactional
    public String uploadImage(MultipartFile imageFile, Integer profileId) throws IOException {
        // Save the image
        var imageToSave = Image.builder()
                .name(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .imageData(ImageUtils.compressImage(imageFile.getBytes()))
                .build();
        Image savedImage = imageRepository.save(imageToSave);

        // Update the profile
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        profile.setImageId(savedImage.getId());
        profileRepository.save(profile);

        return "File uploaded successfully: " + imageFile.getOriginalFilename();
    }


    public byte[] displayImage(UUID uuid) {
        Optional<Image> dbImage = imageRepository.findById(uuid);

        return dbImage.map(image -> {
            try {
                return ImageUtils.decompressImage(image.getImageData());
            } catch (DataFormatException | IOException exception) {
                throw new RuntimeException("Error downloading an image with ID: " + uuid, exception);
            }
        }).orElse(null);

    }


}
