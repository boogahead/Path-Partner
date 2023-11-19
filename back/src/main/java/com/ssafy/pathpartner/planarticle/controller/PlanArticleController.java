package com.ssafy.pathpartner.planarticle.controller;
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
import com.ssafy.pathpartner.planarticle.dto.PlanArticleDto;
import com.ssafy.pathpartner.planarticle.service.PlanArticleService;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/plan")
@Api(tags = {"계획 컨트롤러 API"})
public class PlanArticleController {

        private PlanArticleService planArticleService;

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
                planArticleService.writePlan(planArticleDto);
                return ResponseEntity.ok().body(true);
            } catch (SQLException e) {
                log.debug(e.toString());
                return ResponseEntity.internalServerError().build();
            }
        }

        @ApiOperation(value = "계획 삭제", notes = "계획을 삭제합니다.")
        @ApiResponses({@ApiResponse(code = 200, message = "계획 삭제 시도 성공"),
        @ApiResponse(code = 500, message = "서버 에러")})
        @DeleteMapping("/{planArticleId}")
        public ResponseEntity<Boolean> deletePlan(@PathVariable String planArticleId) {
            log.debug("deletePlan call");

            try {
                planArticleService.deletePlan(planArticleId);
                return ResponseEntity.ok().body(true);
            } catch (SQLException e) {
                log.debug(e.toString());
                return ResponseEntity.internalServerError().build();
            }
        }

        @ApiOperation(value = "계획 조회", notes = "계획을 조회합니다.")
        @ApiResponses({@ApiResponse(code = 200, message = "계획 조회 시도 성공"),
        @ApiResponse(code = 500, message = "서버 에러")})
        @GetMapping("/{groupId}")
        public ResponseEntity<List<PlanArticleDto>> getPlanList(@PathVariable String groupId) {
            log.debug("getPlanList call");

            try {
                List<PlanArticleDto> result = planArticleService.getPlanList(groupId);
                return ResponseEntity.ok().body(result);
            } catch (SQLException e) {
                log.debug(e.toString());
                return ResponseEntity.internalServerError().build();
            }
        }

        @ApiOperation(value = "계획 수정", notes = "계획을 수정합니다.")
        @ApiResponses({@ApiResponse(code = 200, message = "계획 수정 시도 성공"),
        @ApiResponse(code = 500, message = "서버 에러")})
        @PutMapping("/{planArticleId}")
        public ResponseEntity<Boolean> updatePlan(@PathVariable String planArticleId, @RequestParam String plan) {
            log.debug("updatePlan call");
            PlanArticleDto planArticleDto=new PlanArticleDto();
            planArticleDto.setPlanArticleId(planArticleId);
            planArticleDto.setPlan(plan);
            try {
                planArticleService.updatePlan(planArticleDto);
                return ResponseEntity.ok().body(true);
            } catch (SQLException e) {
                log.debug(e.toString());
                return ResponseEntity.internalServerError().build();
            }
        }
}
