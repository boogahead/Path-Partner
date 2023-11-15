package com.ssafy.enjoytrip.member.repository;

import com.ssafy.enjoytrip.member.dto.MemberDto;
import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface MemberDao {

  int insertMember(MemberDto memberDto) throws SQLException;

  MemberDto selectMemberById(String userId) throws SQLException;

  int updateMember(MemberDto memberDto) throws SQLException;

  int updateMemberPassword(MemberDto memberDto) throws SQLException;

  int deleteMember(int uid) throws SQLException;

  MemberDto selectMemberByIdAndName(MemberDto memberDto) throws SQLException;
}
