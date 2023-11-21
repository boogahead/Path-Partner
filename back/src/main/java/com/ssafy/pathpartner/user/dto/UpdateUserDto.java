package com.ssafy.pathpartner.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
  private String uuid;
  private String password;
  private String nickname;
  private MultipartFile profileImg;
  @ApiModelProperty(value = "프로필 사진")
  private byte[] profileImgSerialized;
}
