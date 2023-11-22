package com.ssafy.pathpartner.reviewarticle.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "ReviewArticleDto : 후기글정보", description = "후기글의 상세 정보를 나타낸다.")
public class ReviewArticleDto {

  @ApiModelProperty(hidden = true)
  private String reviewArticleId;
  @ApiModelProperty(value = "제목")
  private String title;
  @ApiModelProperty(value = "내용")
  private String content;
  @ApiModelProperty(value = "작성자")
  private String writerUuid;
  @ApiModelProperty(value = "계획글 id")
  private String planArticleId;
}
