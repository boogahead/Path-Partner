package com.ssafy.pathpartner.user.dto;

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
public class UserInfoDto {

  private String uuid;

  private String id;

  private String nickname;

  private String email;

  private String joinDate;

  public static UserInfoDto fromUserDto(UserDto userDto) {
    return UserInfoDto
        .builder()
        .uuid(userDto.getUuid())
        .id(userDto.getId())
        .nickname(userDto.getNickname())
        .email(userDto.getEmail())
        .joinDate(userDto.getJoinDate())
        .build();
  }
}
