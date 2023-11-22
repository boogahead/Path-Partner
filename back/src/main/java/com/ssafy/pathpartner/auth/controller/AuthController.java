package com.ssafy.pathpartner.auth.controller;

import com.ssafy.pathpartner.auth.TokenGenerator;
import com.ssafy.pathpartner.auth.dto.TokenDTO;
import com.ssafy.pathpartner.user.dto.LoginDto;
import com.ssafy.pathpartner.user.dto.ResetPasswordDto;
import com.ssafy.pathpartner.user.dto.SignUpDto;
import com.ssafy.pathpartner.user.dto.UserDto;
import com.ssafy.pathpartner.user.dto.UserInfoDto;
import com.ssafy.pathpartner.user.exception.InvalidInputException;
import com.ssafy.pathpartner.user.exception.UserNotFoundException;
//import com.ssafy.pathpartner.user.service.FileStorageService;
import com.ssafy.pathpartner.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = {"인증 컨트롤러 API"})
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
  public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto loginDto) {
    log.debug("login call");

    Authentication authentication = daoAuthenticationProvider.authenticate(
        UsernamePasswordAuthenticationToken.unauthenticated(loginDto.getId(),
            loginDto.getPassword()));

    TokenDTO tokenDto = tokenGenerator.createToken((authentication));
    try {
      UserInfoDto userInfoDto = userService.searchUserById(tokenDto.getUserId());
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("userInfo", userInfoDto);
      responseBody.put("accessToken", tokenDto.getAccessToken());
      responseBody.put("refreshToken", tokenDto.getRefreshToken());
      return ResponseEntity.ok().body(responseBody);
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
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Boolean> logout(@AuthenticationPrincipal UserDto userDto) {
    log.debug("logout call");
    return ResponseEntity.ok().body(true);
  }

//  @Autowired
//  private FileStorageService fileStorageService;
  @ApiOperation(value = "회원가입", notes = "id, email, password, nickname으로 회원가입합니다.")
  @ApiResponses({@ApiResponse(code = 201, message = "회원 가입 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @PostMapping("/register")
  public ResponseEntity<Boolean> registerUser(@ModelAttribute SignUpDto signUpDto) {
    log.debug("registerUser call");

    try {
      MultipartFile profileImg = signUpDto.getProfileImg();
//        if (profileImg != null && !profileImg.isEmpty()) {
//            String filePath=fileStorageService.storeFile(profileImg);
//        }
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

  private byte[] loadDefaultImage() throws IOException {
    // Assuming the default image is in the resources directory
    ClassPathResource defaultImage = new ClassPathResource("defaultPicture.jpg");
    return StreamUtils.copyToByteArray(defaultImage.getInputStream());
  }
  @ApiOperation(value = "비밀번호 초기화", notes = "비밀번호를 초기화합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "초기화 성공"),
      @ApiResponse(code = 401, message = "권한 없음"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @PostMapping("/password")
  public ResponseEntity<Map<String, Object>> resetPassword(@ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @RequestBody ResetPasswordDto resetPasswordDto) {
    log.debug("resetPassword call");
    try {
      String result = userService.resetPassword(resetPasswordDto);
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("tempPassword", result);
      return ResponseEntity.ok().body(responseBody);
    } catch (UserNotFoundException e) {
      log.debug(e.toString());
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }
  @ApiOperation(value = "아이디 찾기", notes = "이메일로 아이디를 찾습니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "아이디 찾기 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @GetMapping("/{email}")
  public ResponseEntity<Map<String, Object>> findIdByEmail(@PathVariable String email) {
    log.debug("findIdByEmail call");

    try {
      String result = userService.searchUserByEmail(email);
      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("id", result);
      return ResponseEntity.ok().body(responseBody);
    } catch (UserNotFoundException e) {
      log.debug(e.toString());
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }
}
