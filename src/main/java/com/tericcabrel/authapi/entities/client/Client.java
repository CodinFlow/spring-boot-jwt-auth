package com.tericcabrel.authapi.entities.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Client")
public class Client {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String address;

    @CreationTimestamp
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Europe/Zagreb")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Europe/Zagreb")
    private LocalDateTime updatedAt;
}
