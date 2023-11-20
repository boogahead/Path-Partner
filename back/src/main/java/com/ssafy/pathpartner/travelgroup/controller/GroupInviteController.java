package com.ssafy.pathpartner.travelgroup.controller;

import com.ssafy.pathpartner.travelgroup.exception.TravelGroupNotFoundException;
import com.ssafy.pathpartner.user.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
import com.ssafy.pathpartner.travelgroup.service.TravelGroupService;
import com.ssafy.pathpartner.travelgroup.dto.GroupInviteDto;
import com.ssafy.pathpartner.travelgroup.service.GroupInviteService;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/group-invite")
@Api(tags = {"그룹초대 컨트롤러 API"})
@PreAuthorize("hasRole('USER')")
public class GroupInviteController {

  private final GroupInviteService groupInviteService;

  @Autowired
  public GroupInviteController(GroupInviteService groupInviteService) {
    this.groupInviteService = groupInviteService;
  }

  @ApiOperation(value = "그룹 초대", notes = "그룹에 초대합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "그룹 초대 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @PostMapping("/{groupId}/{inviteTo}")
  public ResponseEntity<Boolean> registerGroupInvite(@PathVariable String groupId,
      @PathVariable String inviteTo) {
    log.debug("registerGroupInvite call");
    GroupInviteDto groupInviteDto = GroupInviteDto.builder()
        .groupId(groupId)
        .inviteTo(inviteTo)
        .build();

    try {
      return ResponseEntity.ok().body(groupInviteService.createGroupInvite(groupInviteDto));
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "그룹 초대 수락", notes = "그룹 초대를 수락합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "그룹 초대 수락 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @PutMapping("/accept/{groupId}")
  public ResponseEntity<Boolean> acceptGroupInvite(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto, @PathVariable String groupId) {
    log.debug("acceptGroupInvite call");

    GroupInviteDto groupInviteDto = GroupInviteDto.builder()
        .groupId(groupId)
        .inviteTo(userDto.getUuid())
        .build();

    try {
      return ResponseEntity.ok().body(groupInviteService.acceptGroupInvite(groupInviteDto));
    } catch (TravelGroupNotFoundException e) {
      log.debug(e.toString());
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "그룹 초대 거절", notes = "그룹 초대를 거절합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "그룹 초대 거절 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @DeleteMapping("/reject/{groupId}")
  public ResponseEntity<Boolean> deleteGroupInvite(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto, @PathVariable String groupId) {
    log.debug("deleteGroupInvite call");

    GroupInviteDto groupInviteDto = GroupInviteDto.builder()
        .groupId(groupId)
        .inviteTo(userDto.getUuid())
        .build();

    try {
      return ResponseEntity.ok().body(groupInviteService.deleteGroupInvite(groupInviteDto));
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "그룹 초대 요청 확인", notes = "내가 받은 그룹 초대를 불러옵니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "그룹 초대 확인 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @GetMapping
  public ResponseEntity<List<GroupInviteDto>> checkGroupInvite(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto) {
    log.debug("checkGroupInvite call");
    try {
      return ResponseEntity.ok().body(groupInviteService.searchGroupInvite(userDto.getUuid()));
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "수락대기 그룹원 가져오기", notes = "그룹 초대 리스트를 확인합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "그룹 초대 리스트 확인 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @GetMapping("/pending/{groupId}")
  public ResponseEntity<List<GroupInviteDto>> getPendingInviteList(@PathVariable String groupId) {
    log.debug("getPendingInviteList call");

    try {
      return ResponseEntity.ok().body(groupInviteService.searchAllPendingInvite(groupId));
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "그룹 초대 취소", notes = "그룹 초대를 취소합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "그룹 초대 취소 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @DeleteMapping("/cancel/{groupId}/{inviteTo}")
  public ResponseEntity<Boolean> cancelGroupInvite(@PathVariable String groupId,
      @PathVariable String inviteTo) {
    log.debug("cancelGroupInvite call");

    GroupInviteDto groupInviteDto = GroupInviteDto.builder()
        .groupId(groupId)
        .inviteTo(inviteTo)
        .build();

    try {
      return ResponseEntity.ok().body(groupInviteService.deleteGroupInvite(groupInviteDto));
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

}
