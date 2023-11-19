package com.ssafy.pathpartner.planarticle.service;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.pathpartner.planarticle.dto.PlanArticleDto;
import com.ssafy.pathpartner.planarticle.repository.PlanArticleDao;
@Service
@Slf4j
public class PlanArticleServiceImpl implements PlanArticleService{
    private final PlanArticleDao planArticleDao;

    @Autowired
    public PlanArticleServiceImpl(PlanArticleDao planArticleDao) {
        this.planArticleDao = planArticleDao;
    }

    @Override
    public void writePlan(PlanArticleDto planArticleDto) throws SQLException {
        /*
        그룹에 속했는지 확인 -> 속해있으면 writePlan
         */
        Map<String,Object> tmp=new HashMap<>();
        tmp.put("groupId",planArticleDto.getGroupId());
        tmp.put("uuid",planArticleDto.getUuid());
        if(planArticleDao.memberCheck(tmp)==0)return;
        planArticleDao.writePlan(planArticleDto);
    }

    @Override
    public void deletePlan(String planArticleId) throws SQLException {
        /*
        멤버가 작성자 / 그룹장인지 확인
        */
        String uuid=("testtesttest");//테스트용 임시 uuid
        Map<String,Object> tmp=new HashMap<>();
        tmp.put("groupId",planArticleDao.getGroupId(planArticleId));
        tmp.put("uuid",uuid);
        if(planArticleDao.fetchRole(tmp)==0){
            if(!planArticleDao.checkWriter(planArticleId).equals(uuid))return;
        }
        tmp.remove("uuid");
        tmp.put("planArticleId",planArticleId);
        planArticleDao.deletePlan(tmp);
    }

    @Override
    public List<PlanArticleDto> getPlanList(String groupId) throws SQLException {
        return planArticleDao.getPlanList(groupId);
    }

    @Override
    public void updatePlan(PlanArticleDto planArticleDto) throws SQLException {
        /*
        멤버가 작성자 / 그룹장인지 확인
        */
        String uuid=("testtesttest");//테스트용 임시 uuid
        if(!planArticleDao.checkWriter(planArticleDto.getPlanArticleId()).equals(uuid))return;
        planArticleDto.setGroupId(planArticleDao.getGroupId(planArticleDto.getPlanArticleId()));
        planArticleDao.updatePlan(planArticleDto);
    }
}
