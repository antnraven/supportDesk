package com.example.image.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ImageDto {
    private final Long id;
    private final Long incidentId;
    private final String url;
    private final String fileName;
    private final Long size;
    private final String mediaType;
}
