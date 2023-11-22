package com.ssafy.enjoytrip.map.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.map.model.dto.GugunDto;
import com.ssafy.enjoytrip.map.model.dto.MarkerDto;
import com.ssafy.enjoytrip.map.model.dto.SidoDto;

public interface MapService {
	
	List<SidoDto> getSido();
	List<GugunDto> getGugun(int sidoCode);
	List<MarkerDto> getMarker(int contentTypeId, int sidoCode, int gugunCode);
	MarkerDto getMarkerById(int contentId);
}
