package com.ssafy.pathpartner.user.controller;

import com.ssafy.pathpartner.user.dto.SignUpDto;
import com.ssafy.pathpartner.user.dto.UpdateUserDto;
import com.ssafy.pathpartner.user.dto.UserDto;
import com.ssafy.pathpartner.user.dto.UserInfoDto;
import com.ssafy.pathpartner.user.exception.AlreadyExistsUserException;
import com.ssafy.pathpartner.user.exception.UserNotFoundException;
import com.ssafy.pathpartner.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/user")
@Api(tags = {"사용자 컨트롤러 API"})
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ApiOperation(value = "회원 정보 가져오기", notes = "uuid를 통해 회원 정보를 가져옵니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "회원 조회 완료"),
      @ApiResponse(code = 203, message = "조회 결과 없음"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @GetMapping("/{uuid}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable String uuid) {
    log.debug("getUserInfo call");

    // 400 잘못된 요청
    if (uuid == null || uuid.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }

    try {
      return ResponseEntity.ok().body(userService.searchUserByUuid(uuid));
    } catch (UserNotFoundException e) {
      log.debug(e.toString());
      return ResponseEntity.notFound().build();
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
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

  @ApiOperation(value = "회원 삭제", notes = "회원을 삭제합니다")
  @ApiResponses({@ApiResponse(code = 200, message = "회원 삭제 시도 성공"),
      @ApiResponse(code = 401, message = "권한 없음"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @DeleteMapping("/{uuid}")
  @PreAuthorize("hasRole('ADMIM') || #userDto.uuid == #uuid")
  public ResponseEntity<Boolean> deleteUser(@AuthenticationPrincipal UserDto userDto,
      @PathVariable("uuid") String uuid) {
    log.debug("deleteUser call");

    try {
      boolean result = userService.deleteUser(uuid);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "회원 탈퇴", notes = "서비스에서 탈퇴합니다")
  @ApiResponses({@ApiResponse(code = 200, message = "회원 삭제 시도 성공"),
      @ApiResponse(code = 401, message = "권한 없음"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @DeleteMapping
  @PreAuthorize("hasAnyRole('ADMIM','USER')")
  public ResponseEntity<Boolean> withdrawal(@AuthenticationPrincipal UserDto userDto) {
    log.debug("withdrawal call");

    try {
      boolean result = userService.deleteUser(userDto.getUuid());
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "개인정보수정", notes = "회원정보를 수정합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "수정 시도 성공"),
      @ApiResponse(code = 401, message = "권한 없음"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @PutMapping
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public ResponseEntity<Boolean> modifyUser(@AuthenticationPrincipal UserDto userDto,
      @RequestBody UpdateUserDto updateUserDto) {
    log.debug("modifyUser call");

    updateUserDto.setUuid(userDto.getUuid());
    try {
      boolean result = userService.updateUser(updateUserDto);
      return ResponseEntity.ok().body(result);
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "로그인", notes = "로그인합니다")
  @ApiResponses({@ApiResponse(code = 200, message = "로그인 시도 성공"),
      @ApiResponse(code = 500, message = "서버에러")})
  @PostMapping("/login")
  public ResponseEntity<Boolean> login(String userId, String userPass,
      @ApiIgnore HttpSession session) {
    log.debug("login call");

    try {
      UserDto loginMember = userService.login(userId, userPass);
      if (loginMember != null) {
        session.setAttribute("loginMember", loginMember);
        return ResponseEntity.ok().body(true);
      } else {
        return ResponseEntity.ok().body(false);
      }
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "로그 아웃", notes = "로그아웃 합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "로그아웃 완료")})
  @GetMapping("/logout")
  public ResponseEntity<Boolean> logout(@ApiIgnore HttpSession session) {
    log.debug("logout call");
    session.invalidate();
    return ResponseEntity.ok().body(true);
  }

  @ApiOperation(value = "비밀번호 변경", notes = "아이디와 이름을 받아서 비밀번호를 변경합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "변경 시도 성공"),
      @ApiResponse(code = 500, message = "서버에러")})
  @PatchMapping("/password")
  public ResponseEntity<Boolean> modifyPassword(@RequestBody UserDto userDto) {
    log.debug("modifyPassword call");

    try {
      int result = userService.updatePassword(userDto);
      if (result > 0) {
        return ResponseEntity.ok().body(true);
      } else {
        return ResponseEntity.ok().body(false);
      }
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }
}
