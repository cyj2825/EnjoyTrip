package com.ssafy.enjoytrip.board.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="BoardComment Dto" ,description="게시판 댓글을 구성하는 DTO")
@Data
public class BoardCommentDto {
	@ApiModelProperty(value="글 번호", example="0")
	private int articleNo;
	@ApiModelProperty(value="댓글 번호", example="0")
	private int commentNo;
	@ApiModelProperty(value="사용자 ID", example="Yejin")
	private String userId;
	@ApiModelProperty(value="댓글 내용", example="하이하이")
	private String commentContent;
	@ApiModelProperty(value="작성 시각", example="1999-01-01")
	private String commentRegisterTime;
}