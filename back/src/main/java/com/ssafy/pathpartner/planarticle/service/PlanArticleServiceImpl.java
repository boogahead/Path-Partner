package com.ssafy.pathpartner.planarticle.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.pathpartner.planarticle.dto.PlanArticleMessage;
import com.ssafy.pathpartner.planarticle.exception.PlanArticleNotFoundException;
import com.ssafy.pathpartner.planarticle.exception.UnauthoriedPlanRequestException;
import com.ssafy.pathpartner.travelgroup.repository.TravelGroupDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.ssafy.pathpartner.planarticle.dto.PlanArticleDto;
import com.ssafy.pathpartner.planarticle.repository.PlanArticleDao;

@Service
@Slf4j
public class PlanArticleServiceImpl implements PlanArticleService {

  private final PlanArticleDao planArticleDao;
  private final TravelGroupDao travelGroupDao;
  private final RedisTemplate<String, String> redisTemplate;
  private final SimpMessagingTemplate messagingTemplate;

  private long sequenceNumber = 0;

  private synchronized long getNextSequenceNumber() {
    return ++sequenceNumber;
  }

  @Autowired
  public PlanArticleServiceImpl(PlanArticleDao planArticleDao, TravelGroupDao travelGroupDao,
      RedisTemplate<String, String> redisTemplate, SimpMessagingTemplate messagingTemplate) {
    this.planArticleDao = planArticleDao;
    this.travelGroupDao = travelGroupDao;
    this.redisTemplate = redisTemplate;
    this.messagingTemplate = messagingTemplate;
  }

  @Override
  public String createPlanArticle(PlanArticleDto planArticleDto)
      throws SQLException, UnauthoriedPlanRequestException, JsonProcessingException {
    if (!travelGroupDao.isGroupMember(planArticleDto.getGroupId(), planArticleDto.getUuid())) {
      throw new UnauthoriedPlanRequestException("해당 그룹에 글쓰기 권한이 없습니다.");
    }

    boolean result = planArticleDao.insertPlanArticle(planArticleDto) > 0;

//    if (result) {
//      PlanArticleMessage message = new PlanArticleMessage();
//      message.setOperation("create");
//      message.setPlanArticleDto(planArticleDto);
//      message.setSequenceNumber(getNextSequenceNumber());
//      String messageJson = new ObjectMapper().writeValueAsString(message);
//
//      // Store the messageJson in Redis
//      redisTemplate.opsForValue().set(planArticleDto.getPlanArticleId(), messageJson);
//
//      // Broadcast the messageJson to all connected clients
//      messagingTemplate.convertAndSend("/topic/planArticle"+planArticleDto.getGroupId(), messageJson);
//    }

    return planArticleDto.getPlanArticleId();
  }

  @Override
  public boolean deletePlanArticle(String planArticleId, String uuid)
      throws SQLException, UnauthoriedPlanRequestException, JsonProcessingException {

    String writer = planArticleDao.selectWriter(planArticleId)
        .orElseThrow(() -> new PlanArticleNotFoundException("여행 계획을 찾을 수 없습니다."));
    String groupId = planArticleDao.selectGroupId(planArticleId)
        .orElseThrow(() -> new PlanArticleNotFoundException("여행 계획을 찾을 수 없습니다."));

    if (writer.equals(uuid) || travelGroupDao.isGroupMaster(groupId, uuid)) {
      boolean isDeleted = planArticleDao.deletePlanArticle(planArticleId) > 0;

//      if (isDeleted) {
//        // Remove the planArticle from Redis
//
//        redisTemplate.delete(planArticleId);
//        PlanArticleMessage message = new PlanArticleMessage();
//        message.setOperation("delete");
//        message.setPlanArticleDto(new PlanArticleDto(
//            planArticleId)); // assuming PlanArticleDto has a constructor that takes planArticleId
//        message.setSequenceNumber(getNextSequenceNumber());
//        String messageJson = new ObjectMapper().writeValueAsString(message);
//
//        // Broadcast the deletion of the planArticle to all connected clients
//        messagingTemplate.convertAndSend("/topic/planArticle" + planArticleId, messageJson);
//      }

      return isDeleted;
    } else {
      throw new UnauthoriedPlanRequestException("해당 계획에 삭제 권한이 없습니다.");
    }
  }

  @Override
  public List<PlanArticleDto> searchAllPlanArticle(String uuid) throws SQLException {
    List<String> groupIdList = travelGroupDao.selectAllMyGroupId(uuid);

    if (groupIdList.isEmpty()) {
      return new ArrayList<>();
    }

    return planArticleDao.selectAllPlanArticle(groupIdList);
  }

  @Override
  public boolean updatePlanArticle(PlanArticleDto planArticleDto, String uuid)
      throws SQLException, UnauthoriedPlanRequestException, JsonProcessingException {
    String groupId = planArticleDao.selectGroupId(planArticleDto.getPlanArticleId())
        .orElseThrow(() -> new PlanArticleNotFoundException("해당하는 여행계획을 찾을 수 없습니다."));

    if (travelGroupDao.isGroupMember(groupId, uuid)) {
//      planArticleDao.lockPlanArticle(planArticleDto.getPlanArticleId());
      boolean isUpdated = planArticleDao.updatePlanArticle(planArticleDto) > 0;

//      if (isUpdated) {
//
//        // Convert the planArticleDto to JSON
//        PlanArticleMessage message = new PlanArticleMessage();
//        message.setOperation("update");
//        message.setPlanArticleDto(planArticleDto);
//        message.setSequenceNumber(getNextSequenceNumber());
//        String messageJson = new ObjectMapper().writeValueAsString(message);
//
//        // Update the messageJson in Redis
//        redisTemplate.opsForValue().set(planArticleDto.getPlanArticleId(), messageJson);
//
//        // Broadcast the messageJson to all connected clients
//        messagingTemplate.convertAndSend("/topic/planArticle" + planArticleDto.getGroupId(),
//            messageJson);
//      }

      return isUpdated;
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
