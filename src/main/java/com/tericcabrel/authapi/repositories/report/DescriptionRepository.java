package com.tericcabrel.authapi.repositories.report;


import com.tericcabrel.authapi.entities.report.Description;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository extends CrudRepository<Description, Integer> {

}
