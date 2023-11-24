package com.ssafy.enjoytrip.board.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="Board Dto" ,description="게시판을 구성하는 DTO")
@Data
public class BoardDto {
	@ApiModelProperty(value="게시글 번호", example="0")
	private int articleNo;
	@ApiModelProperty(value="사용자 ID", example="Taesoo")
	private String userId;
	@ApiModelProperty(value="제목",example="테스트 제목")
	private String subject;
	@ApiModelProperty(value="글 내용", example="테스트 내용")
	private String content;
	@ApiModelProperty(value="장소", example="서울")
	private String location;
	@ApiModelProperty(value="여행 시작 날짜", example="2023-11-01")
	private String startDate;
	@ApiModelProperty(value="여행 종료 날짜", example="2023-11-05")
	private String endDate;
	@ApiModelProperty(value = "메인 이미지")
	private String mainImg;
	@ApiModelProperty(value="조회수", example="0")
	private int hit;
	@ApiModelProperty(value="작성 시각", example="2023-11-11")
	private String registerTime;
}