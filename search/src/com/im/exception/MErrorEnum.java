package com.im.exception;

import com.base.mobile.IMError;

public enum MErrorEnum implements IMError{
	APPID_FAIL_ERROR(1001,"缺少appid"),
	DEVICE_FAIL_ERROR(1002,"缺少deviceid"),
	LOGIN_FAIL_ERROR(1003,"登录验证失败"), 
	PAGE_PARAM1_ERROR(1004,"缺少分页参数page"),
	PAGE_PARAM2_ERROR(1005,"缺少分页参数limit"),
	REQS_OVERFLOW(1006,"系统正忙，请稍后再试~"),
	USER_INSERT_ERROR(1007,"注册用户失败"),
	USER_UPDATE_ERROR(1008, "更新用户信息失败"),
	USER_EXISTS(1008, "用户名或邮箱已存在"),
	LOGIN_FIRST(1009, "请先登录"),
	TOKEN_OUTOFTIME_ERROR(1996, "凭证已过期"),
	EMAIL_SEND_ERROR(1997, "邮件发送失败"),
	EMAIL_NOT_EXIST(1998, "对应注册邮箱不存在"),
	PASSWORD_ERROR(1999, "旧密码不匹配"),
	QUESTION_NOT_EXISTS(2000, "问题不存在");
	

	/** 错误编码 */
	private int errorCode;

	/** 错误信息 */
	private String errorMsg;
	
	/**
	 * 构造函数
	 */
	private MErrorEnum(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * @return int 错误代码
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @return String 错误信息
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

}
