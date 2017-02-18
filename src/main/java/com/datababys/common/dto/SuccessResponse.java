package com.datababys.common.dto;

/**
 * 操作成功Controller返回結果
 * 
 * @author shishun
 *
 */
public class SuccessResponse implements Response {

	@Override
	public String getResponseCode() {
		return Response.CODE_SUCCESS;
	}

	@Override
	public String getResponseMsg() {
		return Response.MSG_SUCCESS;
	}

}
