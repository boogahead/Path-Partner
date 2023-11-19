package com.ssafy.pathpartner.reviewarticle.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.ssafy.pathpartner.reviewarticle.dto.ReviewArticleDto;
import com.ssafy.pathpartner.reviewarticle.service.ReviewArticleService;

@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.POST}, maxAge = 6000)
@RestController
@RequestMapping("/reviewarticle")
@Api("후기게시판 컨트롤러  API V1")
public class ReviewArticleController {

  private static final Logger logger = LoggerFactory.getLogger(ReviewArticleController.class);
  private ReviewArticleService reviewArticleService;

  public ReviewArticleController(ReviewArticleService reviewArticleService) {
    super();
    this.reviewArticleService = reviewArticleService;
  }

  @ApiOperation(value = "후기게시판 글작성", notes = "새로운 후기글 정보를 입력한다.")
  @PostMapping
  public ResponseEntity<?> writeArticle(
      @RequestBody @ApiParam(value = "후기글 정보.", required = true) ReviewArticleDto reviewArticleDto) {
    logger.info("writeArticle reviewArticleDto - {}", reviewArticleDto);
    try {
      reviewArticleService.writeArticle(reviewArticleDto);
      return new ResponseEntity<String>("success", HttpStatus.OK);
    } catch (Exception e) {
      logger.error("후기게시판 글작성 실패", e);
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ApiOperation(value = "후기게시판 글수정", notes = "후기글 정보를 수정한다.")
  @PutMapping
  public ResponseEntity<?> updateArticle(
      @RequestBody @ApiParam(value = "후기글 정보.", required = true) ReviewArticleDto reviewArticleDto) {
    logger.info("updateArticle reviewArticleDto - {}", reviewArticleDto);
    try {
      reviewArticleService.modifyArticle(reviewArticleDto);
      return new ResponseEntity<String>("success", HttpStatus.OK);
    } catch (Exception e) {
      logger.error("후기게시판 글수정 실패", e);
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ApiOperation(value = "후기게시판 글삭제", notes = "후기글 정보를 삭제한다.")
  @DeleteMapping("/{reviewArticleNo}")
  public ResponseEntity<?> deleteArticle(
      @PathVariable("reviewArticleNo") @ApiParam(value = "후기글 번호.", required = true) int reviewArticleNo) {
    logger.info("deleteArticle reviewArticleNo - {}", reviewArticleNo);
    try {
      reviewArticleService.deleteArticle(String.valueOf(reviewArticleNo));
      return new ResponseEntity<String>("success", HttpStatus.OK);
    } catch (Exception e) {
      logger.error("후기게시판 글삭제 실패", e);
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ApiOperation(value = "후기게시판 글목록", notes = "후기글 목록을 조회한다.")
  @GetMapping
  public ResponseEntity<?> listArticle() {
    logger.info("listArticle - 호출");
    try {
      List<ReviewArticleDto> list = reviewArticleService.listArticle();
      if (list.isEmpty()) {
        return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<List<ReviewArticleDto>>(list, HttpStatus.OK);
      }
    } catch (Exception e) {
      logger.error("후기게시판 글목록 조회 실패", e);
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ApiOperation(value = "후기게시판 글상세", notes = "후기글 상세정보를 조회한다.")
  @GetMapping("/{reviewArticleNo}")
  public ResponseEntity<?> getArticle(
      @PathVariable("reviewArticleNo") @ApiParam(value = "후기글 번호.", required = true) int reviewArticleNo) {
    logger.info("getArticle reviewArticleNo - {}", reviewArticleNo);
    try {
      ReviewArticleDto reviewArticleDto = reviewArticleService.getArticle(
          String.valueOf(reviewArticleNo));
      if (reviewArticleDto == null) {
        return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<ReviewArticleDto>(reviewArticleDto, HttpStatus.OK);
      }
    } catch (Exception e) {
      logger.error("후기게시판 글상세 조회 실패", e);
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
