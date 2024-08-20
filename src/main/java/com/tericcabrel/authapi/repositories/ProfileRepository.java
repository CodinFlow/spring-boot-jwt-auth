package com.tericcabrel.authapi.repositories;

import com.tericcabrel.authapi.entities.profile.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile,Integer> {
    Optional<Profile> findByEmail(String email);
}
