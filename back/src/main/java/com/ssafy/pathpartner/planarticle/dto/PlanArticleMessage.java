package com.ssafy.pathpartner.planarticle.dto;
/*
object that will be saved to redis so that clients will know what to do with the given object
 */
public class PlanArticleMessage {
    private String operation;//create, update, delete
    private PlanArticleDto planArticleDto;//the object clients need to process

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
}
