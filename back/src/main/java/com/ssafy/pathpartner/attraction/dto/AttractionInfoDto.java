package com.ssafy.pathpartner.attraction.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "AttractionInfoDto (관광지 정보)", description = "타입, 이름, 시도코드, 시군구 코드를 가지는 Class")
public class AttractionInfoDto {

  @ApiModelProperty(hidden = true)
  private int contentId;

  @ApiModelProperty(value = "관광지 타입")
  private int contentTypeId;

  @ApiModelProperty(value = "관광지 이름")
  private String title;

  @ApiModelProperty(hidden = true)
  private String addr1;

  @ApiModelProperty(hidden = true)
  private String addr2;

  @ApiModelProperty(hidden = true)
  private String zipcode;

  @ApiModelProperty(hidden = true)
  private String tel;

  @ApiModelProperty(hidden = true)
  private String firstImage;

  @ApiModelProperty(hidden = true)
  private String firstImage2;

  @ApiModelProperty(hidden = true)
  private int readcount;

  @ApiModelProperty(value = "시도코드")
  private int sidoCode;

  @ApiModelProperty(value = "시군구코드")
  private int gugunCode;

  @ApiModelProperty(hidden = true)
  private double latitude;

  @ApiModelProperty(hidden = true)
  private double longitude;

  @ApiModelProperty(hidden = true)
  private String mlevel;

  @ApiModelProperty(hidden = true)
  private int likes;

}
