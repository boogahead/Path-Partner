package com.ssafy.pathpartner.planarticle.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.pathpartner.planarticle.exception.PlanArticleNotFoundException;
import com.ssafy.pathpartner.planarticle.exception.UnauthoriedPlanRequestException;
import java.sql.SQLException;
import java.util.List;
import com.ssafy.pathpartner.planarticle.dto.PlanArticleDto;

public interface PlanArticleService {

  String createPlanArticle(PlanArticleDto planArticleDto)
      throws SQLException, UnauthoriedPlanRequestException, JsonProcessingException;

  boolean deletePlanArticle(String planArticleId, String uuid)
          throws SQLException, UnauthoriedPlanRequestException, PlanArticleNotFoundException, JsonProcessingException;

  boolean updatePlanArticle(PlanArticleDto planArticleDto, String uuid)
      throws SQLException, UnauthoriedPlanRequestException, JsonProcessingException;

  PlanArticleDto searchPlanArticle(String plansArticleId) throws SQLException, PlanArticleNotFoundException;

  List<PlanArticleDto> searchAllPlanArticle(String uuid) throws SQLException;


}
