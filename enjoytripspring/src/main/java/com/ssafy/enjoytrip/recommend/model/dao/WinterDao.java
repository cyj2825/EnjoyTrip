package com.ssafy.enjoytrip.recommend.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.recommend.model.dto.SpringDto;
import com.ssafy.enjoytrip.recommend.model.dto.SummerDto;
import com.ssafy.enjoytrip.recommend.model.dto.WinterDto;

@Mapper
public interface WinterDao {
	List<WinterDto> listWinterPlace() throws SQLException;
	WinterDto getWinterPlace(int winterNo) throws SQLException;
}