package com.ssafy.enjoytrip.member.service;

import com.ssafy.enjoytrip.member.model.dto.MemberDto;

public interface MemberService {

	int idCheck(String userId);
	void joinMember(MemberDto memberDto);
	void updateMember(MemberDto memberDto);
	void deleteMember(String userId);
	
	MemberDto login(MemberDto memberDto) throws Exception;
	MemberDto userInfo(String userId) throws Exception;
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
}