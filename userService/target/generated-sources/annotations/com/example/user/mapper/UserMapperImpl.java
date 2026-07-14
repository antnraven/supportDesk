package com.example.user.mapper;

import com.example.grpc.generated.UserRole;
import com.example.user.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-14T15:45:04+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 26 (Axiom JSC)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public com.example.grpc.generated.User toProto(User user) {
        if ( user == null ) {
            return null;
        }

        com.example.grpc.generated.User.Builder user1 = com.example.grpc.generated.User.newBuilder();

        if ( user.getId() != null ) {
            user1.setId( user.getId() );
        }
        user1.setRole( userRoleToUserRole( user.getRole() ) );
        user1.setUserFullname( user.getUserFullname() );
        user1.setUserLogin( user.getUserLogin() );
        user1.setPhoneNumber( user.getPhoneNumber() );
        user1.setEmail( user.getEmail() );
        user1.setWorkplace( user.getWorkplace() );

        return user1.build();
    }

    @Override
    public List<com.example.grpc.generated.User> toProtoList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<com.example.grpc.generated.User> list = new ArrayList<com.example.grpc.generated.User>( users.size() );
        for ( User user : users ) {
            list.add( toProto( user ) );
        }

        return list;
    }

    protected UserRole userRoleToUserRole(com.example.user.enums.UserRole userRole) {
        if ( userRole == null ) {
            return null;
        }

        UserRole userRole1;

        switch ( userRole ) {
            case ADMIN: userRole1 = UserRole.ADMIN;
            break;
            case ANALYST: userRole1 = UserRole.ANALYST;
            break;
            case USER_ROLE_UNSPECIFIED: userRole1 = UserRole.USER_ROLE_UNSPECIFIED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + userRole );
        }

        return userRole1;
    }
}
