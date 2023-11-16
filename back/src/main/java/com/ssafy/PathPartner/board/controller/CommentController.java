package com.ssafy.PathPartner.board.controller;
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

import com.ssafy.PathPartner.board.dto.CommentDto;
import com.ssafy.PathPartner.board.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/comment")
@Api("댓글 컨트롤러  API V1")
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
            @PathVariable("commentNo") @ApiParam(value = "댓글 번호.", required = true) int commentNo) {
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
    @ApiOperation(value = "댓글 리스트", notes = "댓글 리스트를 반환한다.")
    @GetMapping("/{articleNo}")
    public ResponseEntity<?> listComment(
            @PathVariable("articleNo") @ApiParam(value = "게시글 번호.", required = true) int articleNo,
            @RequestParam(required = false, defaultValue = "1") @ApiParam(value = "페이지 번호.", required = false) int page,
            @RequestParam(required = false, defaultValue = "10") @ApiParam(value = "페이지당 댓글 수.", required = false) int size) {
        logger.info("listComment - 리스트 반환");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        HttpStatus status = null;
        try {
            List<CommentDto> result = commentService.listComment(articleNo);
            status = HttpStatus.OK;
            return new ResponseEntity<>(result, headers, status);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(headers, status);
        }
    }
}
