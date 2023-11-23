package com.ssafy.enjoytrip.recommend.model.service;
import java.util.List;

import com.ssafy.enjoytrip.recommend.model.dto.SpringDto;
import com.ssafy.enjoytrip.recommend.model.dto.SummerDto;

public interface SummerService {
	List<SummerDto> listSummerPlace();
	SummerDto getSummerPlace(int summerNo);
}