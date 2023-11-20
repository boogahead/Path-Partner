package com.ssafy.pathpartner.reviewarticle.controller;

import com.ssafy.pathpartner.reviewarticle.exception.ReviewArticleNotFoundException;
import com.ssafy.pathpartner.reviewarticle.exception.UnauthrizedReviewArticleRequestException;
import com.ssafy.pathpartner.user.dto.UserDto;
import java.sql.SQLException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.POST}, maxAge = 6000)
@RestController
@RequestMapping("/review")
@Api(tags = {"후기게시판 컨트롤러  API V1"})
@PreAuthorize("hasRole('USER')")
public class ReviewArticleController {

  private final ReviewArticleService reviewArticleService;

  public ReviewArticleController(ReviewArticleService reviewArticleService) {
    super();
    this.reviewArticleService = reviewArticleService;
  }

  @ApiOperation(value = "후기게시판 글작성", notes = "새로운 후기글 정보를 입력한다.")
  @PostMapping
  public ResponseEntity<Boolean> writeArticle(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @RequestBody ReviewArticleDto reviewArticleDto) {
    log.debug("writeArticle call");

    reviewArticleDto.setWriterUuid(userDto.getUuid());
    try {
      return ResponseEntity.ok().body(reviewArticleService.createReviewArticle(reviewArticleDto));
    } catch (SQLException e) {
      log.error(e.toString());
      return ResponseEntity.badRequest().build();
    }
  }

  @ApiOperation(value = "후기게시판 글수정", notes = "후기글 정보를 수정한다.")
  @PutMapping
  @PreAuthorize("#userDto.uuid == #reviewArticleDto.writerUuid")
  public ResponseEntity<Boolean> updateArticle(@ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @RequestBody ReviewArticleDto reviewArticleDto) {
    log.debug("updateArticle call");

    try {
      return ResponseEntity.ok().body(reviewArticleService.updateReviewArticle(reviewArticleDto));
    } catch (Exception e) {
      log.error(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "후기게시판 글삭제", notes = "후기글 정보를 삭제한다.")
  @DeleteMapping("/{reviewArticleId}")
  public ResponseEntity<Boolean> deleteArticle(@ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @PathVariable String reviewArticleId) {
    log.debug("deleteArticle call");

    try {
      return ResponseEntity.ok()
          .body(reviewArticleService.deleteReviewArticle(reviewArticleId, userDto));
    } catch (ReviewArticleNotFoundException e) {
      log.error(e.toString());
      return ResponseEntity.badRequest().build();
    } catch (UnauthrizedReviewArticleRequestException e) {
      log.error(e.toString());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (SQLException e) {
      log.error(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "후기게시판 글목록", notes = "후기글 목록을 조회한다.")
  @GetMapping
  public ResponseEntity<List<ReviewArticleDto>> getReviewArticleList() {
    log.debug("getReviewArticleList call");
    try {
      return ResponseEntity.ok().body(reviewArticleService.searchAllReviewArticle());
    } catch (SQLException e) {
      log.error(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "후기게시판 글상세", notes = "후기글 상세정보를 조회한다.")
  @GetMapping("/{reviewArticleId}")
  public ResponseEntity<ReviewArticleDto> getReviewArticle(
      @PathVariable String reviewArticleId) {
    log.debug("getReviewArticle ");

    try {
      return ResponseEntity.ok().body(reviewArticleService.searchReviewArticle(reviewArticleId));
    } catch (ReviewArticleNotFoundException e) {
      log.error(e.toString());
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      log.error(e.toString());
      return ResponseEntity.internalServerError().build();
    }
  }
}
