package com.ssafy.enjoytrip.member.model.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.member.model.dto.MemberDto;
import com.ssafy.enjoytrip.member.model.dto.ProfileImgDto;

@Mapper
public interface ProfileDao {
	void insertImg(ProfileImgDto profileImgDto) throws SQLException;
	String getImg(String userId) throws SQLException;
	void updateImg(ProfileImgDto profileImgDto) throws SQLException;
//	void deleteMember(String userId);
}
