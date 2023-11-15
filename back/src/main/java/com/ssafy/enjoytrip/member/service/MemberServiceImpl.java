package com.ssafy.enjoytrip.member.service;


import com.ssafy.enjoytrip.member.dto.MemberDto;
import com.ssafy.enjoytrip.member.repository.MemberDao;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

  private final MemberDao memberDao;

  @Autowired
  public MemberServiceImpl(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
//  @Transactional(rollbackFor = Exception.class)
  public int registMember(MemberDto memberDto) throws SQLException {

    if (memberDao.selectMemberById(memberDto.getUserId()) != null) {
      return 0;
    } else {
      if (memberDto.getRoles() == null || memberDto.getRoles().isEmpty()) {
        memberDto.setRoles("USER");
      }

      log.debug("password encode");
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String encodedPwd = encoder.encode(memberDto.getUserPass());

      memberDto.setUserPass(encodedPwd);
    }
    return memberDao.insertMember(memberDto);
  }

  @Override
  public MemberDto login(String userId, String userPass) throws SQLException {
    MemberDto loginMember = memberDao.selectMemberById(userId);

    if (loginMember != null) {
      boolean isValid = BCrypt.checkpw(userPass, loginMember.getUserPass());

      if (isValid) {
        return loginMember;
      }
    }
    return null;
  }

  @Override
//  @Transactional(rollbackFor = Exception.class)
  public int modifyMember(MemberDto memberDto, MemberDto loginMember)
      throws SQLException {

    memberDto.setUid(loginMember.getUid());

    if (memberDto.getUserPass() == null || memberDto.getUserPass().isEmpty()) {
      memberDto.setUserPass(loginMember.getUserPass());
    }

    if (memberDto.getUserName() == null || memberDto.getUserName().isEmpty()) {
      memberDto.setUserName(loginMember.getUserName());
    }

    if (memberDto.getRoles() == null || memberDto.getRoles().isEmpty()) {
      memberDto.setRoles(loginMember.getRoles());
    }

    return memberDao.updateMember(memberDto);
  }

  @Override
//  @Transactional(rollbackFor = Exception.class)
  public int deleteMember(int uid) throws SQLException {
    return memberDao.deleteMember(uid);
  }

  @Override
  public MemberDto searchMemberById(String userId) throws SQLException {
    return memberDao.selectMemberById(userId);
  }

  @Override
//  @Transactional(rollbackFor = Exception.class)
  public int updatePassword(MemberDto memberDto) throws SQLException {
    MemberDto result = memberDao.selectMemberByIdAndName(memberDto);

    if (result != null) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String encodedPwd = encoder.encode(memberDto.getUserPass());

      result.setUserPass(encodedPwd);
      return memberDao.updateMemberPassword(result);
    }
    return 0;
  }
}
