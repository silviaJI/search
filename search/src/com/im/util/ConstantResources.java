package com.im.util;

import java.util.Date;

import com.base.support.PropertySupport;
import com.base.util.DateUtil;

public class ConstantResources {
	public static String PAGEM_OUTPUT = PropertySupport.getProperty("pageM.output");
	public static int TIME = Integer.parseInt(PropertySupport.getProperty("game.time"));
	public static String STARTTIME =PropertySupport.getProperty("game.starttime"); 
	public static String OVERTIME = PropertySupport.getProperty("game.overtime");
	public static int CHANCE = Integer.parseInt(PropertySupport.getProperty("game.chance"));
	public static int LEVEL = Integer.parseInt(PropertySupport.getProperty("game.level"));
	public static String MAILFROM = PropertySupport.getProperty("mail.from");
	public static int GAMEON = Integer.parseInt(PropertySupport.getProperty("game.on"));
	
	
	public static String MAILHOST = PropertySupport.getProperty("mail.smtp");
	public static String MAILUSERNAME = PropertySupport.getProperty("mail.username");
	public static String MAILPASSWORD = PropertySupport.getProperty("mail.password");
	public static String MAILUSERNAMEQQ = PropertySupport.getProperty("qqmail.username");
	public static String MAILPASSWORDQQ = PropertySupport.getProperty("qqmail.password");
	public static String MAILUSERNAME126 = PropertySupport.getProperty("126mail.username");
	public static String MAILPASSWORD126 = PropertySupport.getProperty("126mail.password");
	
	public static int MAXTIMES = Integer.parseInt(PropertySupport.getProperty("answer.max"));
	//File Save Url
	public static String FILESAVEURL = PropertySupport.getProperty("file.saverUrl");
	
}
