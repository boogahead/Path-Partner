package com.ssafy.pathpartner.reviewarticle.controller;

import com.ssafy.pathpartner.reviewarticle.dto.ReviewArticleDto;
import com.ssafy.pathpartner.user.dto.UserDto;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.ssafy.pathpartner.reviewarticle.dto.LikeDto;
import com.ssafy.pathpartner.reviewarticle.service.LikeService;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.POST}, maxAge = 6000)
@RestController
@RequestMapping("/like")
@Slf4j
public class LikeController {

  private final LikeService likeService;

  public LikeController(LikeService likeService) {
    super();
    this.likeService = likeService;
  }

  @ApiOperation(value = "좋아요", notes = "좋아요를 등록합니다")
  @GetMapping("/{reviewArticleId}")
  public ResponseEntity<Boolean> like(@ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @PathVariable String reviewArticleId) {

    log.debug("like call");

    LikeDto likeDto = LikeDto.builder()
        .reviewArticleId(reviewArticleId)
        .uuid(userDto.getUuid())
        .build();

    try {
      return ResponseEntity.ok().body(likeService.like(likeDto));
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "좋아요 취소", notes = "좋아요를 취소한다.")
  @DeleteMapping("/{reviewArticleId}")
  public ResponseEntity<Boolean> unlike(@ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @PathVariable String reviewArticleId) {

    log.debug("unlike call");

    LikeDto likeDto = LikeDto.builder()
        .reviewArticleId(reviewArticleId)
        .uuid(userDto.getUuid())
        .build();

    try {
      return ResponseEntity.ok().body(likeService.unlike(likeDto));
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "좋아요 수 불러오기", notes = "좋아요 수를 센다.")
  @GetMapping("/count/{reviewArticleId}")
  public ResponseEntity<Integer> countLike(@PathVariable String reviewArticleId) {

    log.debug("countLike call");

    try {
      return ResponseEntity.ok().body(likeService.countLike(reviewArticleId));
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "좋아요 한 유저들", notes = "좋아요 한 유저들을 보여준다.")
  @GetMapping("/user/{reviewArticleId}")
  public ResponseEntity<List<String>> getLikedUserList(
      @PathVariable("reviewArticleId") String reviewArticleId) {

    log.debug("getLikedUserList");
    try {
      return ResponseEntity.ok().body(likeService.searchAllLikedUsers(reviewArticleId));
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "내가 좋아요한 글 가져오기", notes = "좋아요한 글을 가져온다.")
  @GetMapping("/like/review-article")
  public ResponseEntity<List<ReviewArticleDto>> getMyLikedReviewArticleList(
      @ApiIgnore @AuthenticationPrincipal UserDto userDto) {

    log.debug("getMyLikedReviewArticleList");
    try {
      return ResponseEntity.ok().body(likeService.searchAllMyLikedReviewArticle(userDto.getUuid()));
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
