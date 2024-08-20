package com.tericcabrel.authapi.services.serviceImpl;

import com.tericcabrel.authapi.entities.profile.Profile;
import com.tericcabrel.authapi.repositories.ProfileRepository;
import com.tericcabrel.authapi.services.serviceInterface.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    ProfileRepository profileRepository;



    @Override
    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }


    @Override
    public Optional<Profile> findProfileByEmail() {
        String email = getUserEmail();
        return profileRepository.findByEmail(email);
    }

    @Override
    public String getUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @Override
    public void updateProfile(Profile profile) {
        // Fetch the profile based on the currently authenticated user's email
        Optional<Profile> optionalProfile = findProfileByEmail();

        // Check if the profile is present
        Profile updatedProfile = optionalProfile.orElseThrow(() ->
                new IllegalArgumentException("Profile not found for the currently authenticated user"));

        // Update profile details
        updatedProfile.setFirst_name(profile.getFirst_name());
        updatedProfile.setLast_name(profile.getLast_name());
        updatedProfile.setPhone(profile.getPhone());
        updatedProfile.setAddress(profile.getAddress());
        updatedProfile.setBio(profile.getBio());

        // Save the updated profile
        profileRepository.save(updatedProfile);
    }
}
