package com.tericcabrel.authapi.services.serviceInterface;

import com.tericcabrel.authapi.entities.profile.Profile;

import java.util.Optional;

public interface ProfileService {

    void saveProfile(Profile profile);
    Optional<Profile> findProfileByEmail();
    String getUserEmail();
    void updateProfile(Profile profile);
}
