package com.ssafy.enjoytrip.member.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.exception.MyException;
import com.ssafy.enjoytrip.member.model.dao.MemberDao;
import com.ssafy.enjoytrip.member.model.dao.ProfileDao;
import com.ssafy.enjoytrip.member.model.dto.MemberDto;
import com.ssafy.enjoytrip.member.model.dto.ProfileImgDto;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileDao profileDao;
	private Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);
	public ProfileServiceImpl(ProfileDao profileDao) {
		super();
		this.profileDao = profileDao;
	}

	@Override
	public void insertImg(ProfileImgDto profileImgDto){
		try {
			logger.debug("insertImg..................................:{}", profileImgDto);
			profileDao.insertImg(profileImgDto);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("프로필 이미지 추가 중 오류 발생!");
		}
	}
	
	@Override
	public String getImg(String userId){
		try {
			logger.debug("getImg..................................:{}", userId);
			return profileDao.getImg(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("프로필 이미지 조회 중 오류 발생!");
		}
	}
	
	@Override
	public void updateImg(ProfileImgDto profileImgDto){
		try {
			logger.debug("updateImg..................................:{}", profileImgDto);
			profileDao.updateImg(profileImgDto);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("프로필 이미지 수정 중 오류 발생!");
		}
	}
}