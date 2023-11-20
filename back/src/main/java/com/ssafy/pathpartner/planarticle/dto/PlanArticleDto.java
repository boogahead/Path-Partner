package com.ssafy.pathpartner.planarticle.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "GroupInvite (계획정보)", description = "계획 정보들을 표현하는 클래스")
public class PlanArticleDto {

  @ApiModelProperty(hidden = true)
  private String planArticleId;
  @ApiModelProperty(value = "계획")
  private String plan;
  @ApiModelProperty(value = "계획이 적혀있을 그룹 아이디")
  private String groupId;
  @ApiModelProperty(value = "생성 날짜")
  private String creationDate;
  @ApiModelProperty(value = "작성자 uuid")
  private String uuid;
  private String imgSrc;
  private String planTitle;
}
