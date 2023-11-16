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
@ApiModel(value = "GunguDto (군, 구 정보)", description = "군, 구 코드와 이름, 시도 코드를 가지는 Class")
public class GugunDto {

	private int gugunCode;
	private String gugunName;
	private int sidoCode;
}
