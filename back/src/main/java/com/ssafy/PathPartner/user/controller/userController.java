package com.ssafy.pathpartner.user.controller;

import com.ssafy.pathpartner.user.dto.UserDto;
import com.ssafy.pathpartner.user.service.userService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/user")
@Api(tags = {"사용자 컨트롤러 API"})
public class UserController {

  private userService userService;

  @Autowired
  public UserController(userService userService) {
    this.userService = userService;
  }

  @ApiOperation(value = "회원조회", notes = "회원을 조회합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "회원 조회 완료"),
      @ApiResponse(code = 203, message = "조회 결과 없음"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @GetMapping
  public ResponseEntity<UserDto> getMember(String userId) {
    log.debug("getMember call");

    try {
      UserDto result = userService.searchUserById(userId);
      if (result != null) {
        return ResponseEntity.ok()
            .body(result);
      } else {
        return ResponseEntity.noContent().build();
      }
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "회원 등록", notes = "회원을 등록합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "회원 등록 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @PostMapping
  public ResponseEntity<Boolean> registMember(@RequestBody UserDto userDto) {
    log.debug("registMember call");

    try {
      int result = userService.registUser(userDto);
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

  @ApiOperation(value = "회원 삭제", notes = "회원을 삭제합니다")
  @ApiResponses({@ApiResponse(code = 200, message = "회원 삭제 시도 성공"),
      @ApiResponse(code = 401, message = "권한 없음"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @DeleteMapping
  public ResponseEntity<Boolean> deleteMember(@ApiIgnore HttpSession session) {
    log.debug("deleteMember call");

    UserDto loginUser = (UserDto) session.getAttribute("loginMember");
    //if (loginUser == null) {
      //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    /*} else*/ {
      try {
        int result = userService.deleteUser(loginUser.getUuid());
        if (result > 0) {
          session.invalidate();
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

  @ApiOperation(value = "회원정보수정", notes = "회원정보를 수정합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "수정 시도 성공"),
      @ApiResponse(code = 401, message = "권한 없음"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @PutMapping
  public ResponseEntity<Boolean> updateMember(@RequestParam String password,
        @RequestParam String Nickname,
      @ApiIgnore HttpSession session) {
    log.debug("updateMember call");

    /*UserDto loginMember = (UserDto) session.getAttribute("loginMember");

    if (loginMember == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }*/

    try {
      int result = userService.modifyUser(password,Nickname);
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

  @ApiOperation(value = "로그인", notes = "로그인합니다")
  @ApiResponses({@ApiResponse(code = 200, message = "로그인 시도 성공"),
      @ApiResponse(code = 500, message = "서버에러")})
  @PostMapping("/login")
  public ResponseEntity<Boolean> login(String userId,String userPass,
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
