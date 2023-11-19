package com.ssafy.pathpartner.planarticle.repository;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.pathpartner.planarticle.dto.PlanArticleDto;
public interface PlanArticleDao {
    void writePlan(PlanArticleDto planArticleDto) throws SQLException;
    void deletePlan(Map<String,Object> params) throws SQLException;

    List<PlanArticleDto> getPlanList(String groupId) throws SQLException;

    void updatePlan(PlanArticleDto planArticleDto) throws SQLException;

    String checkWriter(String planArticleId) throws SQLException;

    int memberCheck(Map<String,Object> params) throws SQLException;

    int fetchRole(Map<String,Object> params) throws SQLException;

    String getGroupId(String planArticleId) throws SQLException;
}
