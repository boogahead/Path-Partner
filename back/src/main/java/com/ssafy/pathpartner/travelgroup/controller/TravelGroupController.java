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

    @ApiOperation(value = "그룹원 추방", notes = "그룹원을 쫓아냅니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "그룹원 추방 시도 성공"),
            @ApiResponse(code = 500, message = "서버 에러")})
    @DeleteMapping("/{groupId}/{uuid}")
    public ResponseEntity<Boolean> kickmember(@PathVariable String groupId, @PathVariable String uuid
            , @ApiIgnore @AuthenticationPrincipal UserDto userDto) {
        log.debug("kickmember call");
        try {
            return ResponseEntity.ok().body(travelGroupService.kickmember(groupId, uuid, userDto.getUuid()));
        } catch (SQLException e) {
            log.debug(e.toString());
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "그룹원 초대", notes = "그룹원을 초대합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "그룹원 초대 시도 성공"),
            @ApiResponse(code = 500, message = "서버 에러")})
    @PostMapping("/invite/{groupId}/{uuid}")
    public ResponseEntity<Boolean> inviteGroupMember(@PathVariable String groupId, @PathVariable String uuid
            , @ApiIgnore @AuthenticationPrincipal UserDto userDto) {
        log.debug("inviteGroupMember call");
        TravelGroupDto travelGroupDto = TravelGroupDto.builder()
                .groupId(groupId)
                .uuid(uuid)
                .build();
        try {
            return ResponseEntity.ok().body(travelGroupService.inviteGroupMember(travelGroupDto, userDto.getUuid()));
        } catch (SQLException e) {
            log.debug(e.toString());
            return ResponseEntity.internalServerError().build();

        }
    }

    @ApiOperation(value = "그룹원 초대 취소", notes = "그룹원 초대를 취소합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "그룹원 초대 취소 시도 성공"),
            @ApiResponse(code = 500, message = "서버 에러")})
    @DeleteMapping("/invite/{groupId}/{uuid}")
    public ResponseEntity<Boolean> cancelInviteGroupMember(@PathVariable String groupId, @PathVariable String uuid
            , @ApiIgnore @AuthenticationPrincipal UserDto userDto) {
        log.debug("cancelInviteGroupMember call");
        TravelGroupDto travelGroupDto = TravelGroupDto.builder()
                .groupId(groupId)
                .uuid(uuid)
                .build();
        try {
            return ResponseEntity.ok().body(travelGroupService.cancelInviteGroupMember(travelGroupDto, userDto.getUuid()));
        } catch (SQLException e) {
            log.debug(e.toString());
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "그룹원 초대 수락", notes = "그룹원 초대를 수락합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "그룹원 초대 수락 시도 성공"),
            @ApiResponse(code = 500, message = "서버 에러")})
    @PutMapping("/invite/{groupId}")
    public ResponseEntity<Boolean> acceptInvite(@PathVariable String groupId
            , @ApiIgnore @AuthenticationPrincipal UserDto userDto) {
        log.debug("acceptInvite call");
        TravelGroupDto travelGroupDto = TravelGroupDto.builder()
                .groupId(groupId)
                .uuid(userDto.getUuid())
                .build();
        try {
            return ResponseEntity.ok().body(travelGroupService.acceptInvite(travelGroupDto));
        } catch (SQLException e) {
            log.debug(e.toString());
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "그룹원 초대 거절", notes = "그룹원 초대를 거절합니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "그룹원 초대 거절 시도 성공"),
            @ApiResponse(code = 500, message = "서버 에러")})
    @DeleteMapping("/invite/{groupId}")
    public ResponseEntity<Boolean> rejectInvite(@PathVariable String groupId
            , @ApiIgnore @AuthenticationPrincipal UserDto userDto) {
        log.debug("rejectInvite call");
        TravelGroupDto travelGroupDto = TravelGroupDto.builder()
                .groupId(groupId)
                .uuid(userDto.getUuid())
                .build();
        try {
            return ResponseEntity.ok().body(travelGroupService.rejectInvite(travelGroupDto));
        } catch (SQLException e) {
            log.debug(e.toString());
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "초대받은 목록", notes = "초대받은 그룹들의 목록을 가져옵니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "초대받은 그룹들 목록 가져오기 시도 성공"),
            @ApiResponse(code = 500, message = "서버 에러")})
    @GetMapping("/invite/received")
    public ResponseEntity<List<TravelGroupDto>> receivedInviteList(
            @ApiIgnore @AuthenticationPrincipal UserDto userDto) {
        log.debug("receivedInviteList call");
        try {
            return ResponseEntity.ok().body(travelGroupService.receivedInviteList(userDto.getUuid()));
        } catch (SQLException e) {
            log.debug(e.toString());
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "그룹원 초대 목록", notes = "그룹원 초대 목록을 가져옵니다.")
    @ApiResponses({@ApiResponse(code = 200, message = "그룹원 초대 목록 가져오기 시도 성공"),
            @ApiResponse(code = 500, message = "서버 에러")})
    @GetMapping("/invite/sent/{groupId}")
    public ResponseEntity<List<GroupMemberDto>> sentInviteList(@PathVariable String groupId
            , @ApiIgnore @AuthenticationPrincipal UserDto userDto) {
        log.debug("sentInviteList call");
        try {
            return ResponseEntity.ok().body(travelGroupService.sentInviteList(groupId));
        } catch (SQLException e) {
            log.debug(e.toString());
            return ResponseEntity.internalServerError().build();
        }
    }

}
