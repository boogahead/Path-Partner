package com.ssafy.pathpartner.travelgroup.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto implements Serializable {
    private long chatId;
    private String groupId;
    private String uuid;
    private String content;
    private String creationDate;
}
