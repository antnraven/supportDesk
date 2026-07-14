package com.example.incident.mapper;

import com.example.incident.dto.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "userFullname", source = "userFullname")
    @Mapping(target = "userLogin", source = "userLogin")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "workplace", source = "workplace")
    User fromProto(com.example.grpc.generated.User user);

    java.util.List<User> fromProtoList(java.util.List<com.example.grpc.generated.User> users);
}