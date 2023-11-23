package com.ssafy.pathpartner.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendInfoDto {
  private String uuid;
  private String id;
  private String email;
  private String nickname;
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FriendInfoDto that = (FriendInfoDto) o;
    return Objects.equals(uuid, that.uuid) &&
            Objects.equals(id, that.id) &&
            Objects.equals(email, that.email) &&
            Objects.equals(nickname, that.nickname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, id, email, nickname);
  }
}
