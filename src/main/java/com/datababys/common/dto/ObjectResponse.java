package com.datababys.common.dto;

/**
 * 返回Object结果
 * 
 * @author ycitss
 *
 * @param <T>
 */
public class ObjectResponse<T>  implements Response {
	private String responseCode;// 返回编码
	private String responseMsg; // 返回信息
	private T datas;// 返回数据

	public ObjectResponse(String responseCode, String responseMsg, T datas) {
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
		this.datas = datas;
	}

	public ObjectResponse(T datas) {
		this.responseCode = CODE_SUCCESS;
		this.responseMsg = MSG_SUCCESS;
		this.datas = datas;
	}

	@Override
	public String getResponseCode() {
		return responseCode;
	}

	@Override
	public String getResponseMsg() {
		return responseMsg;
	}

	/**
	 * @return the datas
	 */
	public T getDatas() {
		return datas;
	}

	/**
	 * @param datas
	 *            the datas to set
	 */
	public void setDatas(T datas) {
		this.datas = datas;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @param responseMsg
	 *            the responseMsg to set
	 */
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListResponse [responseCode=" + responseCode + ", responseMsg=" + responseMsg + ", datas=" + datas + "]";
	}

}
