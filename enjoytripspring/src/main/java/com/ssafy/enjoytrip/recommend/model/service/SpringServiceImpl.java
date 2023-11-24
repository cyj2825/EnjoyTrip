package com.ssafy.enjoytrip.recommend.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.exception.MyException;
import com.ssafy.enjoytrip.recommend.model.dao.SpringDao;
import com.ssafy.enjoytrip.recommend.model.dto.SpringDto;

@Service("SpringService")
@Repository
public class SpringServiceImpl implements SpringService {

	@Autowired
	private SpringDao springDao;
	private Logger logger = LoggerFactory.getLogger(SpringServiceImpl.class);
	public SpringServiceImpl(SpringDao springDao) {
		super();
		this.springDao = springDao;
	}

	@Override
	@Transactional
	public List<SpringDto> listSpringPlace(){
		try {
			logger.debug("listSpringPlace..................................:{}");
			return springDao.listSpringPlace();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("여행지 리스트 조회 중 오류 발생!");
		}
	}
	
	@Override
	public SpringDto getSpringPlace(int springNo){
		try {
			logger.debug("getSpringPlace..................................:{}", springNo);
			return springDao.getSpringPlace(springNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("여행지 조회 중 오류 발생!");
		}
	}
}