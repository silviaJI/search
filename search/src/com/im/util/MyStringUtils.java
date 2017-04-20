package com.im.util;

import java.util.regex.*;

import org.apache.commons.lang.StringUtils;

import com.base.util.StringUtil;

public class MyStringUtils {
	/**
	 * 是否包含，正则表达式表示的子字符串
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean containsRegex(String regex, String str) {
		boolean flag = false;
		
		Matcher matcher = Pattern.compile(regex).matcher(str);
		while(matcher.find()) {
			flag = true;
		}
		
		return flag;
	}
	
	//content truncate
	public static String contentTruncate(String content) {
		content = StringUtil.emptyIfBlank(content); 
		return content.substring(0, 
				content.length() > 20 ? 20 : content.length());
	}
	
	public static String replaceColon(String str){
		String result = "";
		if(StringUtils.isNotBlank(str)){
			result = str.replaceAll("\"", "'").replaceAll("\n|\t", "");
		}
		return result;
	}
}
