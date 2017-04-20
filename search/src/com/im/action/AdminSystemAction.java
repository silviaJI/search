package com.im.action;

import org.json.simple.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.action.CoreController;
import com.im.dao.model.Admin;
import com.im.dao.model.AdminExample;
import com.im.filter.LoginFilter;
import com.im.service.AdminService;
import com.im.util.WebPageFileController;
import com.im.util.MD5C;

@Controller
public class AdminSystemAction extends CoreController {
	@Autowired
	private AdminService adminService;
	//预览文件处理器
	public static WebPageFileController previewFileController = new WebPageFileController("", "preview", "utf8");
	
	/**
	 * 进入主页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/indexManager")
	public String index(HttpSession session, ModelMap map) throws Exception {
		Object obj = session.getAttribute(LoginFilter.SESSION_USER_KEYM);
		if (obj != null) {
			return "admin/main";
		}
		return "admin/login";
	}
	
	
	
	@RequestMapping("/loginM")
	@ResponseBody
	public String login(String account, String pwd, HttpSession session) throws Exception {
		//if (StringUtils.isBlank(account) || StringUtils.isBlank(pwd)) {
		//	return "1";
		//}
		Map<String, String> map = new HashMap<String, String>();
		AdminExample example = new AdminExample();
		example.createCriteria().andAccountEqualTo(account).andPasswordEqualTo(MD5C.calcMD5(pwd,false)).andStateEqualTo(1);
		List<Admin> list = adminService.selectByExample(example);
		//if (list != null && list.size() > 0) {
			Admin admin = list.get(0);
			map.put("id", admin.getId());
			//session.setAttribute("appid", admin.getAppid());
			session.setAttribute("isM", 1);
			session.setAttribute("account", account);
			session.setAttribute(LoginFilter.SESSION_USER_KEYM, map);
		//} else {
		//	return "2";
		//}
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/changePwdM")
	@ResponseBody
	public String changePwd(String oldpwd, String newpwd, HttpSession session) throws Exception {
		HashMap<String, String> map = (HashMap) session.getAttribute(LoginFilter.SESSION_USER_KEY);
		String id = map.get("id");
		// 超级管理员
		AdminExample example = new AdminExample();
		example.createCriteria().andIdEqualTo(id).andPasswordEqualTo(MD5C.calcMD5(oldpwd,false));
		List<Admin> list = adminService.selectByExample(example);
		if (list != null && list.size() > 0) {
			Admin admin = new Admin();
			admin.setId(id);
			admin.setPassword(MD5C.calcMD5(newpwd,false));
			admin.setModifyTime(new Date());
			adminService.updateByPrimaryKeySelective(admin);
			return "success";
		}
		return "";
	}
	
	@RequestMapping("/logoutM")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:indexManager.do";
	}
	
	@RequestMapping("/pageContent")
	@ResponseBody
	public String pageContent(String url) throws Exception {
		WebPageFileController fileController = new WebPageFileController();
		String result = fileController.read(url, "utf-8"); //为编辑修改而去掉这些
		result = result.replaceAll("(?s).*?<!--editorcontentstart-->(.*?)<!--editorcontentend-->.*", "$1");
		result = result.replaceAll("(?s)<!DOCTYPE html>.*?<body>(.*?)<script src=\"http://cdn.*", "$1");
		
		return result;
	}
	
	/**
	 * 预览网页文件
	 * @param contextReal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/preview",method=RequestMethod.POST)
	@ResponseBody
	public String preview(String contextReal) throws Exception {		
		return previewFileController.write(previewFileController.getHtmlContentUTF8(contextReal), "html");
	}
	
	@RequestMapping("/showImg")
	public String showImg(){
		return "admin/showImg";
	}
}
