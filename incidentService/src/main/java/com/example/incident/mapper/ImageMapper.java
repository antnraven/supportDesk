package com.example.incident.mapper;

import com.example.incident.dto.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "incidentId", source = "incidentId")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "fileName", source = "fileName")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "mediaType", source = "mediaType")
    Image fromProto(com.example.grpc.generated.Image internalImage);

    java.util.List<Image> fromProtoList(java.util.List<com.example.grpc.generated.Image> internalImages);
}
