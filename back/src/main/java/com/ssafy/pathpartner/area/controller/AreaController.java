package com.ssafy.pathpartner.area.controller;

import java.util.List;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.pathpartner.area.dto.GugunDto;
import com.ssafy.pathpartner.area.dto.SidoDto;
import com.ssafy.pathpartner.area.service.AreaService;

@RestController()
@CrossOrigin("*")
@RequestMapping("/area")
@Api(tags = { "지역 컨트롤러 API" })
public class AreaController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AreaController.class);
	private AreaService areaService;

	@Autowired
	public AreaController(AreaService areaService) {
		this.areaService = areaService;
	}

	@ApiOperation(value = "시도 조회", notes = "시도를 조회합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원 조회 완료"), @ApiResponse(code = 203, message = "조회 결과 없음"),
			@ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping("/sido")
	public ResponseEntity<List<SidoDto>> getSidoCode() {
		LOGGER.debug("getSidoCode called");
		List<SidoDto> list = areaService.getSidoCode();
		if (list != null) {
			return ResponseEntity.ok().body(list);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ApiOperation(value = "군구 조회", notes = "군구를 조회합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원 조회 완료"), @ApiResponse(code = 203, message = "조회 결과 없음"),
			@ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping("/gungu/{sidoCode}")
	public ResponseEntity<List<GugunDto>> getGunguCode(
			@PathVariable("sidoCode")
			@ApiParam(value = "시도 코드", required = true) int sidoCode)
	{
		LOGGER.debug("getSidoCode called");
		List<GugunDto> list = areaService.getGunguCode(sidoCode);
		if (list != null) {
			return ResponseEntity.ok().body(list);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
}