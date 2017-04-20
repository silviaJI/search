package com.im.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.base.dialect.PaginationSupport;
import com.base.support.PropertySupport;
import com.im.dao.model.User;
import com.im.dao.model.UserExample;
import com.im.dao.model.UserToken;
import com.im.dao.model.UserTokenExample;
import com.im.util.*;
import com.im.service.UserService;
import com.im.service.UserTokenService;
import com.im.util.ConstantResources;
import com.im.util.MailUtil;
import com.im.util.SimpleMailSender;
import com.im.exception.MErrorEnum;
import com.im.filter.LoginFilter;

@Controller
public class UserAction {

	@Autowired
	private UserService userser;
	
	@Autowired
	UserTokenService tokenser;
	
	private static String registFormat = PropertySupport.getProperty("regist.format");
	private static String passFormat = PropertySupport.getProperty("pass.format");
	private static String registUrl = PropertySupport.getProperty("regist.url");
	private static String passUrl = PropertySupport.getProperty("pass.url");

	@RequestMapping("/email")
	@ResponseBody
	public void email()throws Exception{
		/*
		ArrayList<String>list=new ArrayList<String>();
		String content = "您好！恭喜您已进入信息检索大赛的复赛阶段。还未答题的同学请于4月27日20:00至4月30日23:59期间内登陆官网（http://im.nju.edu.cn/search/index.do）进行答题（温馨提示，本次答题只有一次机会，请确认答题环境无误后再开始答题，有任何问题可以联系我们），预祝您取得好成绩！";
		UserExample uex = new UserExample();
		uex.createCriteria().andIsVEqualTo(2);
//		uex.setOrderByClause("create_time DESC");
		PaginationSupport.setContext(1, 200, null);
		List<User> users = userser.selectByExample(uex);
		for(int i = 0 ; i < users.size() ; i++ ){
			User user = users.get(i);
//70 142
			if(i==70||i==142){
			try{
//			if(user.getEmail().contains("qq")){
				System.out.println(i+  "---" + user.getName() + "----" + user.getEmail()+" ");
				MailUtil.sendMail(user.getEmail(), String.format("南京大学第六届信息检索大赛复赛通知"),user.getName()+" " +content+" 南京大学第六届信息检索大赛主办方  2016年4月27日");
				Thread.sleep(500);
//			}
			}catch(Exception e){
				System.out.println("fail  "+user.getEmail()+"  "+i);
				Thread.sleep(2000);
				list.add("fail  "+user.getEmail()+"  "+i);
			}
			}
			
		}
		System.out.println("sus");
		try{
			File outfile=new File("E:\\webyouxiang.txt");
			FileWriter fw=new FileWriter(outfile);
			BufferedWriter bufw=new BufferedWriter(fw);
			for(String str:list){
				bufw.write(str);
				bufw.newLine();
			}
			bufw.close();
			fw.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
//		return "index";
		*/
	}
	
	@RequestMapping("/Mlogin")
	@ResponseBody
	public String login(User user, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		//首先判断日期是否在比赛期间
		
//		if(!checkDate() && !user.getUsername().equals("694830883@qq.com") && !user.getUsername().equals("liuhuoyunju@163.com")){
//			return JSONRepUtils.fail(null, "复赛已经结束！！快去排名中查看自己是否进入决赛吧！！",1000, 0);
//		}
		//分为通过邮箱或者手机号码登录
		if(true){
			return JSONRepUtils.fail(null, "复赛已经结束！！请耐心等待，成绩会在查看排名中发布",1000, 0);
		}
		String message = "";
		UserExample example = new UserExample();
		List<User> list = null;
		if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
			return JSONRepUtils.fail(null, "账号或密码不能为空",1232, 0);
		}
		if(StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword())){
			example.createCriteria().andPhoneEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
			UserExample.Criteria creatia1 = example.createCriteria().andEmailEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
			example.or(creatia1);
		}
		
		list = userser.selectByExample(example);
		if(list.size() > 0) {
			user = list.get(0);
		
			if(user.getIsV() == 0){
				final String userid = user.getId();
				final String name = user.getName();
				final String email = user.getEmail();
				new Thread(new Runnable() {
					public void run() {
						// TODO Auto-generated method stub
						//获取该用户最新的一条未验证token
						UserTokenExample utex = new UserTokenExample();
						utex.createCriteria().andUseridEqualTo(userid).andIsPolishEqualTo(0);
						List<UserToken> tokens = tokenser.selectByExample(utex);
						if(tokens != null && tokens.size() >0){
							//SimpleMailSender sender = SimpleMailSender.create(email);
							StringBuilder sb = new StringBuilder();
							sb.append(registUrl).append("token=").append(tokens.get(0).getId());
							String content = String.format(registFormat,sb.toString());
							try {
								if(!MailUtil.sendMail(email, String.format("南京大学信息检索大赛-%s账号激活",name),content)){
								}
							} catch (Exception e) {
								e.printStackTrace();
							} 
						}
					}
				}).start();
				return JSONRepUtils.fail(null, "您的账号未验证，请先登录您的注册邮箱进行验证",1000, 0);
			}else if(user.getIsV()!=2){
				return JSONRepUtils.fail(null, "您未能通过初赛",1000, 0);
		    }
			else if(StringUtils.isNotBlank(user.getIp())){
				return JSONRepUtils.fail(null, "您当前已在别处登录或之前没有正常退出，请先注销或者等待15分钟后再重新登录",1000, 0);
			}
			//message=JSONRepUtils.fail(null, "恭喜您已通过初赛，复赛时间4月27日",1000, 0);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", user.getId());
			session.setAttribute("userid", user.getId());
			session.setAttribute("name", user.getName());
			session.setAttribute("isM", 0);
			session.setAttribute(LoginFilter.SESSION_USER_KEY, map);
			message = JSONRepUtils.success(user, 0);
			//更新ip
			user.setIp(request.getRemoteAddr());
			userser.updateByPrimaryKeySelective(user);
			
			
		} else {
			message = JSONRepUtils.fail(null, "登陆验证失败", 0);
		}
		return message;
	}
	
	@RequestMapping("/MChangePass")
	@ResponseBody
	public String changepass(HttpSession session,User user, String passsordNew, Integer isM)throws Exception {
		UserExample uex = new UserExample();
		uex.createCriteria().andIdEqualTo(user.getId()).andPasswordEqualTo(user.getPassword());
		List<User> users = userser.selectByExample(uex);
		if(users == null || users.size() < 1){
			return JSONRepUtils.fail(null, "旧密码不匹配", 0);
		}
		user.setPassword(passsordNew);
		userser.updateByPrimaryKeySelective(user);
		String message = JSONRepUtils.success("success", 0);
		return message;
	}
	
	/**
	 * 注销
	 * @param session
	 * @return
	 */
	@RequestMapping("/MLogout")
	public ModelAndView logout(HttpSession session) {
//		String userid = (String)session.getAttribute("userid");
//		User user = new User();
//		user.setIp("");
//		userser.updateByPrimaryKeySelective(user);
		session.invalidate();
		ModelAndView view = new ModelAndView("user/login");
		return view;
	}
	
	
	/**
	 * 用户注册接口，注册后向其邮箱发送注册邮件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userRegist")
	@ResponseBody
	public String regist(User user)throws Exception{
//		if(ConstantResources.GAMEON == 0 || !checkDate()){
			return JSONRepUtils.fail(null, "不在比赛开放时间内", 0);
//		}
		/*
		if(StringUtils.isBlank(user.getId())){  //新用户注册
			//首先检查参数
			if(StringUtils.isBlank(user.getName())){
				throw new ActionException("name");
			}
			if(StringUtils.isBlank(user.getPassword())){
				throw new ActionException("password");
			}
			if(StringUtils.isBlank(user.getSchool())){
				throw new ActionException("school");
			}
			if(StringUtils.isBlank(user.getDepart())){
				throw new ActionException("depart");
			}
			if(StringUtils.isBlank(user.getStuId())){
				throw new ActionException("stuid");
			}
			if(StringUtils.isBlank(user.getEmail())){
				throw new ActionException("email");
			}
			if(StringUtils.isBlank(user.getPhone())){
				throw new ActionException("phone");
			}
			if(user.getEducation() == null){
				throw new ActionException("education");
			}
			//检查学号、手机号、邮箱地址是否有重复的
			UserExample uex = new UserExample();
			UserExample.Criteria cr1 = uex.createCriteria().andPhoneEqualTo(user.getPhone());
			UserExample.Criteria cr2 = uex.createCriteria().andEmailEqualTo(user.getEmail());
//			UserExample.Criteria cr3 = uex.createCriteria().andEmailEqualTo(user.getStuId());
			uex.or(cr1);
			uex.or(cr2);
//			uex.or(cr3);
			List<User> users = userser.selectByExample(uex);
			//如果发现了有重复的
			if(users != null && users.size() > 0){
				return JSONRepUtils.fail(null, "您的手机号或邮箱已被注册", 0);
			}
			//插入数据
			//补充几个字段
			user.setUsername("");
			user.setScore(0);
			user.setRank(0);
			user.setIsV(0);
			user.setIp("");
			user.setCreateTime(new Date());
			user.setModifyTime(user.getCreateTime());
			//插入数据库
			userser.insert(user);
			//token表中插入一条记录，首先删掉已有的未完成的激活token
			UserTokenExample utex = new UserTokenExample();
			utex.createCriteria().andUseridEqualTo(user.getId()).andIsPolishEqualTo(0).andTypeEqualTo(1);
			tokenser.deleteByExample(utex);
			//插入一条新的
			UserToken token = new UserToken();
			token.setType(1);
			token.setCreateTime(new Date());
			token.setIsPolish(0);
			token.setUserid(user.getId());
			tokenser.insert(token);
			//发送激活邮件
//			SimpleMailSender sender = SimpleMailSender.create(user.getEmail());
			StringBuilder sb = new StringBuilder();
			sb.append(registUrl).append("token=").append(token.getId());
			String content = String.format(registFormat,sb.toString());
			if(!MailUtil.sendMail(user.getEmail(), String.format("南京大学信息检索大赛-%s账号激活",user.getName()), content)){
				userser.deleteByPrimaryKey(user.getId());
				return JSONRepUtils.fail(null, "邮件发送失败", 0);
			}
			user.setPassword("");
			user.setId("");
			return JSONRepUtils.success(user, 0);
		}else{   
		   //用户信息修改
			user.setModifyTime(new Date());
			int i = userser.updateByPrimaryKeySelective(user);
			if(i > 0){
				return JSONRepUtils.success("信息修改成功！", 0);
			}else{
				throw new ActionException(MErrorEnum.USER_UPDATE_ERROR);
			}
		}
		*/
	}
	
	/**
	 * 用户激活
	 * @param token
	 * @param isM
	 * @return
	 */
	@RequestMapping("/active")
	@ResponseBody
	public String active(String token, Integer isM){
		String result;
		String userid = checkToken(token);
		if(StringUtils.isNotBlank(userid)){
			result = JSONRepUtils.success("激活成功", 0);
			UserToken t = new UserToken();
			t.setId(token);
			t.setIsPolish(1);
			tokenser.updateByPrimaryKeySelective(t);
			//再更新用户信息
			User user = new User();
			user.setIsV(1);
			user.setId(userid);
			int i  = userser.updateByPrimaryKeySelective(user);
			if(i > 0){
				result = JSONRepUtils.success("激活成功", 0);
			}else{
				result = JSONRepUtils.fail(null, "数据更新失败", 0);
			}
		}else{
			result = JSONRepUtils.fail(null, "激活邮件已经失效", 0);
		}
		return result;
	}
	
	/**
	 * 发送找回密码的邮件
	 * @param session
	 * @param user
	 * @param isM
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/passMail")
	@ResponseBody
	public String passmail(HttpSession session,User user, Integer isM)throws Exception {
		if(StringUtils.isBlank(user.getEmail())) {
			throw new ActionException("email");
		}
		UserExample uex = new UserExample();
		uex.createCriteria().andEmailEqualTo(user.getEmail());
		List<User> users = userser.selectByExample(uex);
		if(users == null || users.size() < 1){
			return JSONRepUtils.fail(null, "对应邮箱不存在", 0);
		}
		User u = users.get(0);
		//向token表中插入一条记录
		UserToken token = new UserToken();
		token.setUserid(u.getId());
		token.setType(2);
		token.setIsPolish(0);
		token.setCreateTime(new Date());
		tokenser.insert(token);
		StringBuilder sb = new StringBuilder();
		sb.append(passUrl).append("token=").append(token.getId());
		String content = String.format(passFormat, u.getName(),sb.toString());
		if(!MailUtil.sendMail(user.getEmail(), String.format("第五届南京大学信息检索大赛-%s的密码找回邮件",user.getName()), content)){
			return JSONRepUtils.fail(null, "邮件发送失败", 0);
		}
		String message = JSONRepUtils.success("success", 0);
		return message;
	}
	
	/**
	 * 验证token
	 * @param session
	 * @param token
	 * @param isM
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tokenCheck")
	@ResponseBody
	public String PassTokenCheck(HttpSession session,String token, Integer isM)throws Exception {
		String result;
		String userid = checkToken(token);
		if(StringUtils.isNotBlank(userid)){
			result = JSONRepUtils.success(userid, 0);
		}else{
			result = JSONRepUtils.fail(null, "邮件已经失效", 0);
		}
		return result;
	}

	/**
	 * 重置密码
	 * @param session
	 * @param user
	 * @param isM
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/passReset")
	@ResponseBody
	public String passReset(HttpSession session,User user, Integer isM,String token)throws Exception {
		String message = "";
//		System.out.println(user.getId());
		int rows = userser.updateByPrimaryKeySelective(user);
		if(rows < 1){
			throw new ActionException(MErrorEnum.USER_UPDATE_ERROR);
		}else{
			message = JSONRepUtils.success(null, 0);
			//更新token表
			UserToken t = new UserToken();
			t.setIsPolish(1);
			t.setId(token);
			tokenser.updateByPrimaryKeySelective(t);
		}
		return message;
	}
	
	
	
	public boolean checkDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
//		today = "2015-05-01";
		return today.compareTo(ConstantResources.STARTTIME) >= 0  && today.compareTo(ConstantResources.OVERTIME) <= 0;
	}
	
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public String checkToken(String token){
		if(StringUtils.isBlank(token)) {
			return "";
		}
		UserToken t = tokenser.selectByPrimaryKey(token);
		Date now = new Date();
//		System.out.println(now.getTime() - t.getCreateTime().getTime());
		if(t== null || t.getIsPolish() == 1 || now.getTime() - t.getCreateTime().getTime() > 24 * 3600 * 1000){
			return "";
		}
		return t.getUserid();
	}
}
