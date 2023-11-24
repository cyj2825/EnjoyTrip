package com.ssafy.enjoytrip.map.model.dto;

import lombok.Data;

@Data
public class SidoDto {
	
	int sidoCode;
	String sidoName;
	
	public int getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	
	@Override
	public String toString() {
		return "SidoDto [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
	}
	
	public SidoDto(int sidoCode, String sidoName) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}
}
