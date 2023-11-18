package com.ssafy.pathpartner.reviewarticle.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
