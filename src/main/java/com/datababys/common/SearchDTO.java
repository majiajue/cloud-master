package com.datababys.common;

public class SearchDTO {
	private String startTime;
	private String endTime;
	private String organArray;
	private Integer flag;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOrganArray() {
		return organArray;
	}

	public void setOrganArray(String organArray) {
		this.organArray = organArray;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}
