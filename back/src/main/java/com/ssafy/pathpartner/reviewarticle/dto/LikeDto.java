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
@ApiModel(value = "LikeDto (댓글정보)", description = "좋아요")
public class LikeDto {
    @ApiModelProperty(value="후기글 아이디")
    private String reviewArticleId;

    @ApiModelProperty(value="좋아요 누른 사람")
    private String uuid;
}
