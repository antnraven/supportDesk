package com.example.image.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long incidentId;
    private String url;
    private String fileName;
    private Long size;
    private String mediaType;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", incidentId=" + incidentId +
                ", url='" + url + '\'' +
                ", fileName='" + fileName + '\'' +
                ", size=" + size +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }
}
