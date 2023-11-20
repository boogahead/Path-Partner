package com.ssafy.pathpartner.reviewarticle.controller;

import com.ssafy.pathpartner.reviewarticle.exception.UnauthorizedCommentRequestException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.ssafy.pathpartner.reviewarticle.dto.CommentDto;
import com.ssafy.pathpartner.reviewarticle.service.CommentService;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.POST}, maxAge = 6000)
@RestController
@RequestMapping("/comment")
@Slf4j
@PreAuthorize("hasRole('USER')")
public class CommentController {

  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    super();
    this.commentService = commentService;
  }

  @ApiOperation(value = "댓글 작성", notes = "새로운 댓글 정보를 입력한다.")
  @PostMapping
  public ResponseEntity<Boolean> writeComment(@RequestBody CommentDto commentDto) {

    log.debug("writeComment call");

    try {
      return ResponseEntity.ok().body(commentService.createComment(commentDto));
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "댓글 삭제", notes = "댓글 번호에 해당하는 댓글을 삭제한다.")
  @DeleteMapping("/{commentId}")
  public ResponseEntity<Boolean> deleteComment(@ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @PathVariable String commentId) {

    log.debug("deleteComment");

    try {
      return ResponseEntity.ok().body(commentService.deleteComment(commentId, userDto));
    } catch (UnauthorizedCommentRequestException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "댓글 목록", notes = "댓글 목록을 조회한다.")
  @GetMapping("/{reviewArticleId}")
  public ResponseEntity<List<CommentDto>> getCommentList(@PathVariable String reviewArticleId) {

    log.debug("getCommentList");

    try {
      return ResponseEntity.ok().body(commentService.searchAllComment(reviewArticleId));
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "댓글 수정", notes = "댓글 번호에 해당하는 댓글을 수정한다.")
  @PutMapping
  @PreAuthorize("hasRole('ADIMN') || #commentDto.uuid == userDto.uuid")
  public ResponseEntity<Boolean> editComment(@ApiIgnore @AuthenticationPrincipal UserDto userDto,
      @RequestBody CommentDto commentDto) {

    log.debug("editComment");

    try {
      return ResponseEntity.ok().body(commentService.updateComment(commentDto));
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
