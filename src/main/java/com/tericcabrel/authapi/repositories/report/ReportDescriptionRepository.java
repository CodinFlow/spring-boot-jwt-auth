package com.tericcabrel.authapi.repositories.report;

import com.tericcabrel.authapi.entities.report.ReportDescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDescriptionRepository extends CrudRepository<ReportDescription, Integer> {
}
