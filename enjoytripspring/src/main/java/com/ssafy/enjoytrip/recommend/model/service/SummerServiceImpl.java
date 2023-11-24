package com.ssafy.enjoytrip.recommend.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.exception.MyException;
import com.ssafy.enjoytrip.member.model.dao.MemberDao;
import com.ssafy.enjoytrip.member.model.dao.ProfileDao;
import com.ssafy.enjoytrip.member.model.dto.MemberDto;
import com.ssafy.enjoytrip.member.model.dto.ProfileImgDto;
import com.ssafy.enjoytrip.recommend.model.dao.SpringDao;
import com.ssafy.enjoytrip.recommend.model.dao.SummerDao;
import com.ssafy.enjoytrip.recommend.model.dto.SpringDto;
import com.ssafy.enjoytrip.recommend.model.dto.SummerDto;

@Service("SummerService")
@Repository
public class SummerServiceImpl implements SummerService {

	@Autowired
	private SummerDao summerDao;
	private Logger logger = LoggerFactory.getLogger(SummerServiceImpl.class);
	public SummerServiceImpl(SummerDao summerDao) {
		super();
		this.summerDao = summerDao;
	}

	@Override
	public List<SummerDto> listSummerPlace(){
		try {
			logger.debug("listSummerPlace..................................:{}");
			return summerDao.listSummerPlace();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("여행지 리스트 조회 중 오류 발생!");
		}
	}
	
	@Override
	public SummerDto getSummerPlace(int summerNo){
		try {
			logger.debug("getSummerPlace..................................:{}", summerNo);
			return summerDao.getSummerPlace(summerNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("여행지 조회 중 오류 발생!");
		}
	}
}