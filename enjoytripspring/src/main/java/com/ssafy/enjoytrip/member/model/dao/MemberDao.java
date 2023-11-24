package com.ssafy.enjoytrip.member.model.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.member.model.dto.MemberDto;

@Mapper
public interface MemberDao {

	int idCheck(String userId) throws SQLException;
//	void joinMember(MemberDto memberDto, MultipartFile profileImg) throws SQLException;
	void joinMember(MemberDto memberDto) throws SQLException;
	void updateMember(MemberDto memberDto) throws SQLException;
	void deleteMember(String userId) throws SQLException;
	//void profileImgUpload(ProfileImgDto profileImgDto) throws SQLException;
	
	MemberDto login(MemberDto memberDto) throws SQLException;
	MemberDto userInfo(String userId) throws SQLException;
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userid) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
