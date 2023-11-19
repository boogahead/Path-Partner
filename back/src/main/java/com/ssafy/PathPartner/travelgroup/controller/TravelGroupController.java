package com.ssafy.pathpartner.travelgroup.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.ssafy.pathpartner.travelgroup.dto.TravelGroupDto;
import com.ssafy.pathpartner.travelgroup.service.TravelGroupService;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/group")
@Api(tags = {"그룹 컨트롤러 API"})
public class TravelGroupController {

      private TravelGroupService travelGroupService;

      @Autowired
      public TravelGroupController(TravelGroupService travelGroupService) {
     this.travelGroupService = travelGroupService;
      }

      @ApiOperation(value = "그룹 등록", notes = "그룹을 등록합니다.")
      @ApiResponses({@ApiResponse(code = 200, message = "그룹 등록 시도 성공"),
        @ApiResponse(code = 500, message = "서버 에러")})
      @PostMapping
      public ResponseEntity<Boolean> registGroup(@RequestBody TravelGroupDto travelGroupDto) {
     log.debug("registGroup call");

     try {
        travelGroupService.createTravelGroup(travelGroupDto);
        return ResponseEntity.ok().body(true);
     } catch (SQLException e) {
        log.debug(e.toString());
        return ResponseEntity.internalServerError().build();
     }
      }

      @ApiOperation(value = "그룹 삭제", notes = "그룹을 삭제합니다.")
      @ApiResponses({@ApiResponse(code = 200, message = "그룹 삭제 시도 성공"),
        @ApiResponse(code = 500, message = "서버 에러")})
      @DeleteMapping("/{groupId}")
      public ResponseEntity<Boolean> deleteGroup(String groupId) {
     log.debug("deleteGroup call");

     try {
        travelGroupService.deleteTravelGroup(groupId);
        return ResponseEntity.ok().body(true);
     } catch (SQLException e) {
        log.debug(e.toString());
        return ResponseEntity.internalServerError().build();
     }
      }

      @ApiOperation(value = "그룹 탈퇴", notes = "그룹을 탈퇴합니다.")
      @ApiResponses({@ApiResponse(code = 200, message = "그룹 탈퇴 시도 성공"),
        @ApiResponse(code = 500, message = "서버 에러")})
      @DeleteMapping("/leave/{groupId}")
      public ResponseEntity<Boolean> leaveGroup(String groupId) {
          log.debug("leaveGroup call");

          try {
              travelGroupService.leaveTravelGroup(groupId);
              return ResponseEntity.ok().body(true);
          } catch (SQLException e) {
              log.debug(e.toString());
              return ResponseEntity.internalServerError().build();
          }
      }

      @ApiOperation(value = "그룹장인지 확인", notes = "그룹장인지 확인합니다.")
      @ApiResponses({@ApiResponse(code = 200, message = "그룹장인지 확인 시도 성공"),
        @ApiResponse(code = 500, message = "서버 에러")})
      @GetMapping("/master/{groupId}")
      public ResponseEntity<Boolean> checkGroupMaster(String groupId) {
          log.debug("checkGroupMaster call");

          try {
              int result = travelGroupService.checkGroupMaster(groupId);
              if (result == 1) {
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
