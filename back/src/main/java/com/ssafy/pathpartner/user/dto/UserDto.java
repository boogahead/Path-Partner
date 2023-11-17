package com.ssafy.pathpartner.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
public class UserDto {

  @ApiModelProperty(hidden = true)
  private String uuid;

  @ApiModelProperty(value = "아이디")
  private String id;

  @ApiModelProperty(value = "이름")
  private String nickname;

  @ApiModelProperty(value = "비밀번호")
  private String password;

  @ApiModelProperty(hidden = true)
  private String joinDate;

  @ApiModelProperty(value = "역할")
  private int userType = 1;

  @ApiModelProperty(value = "이메일")
  private String email;


	/*@ApiModelProperty(value="프로필 사진")
	private byte[] profileImg;
	public UserDto() {
		// Load the default picture from the resources folder and set it as the default profileImg
		try {
			ClassPathResource resource = new ClassPathResource("defaultPicture.jpg");
			this.profileImg = StreamUtils.copyToByteArray(resource.getInputStream());
		} catch (IOException e) {
			// Handle the exception if the file cannot be loaded
			e.printStackTrace();
		}
	}*/

}
