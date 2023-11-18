package com.ssafy.pathpartner.auth.controller;

import com.ssafy.pathpartner.auth.TokenGenerator;
import com.ssafy.pathpartner.auth.dto.TokenDTO;
import com.ssafy.pathpartner.user.dto.LoginDto;
import com.ssafy.pathpartner.user.dto.SignUpDto;
import com.ssafy.pathpartner.user.dto.UserInfoDto;
import com.ssafy.pathpartner.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;
  private final TokenGenerator tokenGenerator;
  private final DaoAuthenticationProvider daoAuthenticationProvider;
  @Qualifier("jwtRefreshTokenAuthProvider")
  private final JwtAuthenticationProvider refreshTokenAuthProvider;

  @Autowired
  public AuthController(UserService userService, TokenGenerator tokenGenerator,
      DaoAuthenticationProvider daoAuthenticationProvider,
      JwtAuthenticationProvider refreshTokenAuthProvider) {
    this.userService = userService;
    this.tokenGenerator = tokenGenerator;
    this.daoAuthenticationProvider = daoAuthenticationProvider;
    this.refreshTokenAuthProvider = refreshTokenAuthProvider;
  }

  @ApiOperation(value = "로그인", notes = "로그인합니다")
  @ApiResponses({@ApiResponse(code = 200, message = "로그인 시도 성공"),
      @ApiResponse(code = 500, message = "서버에러")})
  @PostMapping("/login")
  public ResponseEntity<UserInfoDto> login(@RequestBody LoginDto loginDto) {
    log.debug("login call");

    Authentication authentication = daoAuthenticationProvider.authenticate(
        UsernamePasswordAuthenticationToken.unauthenticated(loginDto.getId(),
            loginDto.getPassword()));

    TokenDTO tokenDto = tokenGenerator.createToken((authentication));
    try {
      UserInfoDto userInfoDto = userService.searchUserById(tokenDto.getUserId());
      return ResponseEntity.ok()
          .header("Authorization", tokenDto.getAccessToken(), tokenDto.getRefreshToken())
          .body(userInfoDto);
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "토큰 재발급", notes = "토큰을 재발급합니다")
  @ApiResponses({@ApiResponse(code = 200, message = "재발급 성공"),
      @ApiResponse(code = 500, message = "서버에러")})
  @PostMapping("/token")
  public ResponseEntity token(@RequestBody TokenDTO tokenDTO) {
    Authentication authentication = refreshTokenAuthProvider.authenticate(
        new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));
    Jwt jwt = (Jwt) authentication.getCredentials();
    // check if present in db and not revoked, etc

    return ResponseEntity.ok(tokenGenerator.createToken(authentication));
  }

  @ApiOperation(value = "로그 아웃", notes = "로그아웃 합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "로그아웃 완료")})
  @GetMapping("/logout")
  public ResponseEntity<Boolean> logout(@ApiIgnore HttpSession session) {
    log.debug("logout call");
    session.invalidate();
    return ResponseEntity.ok().body(true);
  }

  @ApiOperation(value = "회원가입", notes = "id, email, password, nickname으로 회원가입합니다.")
  @ApiResponses({@ApiResponse(code = 201, message = "회원 가입 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @PostMapping
  public ResponseEntity<Boolean> registerUser(@RequestBody SignUpDto signUpDto) {
    log.debug("registerUser call");

    try {
      boolean result = userService.createUser(signUpDto);
      if (result) {
        return ResponseEntity.created(URI.create("/")).build();
      } else {
        return ResponseEntity.ok().build();
      }
    } catch (Exception e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }
}
