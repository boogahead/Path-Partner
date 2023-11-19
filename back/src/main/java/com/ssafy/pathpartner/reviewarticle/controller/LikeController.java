package com.ssafy.pathpartner.reviewarticle.controller;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.ssafy.pathpartner.reviewarticle.dto.LikeDto;
import com.ssafy.pathpartner.reviewarticle.service.LikeService;
@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/like")
public class LikeController {
    private static final Logger logger = LoggerFactory.getLogger(LikeController.class);
    private LikeService likeService;
    public LikeController(LikeService likeService) {
        super();
        this.likeService = likeService;
    }
    @ApiOperation(value = "좋아요", notes = "좋아요를 누른다.")
    @PostMapping("/{reviewArticleId}")
    public ResponseEntity<?> like(
            @PathVariable("reviewArticleId") @ApiParam(value = "후기글 아이디.", required = true) String reviewArticleId) {
        logger.info("like reviewArticleId - {}", reviewArticleId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        HttpStatus status = null;
        try {
            likeService.like(reviewArticleId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(headers, status);
    }
    @ApiOperation(value = "좋아요 취소", notes = "좋아요를 취소한다.")
    @DeleteMapping("/{reviewArticleId}")
    public ResponseEntity<?> unlike(
            @PathVariable("reviewArticleId") @ApiParam(value = "후기글 아이디.", required = true) String reviewArticleId) {
        logger.info("unlike reviewArticleId - {}", reviewArticleId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        HttpStatus status = null;
        try {
            likeService.unlike(reviewArticleId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(headers, status);
    }
    @ApiOperation(value = "좋아요 수", notes = "좋아요 수를 센다.")
    @GetMapping("/count/{reviewArticleId}")
    public ResponseEntity<?> countLike(
            @PathVariable("reviewArticleId") @ApiParam(value = "후기글 아이디.", required = true) String reviewArticleId) {
        logger.info("countLike reviewArticleId - {}", reviewArticleId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        HttpStatus status = null;
        try {
            likeService.countLike(reviewArticleId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(headers, status);
    }
    @ApiOperation(value = "좋아요 한 유저들", notes = "좋아요 한 유저들을 보여준다.")
    @GetMapping("/users/{reviewArticleId}")
    public ResponseEntity<?> likedUsers(
            @PathVariable("reviewArticleId") @ApiParam(value = "후기글 아이디.", required = true) String reviewArticleId) {
        logger.info("likedUsers reviewArticleId - {}", reviewArticleId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        HttpStatus status = null;
        List<LikeDto> list = null;
        try {
            likeService.likedUsers(reviewArticleId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(list, headers, status);
    }
}
