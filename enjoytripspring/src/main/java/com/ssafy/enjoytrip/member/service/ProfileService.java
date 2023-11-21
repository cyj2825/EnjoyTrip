package com.ssafy.enjoytrip.member.service;

import com.ssafy.enjoytrip.member.model.dto.ProfileImgDto;

public interface ProfileService {
	void insertImg(ProfileImgDto profileImgDto);
	String getImg(String userId);
//	void deleteMember(String userId);
}