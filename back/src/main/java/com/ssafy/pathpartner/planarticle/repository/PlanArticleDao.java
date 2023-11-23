package com.ssafy.pathpartner.planarticle.repository;
import com.ssafy.pathpartner.planarticle.exception.PlanArticleNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.pathpartner.planarticle.dto.PlanArticleDto;
public interface PlanArticleDao {
    int insertPlanArticle(PlanArticleDto planArticleDto) throws SQLException;
    int deletePlanArticle(String planArticleId) throws SQLException;

    int updatePlanArticle(PlanArticleDto planArticleDto) throws SQLException;

    List<PlanArticleDto> selectAllPlanArticle(List<String> groupId) throws SQLException;

    Optional<PlanArticleDto> selectPlanArticle(String planArticleId) throws SQLException;

    Optional<String> selectWriter(String planArticleId) throws SQLException, PlanArticleNotFoundException;

    Optional<String> selectGroupId(String planArticleId) throws SQLException;

    PlanArticleDto lockPlanArticle(String planArticleId);
}
