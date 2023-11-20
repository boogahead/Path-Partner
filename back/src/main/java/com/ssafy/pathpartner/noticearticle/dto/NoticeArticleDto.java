package com.ssafy.pathpartner.noticearticle.dto;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value = "NoticeArticleDto : 공지글 정보", description = "공지글의 상세 정보를 나타낸다.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeArticleDto {

  @ApiModelProperty(value = "공지글번호")
  private String noticeArticleId;
  @ApiModelProperty(value = "글 제목")
  private String title;
  @ApiModelProperty(value = "글 내용")
  private String content;
  @ApiModelProperty(value = "글 작성일")
  private LocalDateTime creationDate;
  @ApiModelProperty(value = "작성 유저")
  private String uuid;

}
