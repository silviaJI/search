package com.im.util;

import java.io.Serializable;

import com.base.util.JSONUtils;

public class JSONRepUtils {
	public static String serialize(Object data, Integer isM) {
		if(isM != null && isM == 1) {
			ResponseWrapper rw = new ResponseWrapper();
			rw.setData(data);
			data = rw;
		}
		return JSONUtils.serialize(data);
	}
	
	public static String success(Object data, Integer isM){
		if(isM != null && isM == 0) {
			ResponseWrapper rw = new ResponseWrapper();
			rw.setData(data);
			data = rw;
		}
		return JSONUtils.serialize(data);
	}
	
	public static String fail(Object data,String message, Integer isM){
		if(isM != null && isM == 0) {
			ResponseWrapper rw = new ResponseWrapper();
			rw.setErrorCode(1);
			rw.setData(data);
			rw.setErrorMsg(message);
			data = rw;
		}
		return JSONUtils.serialize(data);
	}
	
	public static String fail(Object data,String message,int errorCode,  Integer isM){
		if(isM != null && isM == 0) {
			ResponseWrapper rw = new ResponseWrapper();
			rw.setErrorCode(errorCode);
			rw.setData(data);
			rw.setErrorMsg(message);
			data = rw;
		}
		return JSONUtils.serialize(data);
	}

	public static String serialize(Exception exception) {
		ResponseWrapper res = new ResponseWrapper();
		res.setData(new EmptyObj());
		if(exception instanceof ActionException)
			res.setErrorCode(((ActionException)exception).getCode());
		else
			res.setErrorCode(1);
		res.setErrorMsg(exception.getMessage());
		return JSONUtils.serialize(res);
	}
	
	static class EmptyObj implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private long time = System.currentTimeMillis();
		public long getTime() {
			return time;
		}
		public void setTime(long time) {
			this.time = time;
		}
	}
}
