package com.ssafy.pathpartner.notice_article.dto;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "notice_articleDto : 공지글 정보", description = "공지글의 상세 정보를 나타낸다.")
public class notice_articleDto {
    @ApiModelProperty(value = "공지글번호")
    private String noticearticle_id;
    @ApiModelProperty(value = "글 제목")
    private String title;
    @ApiModelProperty(value = "글 내용")
    private String content;
    @ApiModelProperty(value = "글 작성일")
    private LocalDateTime creation_date;
    @ApiModelProperty(value = "작성 유저")
    private String uuid;

    private List<notice_articleDto>noticelist;
    public List<notice_articleDto> getnoticelist(){
        return noticelist;
    }

    public String getnoticearticle_id() {
        return noticearticle_id;
    }

    public void setnoticearticle_id(String noticearticle_id) {
        this.noticearticle_id = noticearticle_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }

    public String getUuid() {
        return uuid;
    }

}
