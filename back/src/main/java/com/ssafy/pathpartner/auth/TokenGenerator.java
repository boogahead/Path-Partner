package com.ssafy.pathpartner.auth;

import com.ssafy.pathpartner.auth.dto.TokenDTO;
import com.ssafy.pathpartner.user.dto.UserDto;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

  @Autowired
  JwtEncoder accessTokenEncoder;

  @Autowired
  @Qualifier("jwtRefreshTokenEncoder")
  JwtEncoder refreshTokenEncoder;

  private String createAccessToken(Authentication authentication) {
    UserDto user = (UserDto) authentication.getPrincipal();
    Instant now = Instant.now();

    JwtClaimsSet claimsSet = JwtClaimsSet.builder()
        .issuer("pathpartner")
        .issuedAt(now)
        .expiresAt(now.plus(99999999, ChronoUnit.MINUTES))
        .claim("id", user.getId())
        .claim("uuid", user.getUuid())
        .build();

    return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
  }

  private String createRefreshToken(Authentication authentication) {
    UserDto user = (UserDto) authentication.getPrincipal();
    Instant now = Instant.now();

    JwtClaimsSet claimsSet = JwtClaimsSet.builder()
        .issuer("pathpartner")
        .issuedAt(now)
        .expiresAt(now.plus(30, ChronoUnit.DAYS))
        .claim("id", user.getId())
        .claim("uuid", user.getUuid())
        .build();

    return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
  }

  public TokenDTO createToken(Authentication authentication) {
    Object getUserResult = authentication.getPrincipal();
    UserDto user = null;
    if (!(getUserResult instanceof UserDto)) {
      throw new BadCredentialsException(
          MessageFormat.format("principal {0} is not of User type",
              authentication.getPrincipal().getClass())
      );
    } else {
      user = (UserDto) getUserResult;
    }

    TokenDTO tokenDTO = new TokenDTO();
    tokenDTO.setUserId(user.getId());
    tokenDTO.setAccessToken(createAccessToken(authentication));

    String refreshToken;
    if (authentication.getCredentials() instanceof Jwt) {
      Jwt jwt = (Jwt) authentication.getCredentials();
      Instant now = Instant.now();
      Instant expiresAt = jwt.getExpiresAt();
      Duration duration = Duration.between(now, expiresAt);
      long daysUntilExpired = duration.toDays();
      if (daysUntilExpired < 7) {
        refreshToken = createRefreshToken(authentication);
      } else {
        refreshToken = jwt.getTokenValue();
      }
    } else {
      refreshToken = createRefreshToken(authentication);
    }
    tokenDTO.setRefreshToken(refreshToken);

    return tokenDTO;
  }
}
