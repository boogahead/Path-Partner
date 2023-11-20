package com.ssafy.pathpartner.travelgroup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupMemberDto {

  private String groupId;
  private String groupName;
  private String uuid;
  private String nickname;
  private String email;
  private boolean groupMaster;

}
