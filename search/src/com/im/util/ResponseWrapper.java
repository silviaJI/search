package com.im.util;

public class ResponseWrapper {
	private Object data;
	/** 错误编码 */
	private int errorCode = 0; //为0表示没有出错
	/** 错误信息 */
	private String errorMsg;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
