package com.im.util;

import com.base.mobile.IMError;

public class ActionException extends MException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ActionException(IMError error) {
		super(error.getErrorCode(),error.getErrorMsg());
	}

	public ActionException(int errorcode,String msg){
		super(errorcode, msg);
	}
	
	
	public ActionException(Exception e){
		super(e);
	}
	
	public ActionException(int errorcode){
		super(errorcode);
	}
	
	public ActionException(String name) {
		super(10001,"缺失必要参数:"+name);
	}
}
