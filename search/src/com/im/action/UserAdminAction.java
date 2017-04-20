package com.im.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.Page;
import com.base.dialect.PaginationSupport;
import com.im.dao.model.User;
import com.im.dao.model.UserExample;
import com.im.dao.model.UserExample.Criteria;
import com.im.util.*;
import com.im.service.UserService;
@Controller
public class UserAdminAction {
	
	@Autowired
	private UserService qser;
	
	@RequestMapping("/listUser")
	@ResponseBody
	public String list(Page param,User user) throws Exception{
		UserExample example = new UserExample();
		PaginationSupport.setContext(param.getPageStart(), param.getPageSize(), null);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(user.getName())){
			criteria.andNameLike("%"+user.getName()+"%");
		}
		if(StringUtils.isNotBlank(user.getStuId())){
			criteria.andStuIdLike("%" + user.getStuId() + "%");
		}
		if(StringUtils.isNotBlank(user.getPhone())){
			criteria.andPhoneLike("%" + user.getPhone() + "%");
		}
		if(StringUtils.isNotBlank(user.getEmail())){
			criteria.andEmailLike("%" + user.getEmail() + "%");
		}
		if(StringUtils.isNotBlank(user.getSchool())){
			criteria.andSchoolLike("%"+user.getSchool()+"%");
		}
		if(StringUtils.isNotBlank(user.getDepart())){
			criteria.andDepartLike("%"+user.getDepart()+"%");
		}
		if(user.getIsV() != null){
			criteria.andIsVEqualTo(user.getIsV());
		}
		if(user.getEducation() != null){
			criteria.andEducationEqualTo(user.getEducation());
		}
		example.setOrderByClause("score DESC,create_time DESC");
		List<User> list = qser.selectByExample(example);
		return JSONRepUtils.serialize(new Page(PaginationSupport.getContext().getTotalCount(), list), 0);
	}
	
	@RequestMapping("/showlistUser")
	public String list() {
		return "users/userList";
	}
	
	@RequestMapping("/toEditUser")
	public String toEdit()throws Exception{
		return "users/userEdit";
	}
	
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	@ResponseBody
	public String save(String contextReal,User user, Integer isTop, HttpSession session)throws Exception{
		//WebPageFileController fileController = new WebPageFileController("bs", "news", "utf8");
		user.setCreateTime(new Date());
		try{
			if(user.getId() == null){
				qser.insert(user);
			}else{
				user.setModifyTime(new Date());
				qser.updateByPrimaryKeySelective(user);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "{\"success\":false}";
		}
		return "{\"success\":true}";
	}
	
	@RequestMapping(value="/findByIdUser")
	@ResponseBody
	public String findById(String id,HttpSession session, Integer isM)throws Exception{		
		User n = qser.selectByPrimaryKey(id);
		return JSONRepUtils.serialize(n, isM);
	}
	
	@RequestMapping("/delUser")
	@ResponseBody
	public String del(String id,String url,HttpSession session)throws Exception{
//		WebPageFileController fileController = new WebPageFileController();
		String[] ids = id.split(",");
		int len = ids.length;
		for(int i = 0; i < len; i++) {
			qser.deleteByPrimaryKey(ids[i]);
//			fileController.deleteFile(urls[i]);
		}
		return "{\"success\":true}";
	}	
}
