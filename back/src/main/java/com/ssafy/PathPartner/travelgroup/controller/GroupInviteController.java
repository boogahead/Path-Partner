package com.ssafy.pathpartner.travelgroup.controller;
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
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
import com.ssafy.pathpartner.travelgroup.service.TravelGroupService;
import com.ssafy.pathpartner.travelgroup.dto.GroupInviteDto;
import com.ssafy.pathpartner.travelgroup.service.GroupInviteService;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/groupInvite")
@Api(tags = {"그룹초대 컨트롤러 API"})
public class GroupInviteController {

        private GroupInviteService groupInviteService;

        @Autowired
        public GroupInviteController(GroupInviteService groupInviteService) {
      this.groupInviteService = groupInviteService;
        }

        @ApiOperation(value = "그룹 초대", notes = "그룹을 초대합니다.")
        @ApiResponses({@ApiResponse(code = 200, message = "그룹 초대 시도 성공"),
          @ApiResponse(code = 500, message = "서버 에러")})
        @PostMapping("/{groupId}/{inviteTo}")
        public ResponseEntity<Boolean> registGroupInvite(@PathVariable String groupId, @PathVariable String inviteTo) {
      log.debug("registGroupInvite call");

      try {
          groupInviteService.createGroupInvite(groupId,inviteTo);
          return ResponseEntity.ok().body(true);
      } catch (SQLException e) {
          log.debug(e.toString());
          return ResponseEntity.internalServerError().build();
      }
        }

        @ApiOperation(value = "그룹 초대 수락", notes = "그룹 초대를 수락합니다.")
        @ApiResponses({@ApiResponse(code = 200, message = "그룹 초대 수락 시도 성공"),
          @ApiResponse(code = 500, message = "서버 에러")})
        @PutMapping("/accept/{groupId}")
        public ResponseEntity<Boolean> acceptGroupInvite(@PathVariable String groupId) {
      log.debug("acceptGroupInvite call");

      try {
          groupInviteService.acceptGroupInvite(groupId);
          return ResponseEntity.ok().body(true);
      } catch (SQLException e) {
          log.debug(e.toString());
          return ResponseEntity.internalServerError().build();
      }
        }

        @ApiOperation(value = "그룹 초대 거절", notes = "그룹 초대를 거절합니다.")
        @ApiResponses({@ApiResponse(code = 200, message = "그룹 초대 거절 시도 성공"),
          @ApiResponse(code = 500, message = "서버 에러")})
        @DeleteMapping("/decline/{groupId}")
        public ResponseEntity<Boolean> deleteGroupInvite(@PathVariable String groupId) {
            log.debug("deleteGroupInvite call");

            try {
                groupInviteService.deleteGroupInvite(groupId);
                return ResponseEntity.ok().body(true);
            } catch (SQLException e) {
                log.debug(e.toString());
                return ResponseEntity.internalServerError().build();
            }
        }

        @ApiOperation(value = "그룹 초대 확인", notes = "그룹 초대를 확인합니다.")
        @ApiResponses({@ApiResponse(code = 200, message = "그룹 초대 확인 시도 성공"),
          @ApiResponse(code = 500, message = "서버 에러")})
        @GetMapping("/check")
        public ResponseEntity<List<String>> checkGroupInvite() {
            log.debug("checkGroupInvite call");
            try {
                List<String>result=groupInviteService.checkGroupInvite();
                return ResponseEntity.ok().body(result);
            } catch (SQLException e) {
                log.debug(e.toString());
                return ResponseEntity.internalServerError().build();
            }
        }

        @ApiOperation(value = "해당 그룹의 초대를 수락 대기중인 인원들의 리스트 확인", notes = "그룹 초대 리스트를 확인합니다.")
        @ApiResponses({@ApiResponse(code = 200, message = "그룹 초대 리스트 확인 시도 성공"),
          @ApiResponse(code = 500, message = "서버 에러")})
        @GetMapping("/pending/{groupId}")
        public ResponseEntity<List<String>> getPendingInviteList(@PathVariable String groupId) {
            log.debug("getPendingInviteList call");
            try {
                List<String>result=groupInviteService.getPendingInviteList(groupId);
                return ResponseEntity.ok().body(result);
            } catch (SQLException e) {
                log.debug(e.toString());
                return ResponseEntity.internalServerError().build();
            }
        }

        @ApiOperation(value="그룹 초대 취소", notes="그룹 초대를 취소합니다.")
        @ApiResponses({@ApiResponse(code=200, message="그룹 초대 취소 성공"),
            @ApiResponse(code=500, message="서버 에러")})
        @DeleteMapping("/cancel/{groupId}/{inviteTo}")
        public ResponseEntity<Boolean> cancelGroupInvite(@PathVariable String groupId, @PathVariable String inviteTo) {
            log.debug("cancelGroupInvite call");
            try {
                groupInviteService.cancelGroupInvite(groupId, inviteTo);
                return ResponseEntity.ok().body(true);
            } catch (SQLException e) {
                log.debug(e.toString());
                return ResponseEntity.internalServerError().build();
            }
        }

}
