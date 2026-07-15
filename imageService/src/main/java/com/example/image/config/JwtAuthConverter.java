package com.example.image.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter defaultConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        var authorities = Stream.concat(
                defaultConverter.convert(jwt).stream(),
                extractRealmRoles(jwt).stream()
        ).toList();

        return new JwtAuthenticationToken(jwt, authorities, jwt.getClaimAsString("preferred_username"));
    }

    private Collection<? extends GrantedAuthority> extractRealmRoles(Jwt jwt) {
        var realmAccess = jwt.getClaimAsMap("realm_access");
        if (realmAccess == null) return List.of();

        @SuppressWarnings("unchecked")
        var roles = (List<String>) realmAccess.get("roles");
        if (roles == null) return List.of();

        return roles.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}