package com.tericcabrel.authapi.repositories;

import com.tericcabrel.authapi.entities.profile.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageRepository extends CrudRepository<Image, UUID> {

}
