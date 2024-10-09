package com.tericcabrel.authapi.entities.profile;

import jakarta.persistence.*;

import java.util.UUID;



@Entity

@Table(name = "Images")
public class Image {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    public Image() {

    }


    public UUID getId() {
        return id;
    }

    public Image(UUID id, String name, String type, byte[] imageData) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    private String type;

    @Lob
    @Column(length = 1000000)
    private byte[] imageData;

}