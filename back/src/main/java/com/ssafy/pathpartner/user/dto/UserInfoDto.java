package com.ssafy.pathpartner.user.dto;

import com.ssafy.pathpartner.friend.dto.FriendInfoDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

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

  public static UserInfoDto fromFriendInfoDto(FriendInfoDto friendInfoDto) {
    return UserInfoDto
        .builder()
        .uuid(friendInfoDto.getUuid())
        .id(friendInfoDto.getId())
        .nickname(friendInfoDto.getNickname())
        .build();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserInfoDto that = (UserInfoDto) o;
    return Objects.equals(uuid, that.uuid) &&
            Objects.equals(id, that.id) &&
            Objects.equals(nickname, that.nickname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, id, nickname);
  }
}
