package com.im.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class EmailCheckUtil {
	

	private static Map<String,String> emailMap = new HashMap<String,String>();
	static{
		emailMap.put("163", ConstantResources.MAILUSERNAME+"-"+ConstantResources.MAILPASSWORD);
		emailMap.put("qq", ConstantResources.MAILUSERNAMEQQ +"-"+ConstantResources.MAILPASSWORDQQ);
		emailMap.put("126", ConstantResources.MAILUSERNAME126 +"-"+ConstantResources.MAILPASSWORD126);
	}
	
	public static String[] getEmailInfo(String email){
		String type = email.substring(email.lastIndexOf("@")+1, email.lastIndexOf("."));
		String mailInfo = emailMap.get(type.toLowerCase());
		if(StringUtils.isBlank(mailInfo)){
			mailInfo = ConstantResources.MAILUSERNAME+"-"+ConstantResources.MAILPASSWORD;
		}
		return mailInfo.split("-");
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
