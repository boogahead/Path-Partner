package com.ssafy.pathpartner.review_article.dto;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "CommentDto (댓글정보)", description = "댓글들임")
public class CommentDto {
    @ApiModelProperty(hidden= true)
    private String commentId;

    @ApiModelProperty(value="후기글 아이디")
    private String reviewArticleId;

    @ApiModelProperty(value="댓글 작성자")
    private String uuid;

    @ApiModelProperty(hidden=true)
    private String creationDate;

    @ApiModelProperty(value="댓글 내용")
    private String content;

}
