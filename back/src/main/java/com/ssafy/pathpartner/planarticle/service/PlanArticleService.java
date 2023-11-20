package com.ssafy.pathpartner.planarticle.service;

import com.ssafy.pathpartner.planarticle.exception.PlanArticleNotFoundException;
import com.ssafy.pathpartner.planarticle.exception.UnauthoriedPlanRequestException;
import java.sql.SQLException;
import java.util.List;
import com.ssafy.pathpartner.planarticle.dto.PlanArticleDto;

public interface PlanArticleService {

  boolean createPlanArticle(PlanArticleDto planArticleDto)
      throws SQLException, UnauthoriedPlanRequestException;

  boolean deletePlanArticle(String planArticleId, String uuid)
      throws SQLException, UnauthoriedPlanRequestException, PlanArticleNotFoundException;

  boolean updatePlanArticle(PlanArticleDto planArticleDto, String uuid)
      throws SQLException, UnauthoriedPlanRequestException;

  PlanArticleDto searchPlanArticle(String plansArticleId) throws SQLException, PlanArticleNotFoundException;

  List<PlanArticleDto> searchAllPlanArticle(String uuid) throws SQLException;


}
