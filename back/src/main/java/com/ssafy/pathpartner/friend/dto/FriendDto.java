package com.ssafy.pathpartner.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendDto {

  String friendFrom;
  String friendTo;
  Boolean accepted;

}
