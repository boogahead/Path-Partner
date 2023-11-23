package com.ssafy.pathpartner.travelgroup.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "GroupInvite (그룹초대정보)", description = "그룹 초대 정보들을 표현하는 클래스")
public class GroupInviteDto {

    @ApiModelProperty(value="초대하는 그룹 아이디")
    private String groupId;
    @ApiModelProperty(value="초대를 받게 될 유저의 uuid")
    private String inviteTo;

    private String groupName;
}
