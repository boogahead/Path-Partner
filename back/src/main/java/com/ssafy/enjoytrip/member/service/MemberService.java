package com.ssafy.enjoytrip.member.service;

import com.ssafy.enjoytrip.member.dto.MemberDto;
import java.sql.SQLException;

public interface MemberService {

	int registMember(MemberDto memberDto) throws SQLException;

	MemberDto login(String userId, String userPass) throws SQLException;

	int modifyMember(MemberDto memberDto, MemberDto loginUser) throws SQLException;

	int deleteMember(int uid) throws SQLException;

	MemberDto searchMemberById(String userId) throws SQLException;

	int updatePassword(MemberDto memberDto) throws SQLException;

}
