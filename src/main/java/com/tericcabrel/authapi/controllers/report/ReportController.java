package com.tericcabrel.authapi.controllers.report;

import com.tericcabrel.authapi.entities.report.Report;
import com.tericcabrel.authapi.services.JwtService;
import com.tericcabrel.authapi.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin("https://web-application-development7405251-317d6980115c9bfc5d610a5bb25d.gitlab.io/")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/auth/reports")


public class ReportController {

  @Autowired
  public ReportService reportService;

    @Autowired
    private JwtService jwtService;


    @GetMapping("")
    public List<Report> getReports(@RequestHeader("Authorization") String token) {
        // Remove "Bearer " from token
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        return reportService.getReportsByUsername(username);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        // Remove "Bearer " from token
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);

        Report report = reportService.getReportByIdAndUsername(id, username);
        return ResponseEntity.ok(report);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Report saveReport(@RequestBody Report report) {
        return reportService.saveReport(report);
    }

   @PutMapping("/{id}")
   public ResponseEntity<Void> archieveReportById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
       // Remove "Bearer " from token
       String jwt = token.substring(7);
       String username = jwtService.extractUsername(jwt);

       Optional<Report> report = Optional.ofNullable(reportService.getReportByIdAndUsername(id, username));
       if (report.isPresent()) {
           reportService.archieveReportByIdAndUsername(id, username);
           return ResponseEntity.ok().build();
       } else {
           return ResponseEntity.status(403).build();
       }
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        // Remove "Bearer " from token
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);

        Optional<Report> report = Optional.ofNullable(reportService.getReportByIdAndUsername(id, username));
        if (report.isPresent()) {
            reportService.deleteReportByIdAndUsername(id, username);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }


}
