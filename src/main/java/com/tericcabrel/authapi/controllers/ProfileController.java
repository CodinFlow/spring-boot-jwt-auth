package com.tericcabrel.authapi.controllers;


import com.tericcabrel.authapi.entities.profile.Profile;
import com.tericcabrel.authapi.services.serviceInterface.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("https://web-application-development7405251-317d6980115c9bfc5d610a5bb25d.gitlab.io/")
@RequestMapping("/user")
@org.springframework.web.bind.annotation.RestController
public class ProfileController {

    @Autowired
    ProfileService profileService;


    @GetMapping("/profile")
    public Optional<Profile> getMyProfile() {
        return profileService.findProfileByEmail();
    }


    @PostMapping("/add-profile")
    public String add(@RequestBody Profile profile) {
        profileService.saveProfile(profile);
        return "New User with the name "+ profile.getFirstname() + " has been added!"  ;
    }

    @PutMapping("/profile-edit")
    public ResponseEntity<?> updateProfile(@RequestBody Profile profile) {
        try {
            profileService.updateProfile(profile);
            return ResponseEntity.ok("Profile updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the profile");
        }
    }


}
