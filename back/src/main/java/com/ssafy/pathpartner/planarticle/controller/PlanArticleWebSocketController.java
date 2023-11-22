package com.ssafy.pathpartner.planarticle.controller;

import com.ssafy.pathpartner.planarticle.dto.PlanArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PlanArticleWebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    PlanArticleWebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/send/planarticle")
    public void sendPlanArticle(PlanArticleDto planArticleDto){
        template.convertAndSend("/topic/planarticle", planArticleDto);
    }
}