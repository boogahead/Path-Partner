package com.ssafy.enjoytrip.member.dto;

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
@ApiModel(value = "MemberDto (회원정보)", description = "id, pw, 이름을 가지는 Class")
public class MemberDto {

	@ApiModelProperty(hidden = true)
	private int uid;

	@ApiModelProperty(value = "아이디")
	private String userId;

	@ApiModelProperty(value = "이름")
	private String userName;

	@ApiModelProperty(value = "비밀번호")
	private String userPass;

	@ApiModelProperty(hidden = true)
	private String joinDate;

	@ApiModelProperty(value = "역할")
	private String roles;

}
