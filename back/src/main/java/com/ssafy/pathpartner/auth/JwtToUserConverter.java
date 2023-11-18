package com.ssafy.pathpartner.auth;

import com.ssafy.pathpartner.user.dto.UserDto;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    @Autowired
    UserDetailsService userDetailsService;
    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        Map <String, Object> claims = jwt.getClaims();
        UserDto user = (UserDto) userDetailsService.loadUserByUsername((String) claims.get("id"));
        return new UsernamePasswordAuthenticationToken(user, jwt, user.getAuthorities());
    }
}
