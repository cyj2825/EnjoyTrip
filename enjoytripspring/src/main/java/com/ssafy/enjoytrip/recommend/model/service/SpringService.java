package com.ssafy.enjoytrip.recommend.model.service;
import java.util.List;

import com.ssafy.enjoytrip.recommend.model.dto.SpringDto;

public interface SpringService {
	List<SpringDto> listSpringPlace();
	SpringDto getSpringPlace(int springNo);
}