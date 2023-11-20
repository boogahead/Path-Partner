package com.ssafy.pathpartner.planarticle.service;

import com.ssafy.pathpartner.planarticle.exception.PlanArticleNotFoundException;
import com.ssafy.pathpartner.planarticle.exception.UnauthoriedPlanRequestException;
import com.ssafy.pathpartner.travelgroup.repository.TravelGroupDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.pathpartner.planarticle.dto.PlanArticleDto;
import com.ssafy.pathpartner.planarticle.repository.PlanArticleDao;

@Service
@Slf4j
public class PlanArticleServiceImpl implements PlanArticleService {

  private final PlanArticleDao planArticleDao;
  private final TravelGroupDao travelGroupDao;

  @Autowired
  public PlanArticleServiceImpl(PlanArticleDao planArticleDao, TravelGroupDao travelGroupDao) {
    this.planArticleDao = planArticleDao;
    this.travelGroupDao = travelGroupDao;
  }

  @Override
  public boolean createPlanArticle(PlanArticleDto planArticleDto)
      throws SQLException, UnauthoriedPlanRequestException {
    if (travelGroupDao.isGroupMember(planArticleDto.getGroupId(), planArticleDto.getUuid())) {
      throw new UnauthoriedPlanRequestException("해당 그룹에 글쓰기 권한이 없습니다.");
    }
    return planArticleDao.insertPlanArticle(planArticleDto) > 0;
  }

  @Override
  public boolean deletePlanArticle(String planArticleId, String uuid)
      throws SQLException, UnauthoriedPlanRequestException, PlanArticleNotFoundException {
    // 작성자 가져오기
    String writer = planArticleDao.selectWriter(planArticleId)
        .orElseThrow(() -> new PlanArticleNotFoundException("여행 계획을 찾을 수 없습니다."));

    // 작성글 그룹 가져오기
    String groupId = planArticleDao.selectGroupId(planArticleId)
        .orElseThrow(() -> new PlanArticleNotFoundException("여행 계획을 찾을 수 없습니다."));

    // 삭제 권한 확인
    if (writer.equals(uuid) || travelGroupDao.isGroupMaster(groupId, uuid)) {
      return planArticleDao.deletePlanArticle(planArticleId) > 0;
    } else {
      throw new UnauthoriedPlanRequestException("해당 계획에 삭제 권한이 없습니다.");
    }
  }

  @Override
  public List<PlanArticleDto> searchAllPlanArticle(String uuid) throws SQLException {
    List<String> groupIdList = travelGroupDao.selectAllMyGroupId(uuid);

    if(groupIdList.isEmpty()) {
      return new ArrayList<>();
    }

    return planArticleDao.selectAllPlanArticle(groupIdList);
  }

  @Override
  public boolean updatePlanArticle(PlanArticleDto planArticleDto, String uuid)
      throws SQLException, UnauthoriedPlanRequestException {

    // 삭제 권한 확인
    if (travelGroupDao.isGroupMember(planArticleDto.getGroupId(), uuid)) {
      return planArticleDao.updatePlanArticle(planArticleDto) > 0;
    } else {
      throw new UnauthoriedPlanRequestException("해당 계획에 수정 권한이 없습니다.");
    }
  }

  @Override
  public PlanArticleDto searchPlanArticle(String plansArticleId) throws SQLException {
    return planArticleDao.selectPlanArticle(plansArticleId)
        .orElseThrow(() -> new PlanArticleNotFoundException("여행 계획을 찾을 수 없습니다."));
  }
}
