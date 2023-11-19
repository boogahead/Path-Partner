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
@ApiModel(value = "TravelGroupDto (그룹정보)", description = "그룹 이름,속한 유저 등을 표현하는 클래스")
public class TravelGroupDto {

    @ApiModelProperty(hidden = true)
    private String groupId;
    @ApiModelProperty(value = "그룹 이름")
    private String groupName;
    @ApiModelProperty(hidden = true)
    private String creationDate;
    @ApiModelProperty(value="속한 유저의 uuid")
    private String uuid;
    @ApiModelProperty(value="그룹장 여부 - true = 그룹장 , false = 그룹원")
    private Boolean groupMaster;
}
