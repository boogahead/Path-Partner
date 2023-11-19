package com.ssafy.pathpartner.noticearticle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.ssafy.pathpartner.noticearticle.dto.notice_articleDto;
import com.ssafy.pathpartner.noticearticle.service.NoticeArticleService;
//http://localhost/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/notice")
@Api("공지게시판 컨트롤러  API V1")
public class NoticeArticleController {

    private static final Logger logger = LoggerFactory.getLogger(NoticeArticleController.class);
//	private static final String SUCCESS = "success";
//	private static final String FAIL = "fail";

    private NoticeArticleService NoticeArticleService;

    public NoticeArticleController(NoticeArticleService NoticeArticleService) {
        super();
        this.NoticeArticleService = NoticeArticleService;
    }

    @ApiOperation(value = "공지 게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
            @ApiResponse(code = 500, message = "서버에러!!") })
    @GetMapping
    public ResponseEntity<?>list_Notice_Article(
            @RequestParam(required = false) @ApiParam(value = "페이지 번호") String page,
            @RequestParam(required = false) @ApiParam(value = "페이지 당 게시글 수") String size) {
        logger.debug("listArticle - 호출");
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            result = NoticeArticleService.getnotice_articleList();
            result.put("status", true);
        } catch (Exception e) {
            logger.error("공지글 목록 조회 실패", e);
            result.put("status", false);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result, status);
    }
    @ApiOperation(value = "공지글 글 보기", notes = "글 번호에 해당하는 게시글의 정보 반환.")
    @GetMapping("/{noticearticle_id}")
    public ResponseEntity<?>get_Notice_Article(
            @PathVariable @ApiParam(value = "공지글 번호", required = true) String noticearticle_id) {
        logger.debug("getArticle - 호출");
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            result.put("notice_article", NoticeArticleService.getnotice_article(noticearticle_id));
            result.put("status", true);
        } catch (Exception e) {
            logger.error("공지글 조회 실패", e);
            result.put("status", false);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result, status);
    }


    @ApiOperation(value = "공지글 작성", notes = "새로운 게시글 정보를 입력한다.")
    @PostMapping
    public ResponseEntity<?> write_Notice_Article(
            @RequestBody @ApiParam(value = "게시글 정보.", required = true) notice_articleDto notice_articleDto) {
        logger.debug("writeArticle - 호출");
        if (notice_articleDto.getTitle() == null || notice_articleDto.getContent() == null)
            return new ResponseEntity<>("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        try {
            NoticeArticleService.createnotice_article(notice_articleDto);
        } catch (Exception e) {
            logger.error("공지글 작성 실패", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "공지글 수정", notes = "공지글 정보를 수정한다.")
    @PutMapping
    public ResponseEntity<?> update_Notice_Article(
            @RequestBody @ApiParam(value = "공지글 정보.", required = true) notice_articleDto notice_articleDto) {
        logger.debug("updateArticle - 호출");
        if (notice_articleDto.getTitle() == null || notice_articleDto.getContent() == null)
            return new ResponseEntity<>("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        try {
            NoticeArticleService.editnotice_article(notice_articleDto);
        } catch (Exception e) {
            logger.error("공지글 수정 실패", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "공지글 삭제", notes = "공지글 번호에 해당하는 공지글을 삭제한다.")
    @DeleteMapping("/{noticearticle_id}")
    public ResponseEntity<?> delete_Notice_Article(
            @PathVariable @ApiParam(value = "공지글 번호", required = true) String noticearticle_id) {
        logger.debug("deleteArticle - 호출");
        try {
            NoticeArticleService.deletenotice_article(noticearticle_id);
        } catch (Exception e) {
            logger.error("공지글 삭제 실패", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
