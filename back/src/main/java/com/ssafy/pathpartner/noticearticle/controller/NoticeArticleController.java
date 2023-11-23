package com.ssafy.pathpartner.noticearticle.controller;

import com.ssafy.pathpartner.noticearticle.dto.NoticeArticleDto;
import com.ssafy.pathpartner.noticearticle.exception.NoticeArticleNotFound;
import com.ssafy.pathpartner.user.dto.UserDto;
import java.sql.SQLException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.ssafy.pathpartner.noticearticle.service.NoticeArticleService;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.POST}, maxAge = 6000)
@RestController
@RequestMapping("/notice")
@Api(tags = {"공지글 컨트롤러 API"})
@PreAuthorize("hasRole('USER')")
public class NoticeArticleController {

  private final NoticeArticleService noticeArticleService;

  @Autowired
  public NoticeArticleController(NoticeArticleService noticeArticleService) {
    super();
    this.noticeArticleService = noticeArticleService;
  }

  @ApiOperation(value = "공지 게시판 글목록", notes = "모든 게시글의 정보를 가져옵니다.")
  @ApiResponses({@ApiResponse(code = 200, message = "가져오기 성공"),
      @ApiResponse(code = 500, message = "서버에러")})
  @GetMapping
  public ResponseEntity<List<NoticeArticleDto>> getAllNoticeArticle() {
    log.debug("getAllNoticeArticle call");

    try {
      return ResponseEntity.ok().body(noticeArticleService.searchAllNoticeArticle());
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "공지 게시판 글 보기", notes = "글 번호에 게시글의 정보를 가져옵니다.")
  @GetMapping("/{noticeArticleId}")
  public ResponseEntity<NoticeArticleDto> getNoticeArticle(
      @PathVariable String noticeArticleId) {
    log.debug("getNoticeArticle call");

    try {
      return ResponseEntity.ok().body(noticeArticleService.searchNoticeArticle(noticeArticleId));
    } catch (NoticeArticleNotFound e) {
      return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "공지글 작성", notes = "새로운 게시글 정보를 입력한다.")
  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Boolean> writeNoticeArticle(
      @RequestBody NoticeArticleDto noticeArticleDto, @ApiIgnore @AuthenticationPrincipal UserDto userDto) {
    log.debug("writeNoticeArticle call");

    if (noticeArticleDto.getTitle() == null || noticeArticleDto.getContent() == null) {
      return ResponseEntity.badRequest().build();
    }

    noticeArticleDto.setUuid(userDto.getUuid());

    try {
      return ResponseEntity.ok().body(noticeArticleService.createNoticeArticle(noticeArticleDto));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "공지글 수정", notes = "공지글을 수정합니다.")
  @PutMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Boolean> modifyNoticeArticle(
      @RequestBody NoticeArticleDto noticeArticleDto) {
    log.debug("modifyNoticeArticle call");

    if (noticeArticleDto.getTitle() == null || noticeArticleDto.getContent() == null) {
      return ResponseEntity.badRequest().build();
    }

    try {
      return ResponseEntity.ok().body(noticeArticleService.updateNoticeArticle(noticeArticleDto));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @ApiOperation(value = "공지글 삭제", notes = "공지글을 삭제합니다.")
  @DeleteMapping("/{noticeArticleId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Boolean> deleteNoticeArticle(
      @PathVariable String noticeArticleId) {
    log.debug("deleteNoticeArticle call");

    try {
      return ResponseEntity.ok().body(noticeArticleService.deleteNoticeArticle(noticeArticleId));
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

}
