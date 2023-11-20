package com.ssafy.pathpartner.planarticle.controller;

import com.ssafy.pathpartner.planarticle.exception.PlanArticleNotFoundException;
import com.ssafy.pathpartner.planarticle.exception.UnauthoriedPlanRequestException;
import com.ssafy.pathpartner.user.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.sql.SQLException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.ssafy.pathpartner.planarticle.dto.PlanArticleDto;
import com.ssafy.pathpartner.planarticle.service.PlanArticleService;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/plan")
@Api(tags = {"여행계획 컨트롤러 API"})
@PreAuthorize("hasRole('USER')")
public class PlanArticleController {

  private final PlanArticleService planArticleService;

  @Autowired
  public PlanArticleController(PlanArticleService planArticleService) {
    this.planArticleService = planArticleService;
  }

  @ApiOperation(value = "계획 작성", notes = "계획을 작성합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "계획 작성 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @PostMapping
  public ResponseEntity<Boolean> writePlan(@RequestBody PlanArticleDto planArticleDto) {
    log.debug("writePlan call");

    try {
      return ResponseEntity.ok().body(planArticleService.createPlanArticle(planArticleDto));
    } catch (UnauthoriedPlanRequestException e) {
      log.debug(e.toString());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "계획 삭제", notes = "계획을 삭제합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "계획 삭제 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @DeleteMapping("/{planArticleId}")
  public ResponseEntity<Boolean> deletePlan(@ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @PathVariable String planArticleId) {
    log.debug("deletePlan call");

    try {
      return ResponseEntity.ok()
          .body(planArticleService.deletePlanArticle(planArticleId, userDto.getUuid()));
    } catch (UnauthoriedPlanRequestException e) {
      log.debug(e.toString());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (PlanArticleNotFoundException e) {
      log.debug(e.toString());
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "여행계획목록 가져오기", notes = "내가 속한 그룹의 모든 여행계획목록을 가져옵니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "계획 조회 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @GetMapping
  public ResponseEntity<List<PlanArticleDto>> getPlanArticleList(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto) {
    log.debug("getPlanArticleList call");

    try {
      return ResponseEntity.ok().body(planArticleService.searchAllPlanArticle(userDto.getUuid()));
    } catch (PlanArticleNotFoundException e) {
      log.debug(e.toString());
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "여행계획 가져오기", notes = "여행계획을 가져옵니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "계획 조회 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @GetMapping("/{planArticleId}")
  public ResponseEntity<PlanArticleDto> getPlanArticle(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto, @PathVariable String planArticleId) {
    log.debug("getPlanArticle call");

    try {
      return ResponseEntity.ok().body(planArticleService.searchPlanArticle(planArticleId));
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "계획 수정", notes = "계획을 수정합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "계획 수정 시도 성공"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @PutMapping
  public ResponseEntity<Boolean> updatePlan(@ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @RequestBody PlanArticleDto planArticleDto) {
    log.debug("updatePlan call");

    try {
      return ResponseEntity.ok()
          .body(planArticleService.updatePlanArticle(planArticleDto, userDto.getUuid()));
    } catch (UnauthoriedPlanRequestException e) {
      log.debug(e.toString());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (SQLException e) {
      log.debug(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }
}
