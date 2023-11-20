package com.ssafy.pathpartner.area.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "SidoDto (시, 도 정보)", description = "시, 도 코드와 이름을 가지는 Class")
public class SidoDto {

  private int sidoCode;
  private String sidoName;

}
