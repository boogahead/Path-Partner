package com.ssafy.pathpartner.planarticle.controller;

import com.ssafy.pathpartner.planarticle.dto.PlanArticleDto;

public class PlanArticleMessage {
    private String operation;
    private PlanArticleDto planArticleDto;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public PlanArticleDto getPlanArticleDto() {
        return planArticleDto;
    }

    public void setPlanArticleDto(PlanArticleDto planArticleDto) {
        this.planArticleDto = planArticleDto;
    }

    // getters and setters
}