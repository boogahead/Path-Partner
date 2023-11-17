package com.ssafy.pathpartner.auth;

import com.ssafy.pathpartner.user.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        UserDto user = new UserDto();
        user.setId(jwt.getSubject());
        return new UsernamePasswordAuthenticationToken(user, jwt, user.getAuthorities());
    }
}
