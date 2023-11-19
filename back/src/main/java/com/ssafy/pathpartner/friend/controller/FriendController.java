package com.ssafy.pathpartner.friend.controller;

import com.ssafy.pathpartner.friend.dto.FriendDto;
import com.ssafy.pathpartner.friend.dto.FriendInfoDto;
import com.ssafy.pathpartner.friend.service.FriendService;
import com.ssafy.pathpartner.user.dto.UserDto;
import com.ssafy.pathpartner.user.exception.InvalidInputException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.sql.SQLException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("/friend")
public class FriendController {

  private final FriendService friendService;

  @Autowired
  public FriendController(FriendService friendService) {
    this.friendService = friendService;
  }

  @ApiOperation(value = "내 친구 가져오기", notes = "나의 친구들을 가져옵니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "가져오기 성공"),
      @ApiResponse(code = 500, message = "서버에러")})
  @GetMapping
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<List<FriendInfoDto>> getMyFriendList(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto) {
    log.debug("getMyFriendList call");

    try {
      List<FriendInfoDto> result = friendService.searchAllMyFriend(userDto.getUuid());
      return ResponseEntity.ok().body(result);
    } catch (InvalidInputException e) {
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "친구요청", notes = "친구요청을 보냅니다")
  @ApiResponses({@ApiResponse(code = 200, message = "요청 성공"),
      @ApiResponse(code = 204, message = "검색 결과 없음"),
      @ApiResponse(code = 500, message = "서버에러")})
  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Boolean> sendFriendRequest(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto, @RequestBody FriendDto friendDto) {
    friendDto.setFriendFrom(userDto.getUuid());

    try {
      return ResponseEntity.ok()
          .body(friendService.createFriendRequest(friendDto));
    } catch (InvalidInputException e) {
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "친구요청수락", notes = "친구요청을 수락합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "수락 성공"),
      @ApiResponse(code = 204, message = "검색 결과 없음"),
      @ApiResponse(code = 500, message = "서버에러")})
  @PatchMapping("/accept")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Boolean> acceptFriendRequest(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto, @RequestBody FriendDto friendDto) {
    String temp = friendDto.getFriendFrom();
    friendDto.setFriendFrom(userDto.getUuid());
    friendDto.setFriendTo(temp);
    friendDto.setAccepted(true);

    try {
      return ResponseEntity.ok()
          .body(friendService.updateFriend(friendDto));
    } catch (InvalidInputException e) {
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "친구 요청 취소", notes = "친구요청을 취소합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "취소 성공"),
      @ApiResponse(code = 204, message = "검색 결과 없음"),
      @ApiResponse(code = 500, message = "서버에러")})
  @DeleteMapping("/cancel/{friendTo}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Boolean> cancelFriendRequest(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto, @PathVariable String friendTo) {
    FriendDto friendDto = FriendDto.builder()
        .friendFrom(userDto.getUuid())
        .friendTo(friendTo)
        .build();
    try {
      return ResponseEntity.ok()
          .body(friendService.deleteFriend(friendDto));
    } catch (InvalidInputException e) {
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "친구 삭제", notes = "친구를 삭제합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "삭제 성공"),
      @ApiResponse(code = 204, message = "검색 결과 없음"),
      @ApiResponse(code = 500, message = "서버에러")})
  @DeleteMapping
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Boolean> deleteFriend(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto, @RequestParam String friendUuid) {
    FriendDto friendDto = FriendDto.builder()
        .friendFrom(userDto.getUuid())
        .friendTo(friendUuid)
        .build();

    try {
      return ResponseEntity.ok()
          .body(friendService.deleteFriend(friendDto));
    } catch (InvalidInputException e) {
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "친구 요청 거절", notes = "친구요청을 거절합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "거절 성공"),
      @ApiResponse(code = 204, message = "검색 결과 없음"),
      @ApiResponse(code = 500, message = "서버에러")})
  @DeleteMapping("/reject/{friendFrom}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Boolean> rejectFriendRequest(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto, @PathVariable String friendFrom) {
    FriendDto friendDto = FriendDto.builder()
        .friendFrom(friendFrom)
        .friendTo(userDto.getUuid())
        .build();

    try {
      return ResponseEntity.ok()
          .body(friendService.deleteFriend(friendDto));
    } catch (InvalidInputException e) {
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "내가 보낸 요청 가져오기", notes = "내가 보낸 친구 요청을 가져옵니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "가져오기 성공"),
      @ApiResponse(code = 500, message = "서버에러")})
  @GetMapping("/sent")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<List<FriendInfoDto>> getFriendRequestSent(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto) {

    try {
      return ResponseEntity.ok()
          .body(friendService.searchAllMyFriendRequest(userDto.getUuid()));
    } catch (InvalidInputException e) {
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "내가 받은 요청 가져오기", notes = "내가 받은 친구 요청을 가져옵니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "가져오기 성공"),
      @ApiResponse(code = 500, message = "서버에러")})
  @GetMapping("/received")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<List<FriendInfoDto>> getFriendRequestReceived(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto) {

    try {
      return ResponseEntity.ok()
          .body(friendService.searchAllMyFriendRequestReceived(userDto.getUuid()));
    } catch (InvalidInputException e) {
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
