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
}
