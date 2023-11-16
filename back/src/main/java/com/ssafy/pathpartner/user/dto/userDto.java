package com.ssafy.pathpartner.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MemberDto (회원정보)", description = "id, pw, 이름을 가지는 Class")
public class userDto {

	@ApiModelProperty(hidden = true)
	private String uid;

	@ApiModelProperty(value = "아이디")
	private String userId;

	@ApiModelProperty(value = "이름")
	private String userName;

	@ApiModelProperty(value = "비밀번호")
	private String userPass;

	@ApiModelProperty(hidden = true)
	private String joinDate;

	@ApiModelProperty(value = "역할")
	private int userType=1;

	@ApiModelProperty(value="이메일")
	private String email;

}
