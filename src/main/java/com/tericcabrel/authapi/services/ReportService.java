package com.tericcabrel.authapi.services;

import com.tericcabrel.authapi.entities.report.Description;
import com.tericcabrel.authapi.entities.report.Report;
import com.tericcabrel.authapi.repositories.report.DescriptionRepository;
import com.tericcabrel.authapi.repositories.report.ReportDescriptionRepository;
import com.tericcabrel.authapi.repositories.report.ReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    DescriptionRepository descriptionRepository;
    @Autowired
    ReportDescriptionRepository reportDescriptionRepository;


    public List<Report> getReportsByUsername(String username) {
        List<Report> reports = reportRepository.findByUsername(username);

        return reports.stream().filter(report -> !report.isArchived())
                .collect(Collectors.toList());
    }

    public Report getReportByIdAndUsername(Long id, String username) {
        return reportRepository.findByIdAndUsername(id, username)
                .filter(report -> !report.isArchived())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found or is archived"));
    }

    @Transactional
    public Report saveReport(Report report) {
        try {
            System.out.println("report from  " + report.getUsername());
            Report savedReport = reportRepository.save(report);

            for (Description description : savedReport.getDescriptions()) {
                description.setReport(savedReport);
                descriptionRepository.save(description);
            }
            savedReport.setDescriptions(savedReport.getDescriptions());

            System.out.println("success " + savedReport);

            return savedReport;
        } catch (Exception e) {
            System.out.println("something went wrong! " + e);
            throw e;
        }
    }

    @Transactional
    public void archieveReportByIdAndUsername(Long id, String username) {
        Report report =  reportRepository.findByIdAndUsername(id,username).orElse(null);
        if (report != null ) {
            report.setArchived(true);
            reportRepository.save(report);
        }
    }




    public void removeAllReport () {
        reportRepository.deleteAll();
    }

    public void deleteReportByIdAndUsername(Long id, String username) {
        Report report =  reportRepository.findByIdAndUsername(id,username).orElse(null);
        if (report != null) {
            reportRepository.delete(report);
        }
    }
}
