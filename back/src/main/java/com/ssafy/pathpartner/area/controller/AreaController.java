package com.ssafy.pathpartner.area.controller;

import io.swagger.annotations.ApiParam;
import java.sql.SQLException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pathpartner.area.dto.GugunDto;
import com.ssafy.pathpartner.area.dto.SidoDto;
import com.ssafy.pathpartner.area.service.AreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@CrossOrigin("*")
@RequestMapping("/area")
@Slf4j
@Api(tags = {"지역 컨트롤러 API"})
public class AreaController {

  private final AreaService areaService;

  @Autowired
  public AreaController(AreaService areaService) {
    this.areaService = areaService;
  }

  @ApiOperation(value = "시도 코드 가져오기", notes = "시도 코드를 가져옵니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "시도 코드 조회 완료"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @GetMapping("/sido")
  public ResponseEntity<List<SidoDto>> getSidoCode() {

    log.debug("getSidoCode call");

    try {
      return ResponseEntity.ok().body(areaService.getSidoCode());
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "구군 코드 가져오기", notes = "구군 코드를 가져옵니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "구군 코드 조회 완료"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @GetMapping("/gugun/{sidoCode}")
  public ResponseEntity<List<GugunDto>> getGunguCode(
      @PathVariable("sidoCode") int sidoCode) {

    log.debug("getSidoCode call");

    try {
      return ResponseEntity.ok().body(areaService.getGunguCode(sidoCode));
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

}
