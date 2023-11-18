package com.ssafy.pathpartner.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResetPasswordDto {
  private String id;
  private String email;
}
