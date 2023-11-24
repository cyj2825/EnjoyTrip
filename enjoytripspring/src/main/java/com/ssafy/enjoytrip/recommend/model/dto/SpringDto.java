package com.ssafy.enjoytrip.recommend.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="Spring Dto" ,description="봄 추천 여행지를 구성하는 DTO")
@Data
public class SpringDto {
	@ApiModelProperty(value="여행지 번호", example="0")
	private int springNo;
	@ApiModelProperty(value="여행지 이름", example="제주도")
	private String name;
	@ApiModelProperty(value="여행지 주소",example="제주시 애월읍")
	private String address;
	@ApiModelProperty(value="여행지 메인 이미지")
	private String mainImg;
	@ApiModelProperty(value="여행지 정보", example="여기는 00가 유명해요~")
	private String content;
	@ApiModelProperty(value="추천 숙소 1", example="그랜드 조선 제주")
	private String accommodation1;
	@ApiModelProperty(value="추천 숙소 2", example="호텔 브릿지 서귀포")
	private String accommodation2;
}