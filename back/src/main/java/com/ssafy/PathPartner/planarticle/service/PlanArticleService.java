package com.ssafy.pathpartner.planarticle.service;
import java.sql.SQLException;
import java.util.List;
import com.ssafy.pathpartner.planarticle.dto.PlanArticleDto;
public interface PlanArticleService {
    void writePlan(PlanArticleDto planArticleDto) throws SQLException;
    void deletePlan(String planArticleId) throws SQLException;
    List<PlanArticleDto> getPlanList(String groupId) throws SQLException;
    void updatePlan(PlanArticleDto planArticleDto) throws SQLException;


}
