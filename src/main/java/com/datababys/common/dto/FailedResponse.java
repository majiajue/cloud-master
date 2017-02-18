package com.datababys.common.dto;

/**
 * 操作失败Controller返回结果
 * 
 * @author ycitss
 * @param <T>
 *
 */
public class FailedResponse implements Response {
	private String responseCode;// 返回编码
	private String responseMsg; // 返回信息

	public FailedResponse() {
		this.responseCode = Response.CODE_FAILED;
		this.responseMsg = Response.MSG_FAILED;
	};

	public FailedResponse(String responseCode, String responseMsg) {
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
	};

	public FailedResponse(String responseMsg) {
		this.responseCode = Response.CODE_FAILED;
		this.responseMsg = responseMsg;
	};

	@Override
	public String getResponseCode() {
		return responseCode;
	}

	@Override
	public String getResponseMsg() {
		return responseMsg;
	}

}
