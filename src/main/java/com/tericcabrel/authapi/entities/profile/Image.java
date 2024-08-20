package com.tericcabrel.authapi.entities.profile;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Data
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Images")
public class Image {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String type;

    @Lob
    @Column(length = 1000000)
    private byte[] imageData;

}