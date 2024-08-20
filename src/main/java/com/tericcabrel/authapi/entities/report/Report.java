package com.tericcabrel.authapi.entities.report;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String username;
    private int week;
    private String department;
    private int year;
    @Column(name = "archive")
    private boolean archived;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("report")
    private List<Description> descriptions = new ArrayList<>();

    @CreationTimestamp
    @JsonFormat(pattern="dd.MM.yyyy", timezone="Europe/Zagreb")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @JsonFormat(pattern="dd.MM.yyyy", timezone="Europe/Zagreb")
    private LocalDateTime updatedAt;


}
