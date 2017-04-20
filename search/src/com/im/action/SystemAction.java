package com.im.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.im.util.JSONRepUtils;
@Controller
public class SystemAction {

	
	

	@RequestMapping("/help")
	public ModelAndView help(){
		return new ModelAndView("help","message","我是帮助我们");
	}
	
	
	@RequestMapping("/contact")
	public ModelAndView contact(){
		return new ModelAndView("contact","message","我是联系我们");
	}
	
	@RequestMapping("/login")
	public String login(){
		return "user/login";
	}
	
	@RequestMapping("/registSuccess")
	public String registSuccess(){
		return "user/registSuccess";
	}
	
	@RequestMapping("/regist")
	public String regist(){
		return "user/login";
		//return "user/regist";
	}
	
	@RequestMapping("/modifypass")
	public String modifypass() throws Exception {
		return "/user/modifypass";
	}

	@RequestMapping("/findpfirst")
	public String findpfirst(String id) throws Exception {
		return "/user/findpassone";
	}
	
	@RequestMapping("/findpsec")
	public String findpsec(String id) throws Exception {
		return "/user/findpasssec";
	}
	
	@RequestMapping("/userActive")
	public String userActive(){
		return "user/active";
	}
	
	@RequestMapping("/userFindpass")
	public String userFindpass(){
		return "user/findpass";
	}
}
