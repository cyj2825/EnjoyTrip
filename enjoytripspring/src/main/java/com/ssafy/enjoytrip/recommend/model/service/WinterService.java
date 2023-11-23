package com.ssafy.enjoytrip.recommend.model.service;
import java.util.List;

import com.ssafy.enjoytrip.recommend.model.dto.SpringDto;
import com.ssafy.enjoytrip.recommend.model.dto.WinterDto;

public interface WinterService {
	List<WinterDto> listWinterPlace();
	WinterDto getWinterPlace(int winterNo);
}