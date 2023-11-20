package com.ssafy.pathpartner.travelgroup.controller;

import com.ssafy.pathpartner.travelgroup.dto.GroupMemberDto;
import com.ssafy.pathpartner.travelgroup.exception.MasterCanNotLeaveGroupException;
import com.ssafy.pathpartner.travelgroup.exception.UnauthoriedGroupRequestException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
import com.ssafy.pathpartner.travelgroup.service.TravelGroupService;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/group")
@Api(tags = {"그룹 컨트롤러 API"})
@PreAuthorize("hasRole('USER')")
public class TravelGroupController {

  private final TravelGroupService travelGroupService;

  @Autowired
  public TravelGroupController(TravelGroupService travelGroupService) {
    this.travelGroupService = travelGroupService;
  }

  @ApiOperation(value = "그룹 생성", notes = "그룹을 생성합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "그룹 생성 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @PostMapping
  public ResponseEntity<Boolean> registerGroup(@ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @RequestBody TravelGroupDto travelGroupDto) {
    log.debug("registerGroup call");

    travelGroupDto.setUuid(userDto.getUuid());
    travelGroupDto.setGroupMaster(true);

    try {
      return ResponseEntity.ok().body(travelGroupService.createTravelGroup(travelGroupDto));
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "그룹 삭제", notes = "그룹을 삭제합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "그룹 삭제 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @DeleteMapping("/{groupId}")
  public ResponseEntity<Boolean> removeGroup(@ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @PathVariable String groupId) {
    log.debug("removeGroup call");

    try {
      return ResponseEntity.ok()
          .body(travelGroupService.deleteTravelGroup(groupId, userDto.getUuid()));
    } catch (UnauthoriedGroupRequestException e) {
      log.debug(e.toString());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "그룹 나가기", notes = "그룹을 나갑니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "그룹 나가기 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @DeleteMapping("/leave/{groupId}")
  public ResponseEntity<Boolean> leaveGroup(@ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @PathVariable String groupId) {
    log.debug("leaveGroup call");

    try {
      return ResponseEntity.ok()
          .body(travelGroupService.leaveTravelGroup(groupId, userDto.getUuid()));
    } catch (UnauthoriedGroupRequestException e) {
      log.debug(e.toString());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (MasterCanNotLeaveGroupException e) {
      log.debug(e.toString());
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "그룹 멤버 정보 불러오기", notes = "그룹 멤버들의 정보를 가져옵니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "불러오기 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @GetMapping("/member/{groupId}")
  public ResponseEntity<List<GroupMemberDto>> getGroupMember(@PathVariable String groupId) {
    log.debug("getGroupMember call");

    try {
      return ResponseEntity.ok().body(travelGroupService.searchGroupMember(groupId));
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "가입한 그룹 불러오기", notes = "내가 가입한 그룹들을 가져옵니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "불러오기 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @GetMapping
  public ResponseEntity<List<TravelGroupDto>> getGroupList(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto) {
    log.debug("getGroupList call");

    try {
      return ResponseEntity.ok().body(travelGroupService.searchAllGroup(userDto.getUuid()));
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }
}
