package com.ssafy.enjoytrip.recommend.model.service;
import java.util.List;

import com.ssafy.enjoytrip.recommend.model.dto.AutumnDto;
import com.ssafy.enjoytrip.recommend.model.dto.SpringDto;

public interface AutumnService {
	List<AutumnDto> listAutumnPlace();
	AutumnDto getAutumnPlace(int autumnNo);
}