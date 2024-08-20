package com.tericcabrel.authapi.entities.report;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "description_id", referencedColumnName = "id")
    private Description description;

}
