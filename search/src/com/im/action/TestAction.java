package com.im.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.base.Page;
import com.base.util.JSONUtils;
import com.im.dao.model.Question;
import com.im.util.*;
import com.im.service.QuestionService;
import com.im.util.ConstantResources;
@Controller
public class TestAction {
	

	@Autowired
	private QuestionService qser;

	
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Question> lists = new ArrayList<Question>();
		Question qs = new Question();
		qs.setAnswer("A");
		qs.setContent("五百年大闹天宫的是谁？");
		qs.setOption1("孙悟空");
		qs.setOption2("猪八戒");
		qs.setRank(1);
		qs.setCreateTime(new Date());
		qs.setModifyTime(qs.getCreateTime());
		qs.setPoint(5);
		qs.setType(1);
//		int i = qser.insert(qs);
		qs = qser.selectByPrimaryKey(8);
//		System.out.println(i);
		lists.add(qs);
		String result = JSONRepUtils.success(lists, 1);
		System.out.println(result);
		return result;
	}
	
	
	@RequestMapping("/nav")
	public ModelAndView nav(){
		return new ModelAndView("public/nav","message","我是导航");
	}
	
	
	
}
