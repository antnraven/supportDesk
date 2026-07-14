package com.example.user.mapper;

import com.example.user.entities.User;
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
    com.example.grpc.generated.User toProto(User user);

    java.util.List<com.example.grpc.generated.User> toProtoList(java.util.List<User> users);

//    @Mapping(target = "id", expression = "java(user.getId() != null ? user.getId() : 0L)")
//    default com.example.grpc.generated.User safeToProto(User user) {
//        return toProto(user);
//    }
}
