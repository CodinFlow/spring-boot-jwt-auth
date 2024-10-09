package com.tericcabrel.authapi.entities.report;


import jakarta.persistence.*;


@Entity
public class ReportDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "description_id", referencedColumnName = "id")
    private Description description;

}
