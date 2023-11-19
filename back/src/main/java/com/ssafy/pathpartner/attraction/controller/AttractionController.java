package com.ssafy.pathpartner.attraction.controller;

import com.ssafy.pathpartner.attraction.dto.AttractionInfoDto;
import com.ssafy.pathpartner.attraction.service.AttractionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attraction")
@CrossOrigin("*")
@Api(tags = {"관광지 컨트롤러 API"})
public class AttractionController {

  private final AttractionService attractionService;

  @Autowired
  public AttractionController(AttractionService attractionService) {
    this.attractionService = attractionService;
  }

  @ApiOperation(value = "관광지 조회", notes = "관광지를 조회합니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "관광지 조회 완료"),
      @ApiResponse(code = 500, message = "서버 에러")})
  @GetMapping
  public ResponseEntity<List<AttractionInfoDto>> getAttractionInfoList(String sidoCode, String sigunguCode, String[] contentType) {
    try {
      List<AttractionInfoDto> result = attractionService.getAttractionList(sidoCode, sigunguCode,
          contentType);
      if (result != null) {
        return ResponseEntity.ok().body(result);
      } else {
        return ResponseEntity.noContent().build();
      }
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
