package com.im.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.util.SpringContextUtil;
import com.im.dao.model.User;
import com.im.service.UserService;

public class UserSessionListener implements HttpSessionListener{
	
	private UserService userser;
	
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		// TODO Auto-generated method stub
		HttpSession session = (HttpSession)sessionEvent.getSession();
//		System.out.println(session.getAttribute("userid"));
	}

	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		// TODO Auto-generated method stub
		if(userser == null)
			userser = SpringContextUtil.getBean(UserService.class);
		HttpSession session = (HttpSession)sessionEvent.getSession();
		String userid = "";
		if(session.getAttribute("userid") != null){
		   userid = (String)session.getAttribute("userid");
		}
		if(StringUtils.isNotBlank(userid)){
			User user = new User();
			user.setId(userid);
			user.setIp("");
			userser.updateByPrimaryKeySelective(user);
		}
//		System.out.println(session.getAttribute("userid"));
	}

}
