package com.ssafy.enjoytrip.recommend.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.recommend.model.dto.SpringDto;

@Mapper
public interface SpringDao {
	List<SpringDto> listSpringPlace() throws SQLException;
	SpringDto getSpringPlace(int springNo) throws SQLException;
}