package com.ssafy.pathpartner.travelgroup.controller;
import com.ssafy.pathpartner.travelgroup.dto.ChatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    ChatWebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/send/chat")
    public void sendChat(ChatDto chatDto){
        template.convertAndSend("/topic/chat", chatDto);
    }
}