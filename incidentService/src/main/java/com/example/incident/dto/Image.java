package com.example.incident.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(setterPrefix = "with", access = AccessLevel.PUBLIC)
@Getter
@ToString
public class Image {
    private final Long id;
    private final Long incidentId;
    private final String url;
    private final String fileName;
    private final Long size;
    private final String mediaType;
}
