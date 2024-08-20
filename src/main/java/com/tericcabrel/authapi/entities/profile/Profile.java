package com.tericcabrel.authapi.entities.profile;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROFILE_TB")
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer profile_id;

    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String mobile;
    private String address;
    private String bio;
    private UUID imageId; // UUID reference to Image

    @CreationTimestamp
    @JsonFormat(pattern="dd.MM.yyyy", timezone="Europe/Zagreb")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @JsonFormat(pattern="dd.MM.yyyy", timezone="Europe/Zagreb")
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
