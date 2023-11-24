package com.ssafy.enjoytrip.recommend.model.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.exception.MyException;
import com.ssafy.enjoytrip.recommend.model.dao.WinterDao;
import com.ssafy.enjoytrip.recommend.model.dto.WinterDto;

@Service("WinterService")
@Repository
public class WinterServiceImpl implements WinterService {

	@Autowired
	private WinterDao winterDao;
	private Logger logger = LoggerFactory.getLogger(WinterServiceImpl.class);
	public WinterServiceImpl(WinterDao winterDao) {
		super();
		this.winterDao = winterDao;
	}

	@Override
	public List<WinterDto> listWinterPlace(){
		try {
			logger.debug("listWinterPlace..................................:{}");
			return winterDao.listWinterPlace();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("여행지 리스트 조회 중 오류 발생!");
		}
	}
	
	@Override
	public WinterDto getWinterPlace(int winterNo){
		try {
			logger.debug("getWinterPlace..................................:{}", winterNo);
			return winterDao.getWinterPlace(winterNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("여행지 조회 중 오류 발생!");
		}
	}
}