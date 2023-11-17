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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserDto (회원정보)", description = "id, pw, 이름을 가지는 Class")
public class UserDto implements UserDetails {

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

  public static UserDto fromSignUpDto(SignUpDto signUpDto) {
    return UserDto
        .builder()
        .id(signUpDto.getId())
        .nickname(signUpDto.getNickname())
        .password(signUpDto.getPassword())
        .email(signUpDto.getEmail())
        .build();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    switch (userType) {
      case 0:
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        break;
      case 1:
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        break;
      default:
    }

    return authorities;
  }

  // Spring Security에서 userName은 id를 의미함
  @Override
  public String getUsername() {
    return id;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


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
