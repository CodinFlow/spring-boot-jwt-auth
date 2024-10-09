package com.tericcabrel.authapi.entities.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity

public class Description {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    //@JsonFormat(pattern="dd.MM.yyyy", timezone="Europe/Zagreb")
    private LocalDate day ;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    @JsonIgnoreProperties("descriptions")
    private Report report;

}
