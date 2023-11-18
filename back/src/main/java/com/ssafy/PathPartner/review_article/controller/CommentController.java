package com.ssafy.pathpartner.review_article.controller;
import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.ssafy.pathpartner.review_article.dto.CommentDto;
import com.ssafy.pathpartner.review_article.service.CommentService;
@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/comment")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    private CommentService commentService;
    public CommentController(CommentService commentService) {
        super();
        this.commentService = commentService;
    }
    @ApiOperation(value = "댓글 작성", notes = "새로운 댓글 정보를 입력한다.")
    @PostMapping
    public ResponseEntity<?> writeComment(
            @RequestBody @ApiParam(value = "댓글 정보.", required = true) CommentDto commentDto) {
        logger.info("writeComment commentDto - {}", commentDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        HttpStatus status = null;
        try {
            commentService.writeComment(commentDto);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(headers, status);
    }
    @ApiOperation(value = "댓글 삭제", notes = "댓글 번호에 해당하는 댓글을 삭제한다.")
    @DeleteMapping("/{commentNo}")
    public ResponseEntity<?> deleteComment(
            @PathVariable("commentNo") @ApiParam(value = "댓글 번호.", required = true) String commentNo) {
        logger.info("deleteComment - {}", commentNo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        HttpStatus status = null;
        try {
            commentService.deleteComment(commentNo);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(headers, status);
    }
    @ApiOperation(value = "댓글 목록", notes = "댓글 목록을 조회한다.")
    @GetMapping("/{reviewArticleId}")
    public ResponseEntity<?> listComment(
            @PathVariable("reviewArticleId") @ApiParam(value = "후기글 아이디.", required = true) String reviewArticleId) {
        logger.info("listComment - {}", reviewArticleId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        HttpStatus status = null;
        List<CommentDto> list = null;
        try {
            list = commentService.listComment(reviewArticleId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(list, headers, status);
    }
    @ApiOperation(value = "댓글 수정", notes = "댓글 번호에 해당하는 댓글을 수정한다.")
    @PostMapping("/{commentNo}")
    public ResponseEntity<?> editComment(
            @PathVariable("commentNo") @ApiParam(value = "댓글 번호.", required = true) String commentNo,
            @RequestParam("content") @ApiParam(value = "수정할 댓글 내용.", required = true) String content) {
        logger.info("editComment - {}", commentNo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        HttpStatus status = null;
        try {
            commentService.editComment(commentNo, content);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(headers, status);
    }
}
