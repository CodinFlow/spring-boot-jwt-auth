package com.tericcabrel.authapi.repositories.report;

import com.tericcabrel.authapi.entities.report.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Integer> {
    List<Report> findByUsername(String username);
    Optional<Report> findByIdAndUsername(Long id, String username);
}


