package com.im.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.base.dialect.PaginationSupport;
import com.im.dao.model.Answer;
import com.im.dao.model.AnswerDetail;
import com.im.dao.model.AnswerDetailExample;
import com.im.dao.model.AnswerExample;
import com.im.dao.model.NextQuestion;
import com.im.dao.model.Question;
import com.im.dao.model.User;
import com.im.dao.model.UserExample;
import com.im.util.*;
import com.im.exception.MErrorEnum;
import com.im.service.AnswerDetailService;
import com.im.service.AnswerService;
import com.im.service.QuestionService;
import com.im.service.UserService;
import com.im.util.ConstantResources;
import com.im.util.MyStringUtils;

@Controller
public class GameAction {
	
	@Autowired
	private QuestionService qser;
	
	@Autowired
	private AnswerService anser;
	
	@Autowired
	private AnswerDetailService adeser;
	
	@Autowired
	private UserService userser;
	
	private static Logger logger = Logger.getLogger(GameAction.class);
	
	/**
	 * 进入比赛首页
	 * @return
	 */
	@RequestMapping("/game")
	public ModelAndView game(HttpSession session)throws Exception{
		String userid = (String)session.getAttribute("userid");
		String name = (String)session.getAttribute("name");
		int totalQuestion = 15;
		if(ConstantResources.LEVEL == 2){
			totalQuestion = 6;
		}
		if(StringUtils.isBlank(userid)){
			ModelAndView view = new ModelAndView("user/login");
			return view;
		}
		ModelAndView view = new ModelAndView("game/gameindex");
		view.addObject("tag", "0");
		//首先检查是否已经选好题目了
		AnswerExample aex = new AnswerExample();
		aex.createCriteria().andUseridEqualTo(userid).andRankEqualTo(ConstantResources.LEVEL);
		List<Answer> answers = anser.selectByExample(aex);
		Map<String,String> map = new HashMap<String,String>();
		//如果还没有筛选出题目，那么就为其选择题目
		//ids用来保存这个用户已经做过的题目列表
		StringBuilder sb = new StringBuilder();
		//说明这个阶段该用户还没有做过题目
		if(answers == null || answers.size() < ConstantResources.CHANCE){
			answers = new ArrayList<Answer>();
			//如果是复赛的话得先拿到初赛时做过的题目
//			if(ConstantResources.LEVEL == 2){
//				aex.clear();
//				aex.createCriteria().andUseridEqualTo(userid).andRankEqualTo(1);
//				List<Answer> answersRoundOne = anser.selectByExample(aex);
//				for(Answer answer : answersRoundOne){
//					sb.append(answer.getQuestions() + ",");
//				}
//			}
			//有几次机会就选几次题目
			for(int i = 1; i <= ConstantResources.CHANCE; i++){
				StringBuilder qlists = new StringBuilder();
				//首先插入一条答题总表数据
				Answer ans = new Answer();
				ans.setUserid(userid);
				ans.setTimeLeft(ConstantResources.TIME);
				ans.setCreateTime(new Date());
				ans.setModifyTime(ans.getCreateTime());
				ans.setScore(0);
				ans.setRank(ConstantResources.LEVEL);
				ans.setName(name);
				ans.setIsOver(0);
				ans.setNowNumber(1);
				ans.setTimes(i);
				ans.setAnsTimes(0);
				ans.setHasRead(0);
				ans.setIsChecked(0);
				//插入数据库
				anser.insert(ans);
				
				//每一次选题之前都要保证不要出现已经选择的题目
//				System.out.println(sb.toString());
				if(sb.length() > 0){
					map.put("list", sb.substring(0, sb.length()-1));
				}
				List<Question> questions = null;
				//如果是初赛阶段
				if(ConstantResources.LEVEL == 1){
					questions = qser.selectForRoundOne(map);
					//调整问题的领域比例问题
					//ajustQuestionArea(questions,Arrays.asList(sb.substring(0, sb.length()>0 ? sb.length()-1:0).split(",")));
//					Collections.sort(questions, new Comparator<Question>() {
//						public int compare(Question o1, Question o2) {
//							// TODO Auto-generated method stub
//							return o1.getRank() - o2.getRank();
//						}
//					});
				}else{
					questions = qser.selectForRoundTwo(map);
				}
				totalQuestion = questions.size();
			
				Date now = new Date();
				for(int k = 0 ; k < questions.size() ; k++){
					Question q = questions.get(k);
					qlists.append(q.getId()+",");
					//向答题详情表中插入每一条答题记录
					AnswerDetail ansdetail = new AnswerDetail();
					ansdetail.setPid(ans.getId());
					ansdetail.setIsOver(0);
					ansdetail.setQuesionId(q.getId());
					ansdetail.setQuestionKey(q.getAnswer());
					ansdetail.setRank(k+1);
					ansdetail.setQuestionRank(q.getRank());
					ansdetail.setScore(0);
					ansdetail.setCreateTime(now);
					ansdetail.setModifyTime(now);
					ansdetail.setQuestionAns("");
					ansdetail.setIsCheck(0);
					adeser.insert(ansdetail);
				}
				//
				ans.setQuestions(qlists.substring(0, qlists.length()-1));
				anser.updateByPrimaryKeySelective(ans);
				sb.append(qlists);
				answers.add(ans);
			}
		}else{
			for(int i = 0 ; i < answers.size() ; i++){
				Answer ans = answers.get(i);
//				System.out.println(ans.getIsChecked());
				//此时表示题目已经答完了，需要更新其状态
				if(ans.getAnsTimes() >= ConstantResources.MAXTIMES){
					updateOverStatus((String)session.getAttribute("userid"), ans,false);
				}
			}
		}
		view.addObject("level",ConstantResources.LEVEL);
		view.addObject("answers", answers);
		view.addObject("totalQuestion", totalQuestion);
		return view;
	}
	
	
	/**
	 * 总是显示当前最新的未答的题目
	 * @return
	 */
	@RequestMapping("/answer")
	public ModelAndView answer(HttpSession session,Integer id){
		String userid = (String)session.getAttribute("userid");
		ModelAndView view = new ModelAndView("game/answer");
		Answer answer = null;
		if(id != null){
			answer = anser.selectByPrimaryKey(id);
		}else{
			AnswerExample aex = new AnswerExample();
			aex.createCriteria().andUseridEqualTo(userid).andIsOverEqualTo(0);
			aex.setOrderByClause("times ASC");
			List<Answer> lists = anser.selectByExample(aex);
			answer = lists.get(0);
			
		}
		if(answer == null || !userid.equals(answer.getUserid())){
			return new ModelAndView("game/gameindex");
		}
		//判断是否阅读过，如果还没阅读过就更新
		if(answer.getHasRead() == 0){
			answer.setHasRead(1);
			anser.updateByPrimaryKeySelective(answer);
		}
		//判断当前已经答题的次数，并更新
		int maxTimes = answer.getAnsTimes();
		if(maxTimes >= ConstantResources.MAXTIMES){
			view = new ModelAndView("game/gameindex");
			return view;
		}else{
			answer.setAnsTimes(answer.getAnsTimes() + 1);
			anser.updateByPrimaryKeySelective(answer);
		}
		view.addObject("maxTimes",maxTimes);
		view.addObject("answer",answer);
		session.setAttribute("pid", answer.getId());
		AnswerDetailExample adex = new AnswerDetailExample();
		adex.createCriteria().andPidEqualTo(answer.getId()).andIsOverEqualTo(0);
		adex.setOrderByClause("rank ASC");
		List<AnswerDetail> details = adeser.selectByExample(adex);
		//说明这个已经回答完了
		if(details == null || details.size() < 1){
			view = new ModelAndView("redirect:game.do");
			return view;
		}
		Question q = qser.selectByPrimaryKey(details.get(0).getQuesionId());
//		System.out.println(JSONUtils.serialize(q));
		view.addObject("level",ConstantResources.LEVEL);
		view.addObject("detail", details.get(0));
		view.addObject("question", q);
		return view;
	}
	
	@RequestMapping("/answerQues")
	@ResponseBody
	public String answerQues(String id,Integer pid,Integer type,String answer,Integer timeLeft,HttpSession session) throws Exception{
		String result = "";
//		System.out.println("答案是---" + answer);
		AnswerDetail detail = adeser.selectByPrimaryKey(id);
		if(detail == null){
			throw new ActionException(MErrorEnum.QUESTION_NOT_EXISTS);
		}
		boolean hasAnswered = false;
		//检查这一题是不是做过了
		if(detail.getIsOver() == 1){
			hasAnswered = true;
//			result = JSONRepUtils.fail(null, "该题目您已经答过", 0);
		}else{
			//只有单选题和多选题才计算分数
			if(type != 3){
				int score = calScore(detail, answer);
				detail.setScore(score);
			}
//			System.out.println("的风湿" + score + answer.toString() + detail.getQuestionKey());
			detail.setQuestionAns(MyStringUtils.replaceColon(answer));
			detail.setIsOver(1);
			detail.setModifyTime(new Date());
			adeser.updateByPrimaryKeySelective(detail);
		}
		//再去选择下一题
		AnswerDetailExample adex = new AnswerDetailExample();
		adex.createCriteria().andPidEqualTo(detail.getPid()).andRankEqualTo(detail.getRank() + 1);
		List<AnswerDetail> answered = adeser.selectByExample(adex);
		//此时说明已经回答完了，那么就需要
		AnswerDetail next = null;
		//更新剩余时间
		Answer ans = new Answer();
		ans.setId(pid);
		ans.setModifyTime(new Date());
		ans.setTimeLeft(timeLeft <= 0 ? 0 : timeLeft);
		ans.setNowNumber(detail.getRank()+1);
		anser.updateByPrimaryKeySelective(ans);
		//返回下一题或者计算总分
		if(answered == null || answered.size() < 1 || timeLeft <= 0){
			ans = anser.selectByPrimaryKey(pid);
			updateOverStatus((String)session.getAttribute("userid"), ans,false);
			result = JSONRepUtils.success(ans, 0);
		}else{
			next = answered.get(0);
			next.setQuestionKey("");
			Question q = qser.selectByPrimaryKey(next.getQuesionId());
			q.setAnswer("");
			detail.setQuestionKey("");
			detail.setScore(0);
			if(hasAnswered){
				next.setLastStatus(1);
			}
			NextQuestion ques = new NextQuestion();
			ques.setQuestion(q);
			ques.setDetail(next);
			result = JSONRepUtils.success(ques, 0);
		}
		return result;
	}
	
	@RequestMapping("/check")
	@ResponseBody
	public String test()throws Exception{
		UserExample uex = new UserExample();
		uex.createCriteria().andIsVEqualTo(2);
		List<User> users = userser.selectByExample(uex);
		for(int i = 0 ; i < users.size() ; i++){
			User u = users.get(i);
			AnswerExample aex = new AnswerExample();
			aex.createCriteria().andRankEqualTo(2).andUseridEqualTo(u.getId());
			List<Answer> ans = anser.selectByExample(aex);
			Answer f = null;
			if(ans.size() == 2){
				if(ans.get(0).getScore() > ans.get(1).getScore()){
					f = ans.get(0);
				}else{
					f =ans.get(1);
				}
			}else if(ans.size() == 1){
				f = ans.get(0);
			}
			if(f == null){
				System.out.println("***" +u.getName());
				
			}else{
			  //System.out.println((i+1)+"---"+f.getName() + "----" + f.getScore()+"---"+f.getTimeLeft());
				u.setScore(f.getScore());
				u.setRank(3600-f.getTimeLeft());
				userser.updateByPrimaryKeySelective(u);
			}
		}
		
		
		return "dd";
	}
	
	@RequestMapping("/checkAnswer")
	@ResponseBody
	public String checkAnswer(HttpSession session,String id1, String score1, String id2, String score2,Integer pid) throws Exception{
		//首先更新两个答题详情
		updateAnswerDetailOver(id1, Integer.parseInt(score1));
		//updateAnswerDetailOver(id2, Integer.parseInt(score2));
		//再更新回答表
		Answer ans = anser.selectByPrimaryKey(pid);
		if(ans != null){
			updateOverStatus(ans.getUserid(), ans, true);
		}
		return "{\"success\":true}";
	}
	
	@RequestMapping("/syschroTime")
	@ResponseBody
	public String synTime(Integer id,Integer timeLeft) throws Exception{
		String result = "";
		int i = 0;
		//System.out.println(id + "  " + timeLeft);
		if(id != null && timeLeft != null){
			Answer ans = new Answer();
			ans.setId(id);
			ans.setTimeLeft(timeLeft);
			i = anser.updateByPrimaryKeySelective(ans);
		}
		if(i > 0){
			result = JSONRepUtils.success("success", 0);
		}else{
			result = JSONRepUtils.fail("fail", "", 0);
		}
		return result;
	}
	
	@RequestMapping("/rank")
	public ModelAndView rank(HttpSession session,Integer page, Integer limit){
		if(page == null)
			page = 1;
		if(limit == null)
			limit = 20;
		if(page >10)
			page = 10;
		ModelAndView view = new ModelAndView("game/rank");
		UserExample uex = new UserExample();
		uex.createCriteria().andIsVEqualTo(ConstantResources.LEVEL);
		uex.setOrderByClause("score DESC ,rank ASC,create_time ASC");
		PaginationSupport.setContext(page, limit, null);
		List<User> users = userser.selectByExample(uex);
		view.addObject("users",users);
		int count = PaginationSupport.getContext().getTotalCount();
		view.addObject("totalPage",count / limit + 1);
		view.addObject("total", count);
		view.addObject("page", page);
		view.addObject("limit", limit);
		view.addObject("module", "rank.do");
		return view;
	}
	
	@RequestMapping("/tips")
	public ModelAndView tips(Integer id){
		ModelAndView view = new ModelAndView("game/tips");
		view.addObject("id",id);
		return view;
	}
	
	public void updateAnswerDetailOver(String id, Integer score){
		AnswerDetail detail = new AnswerDetail();
		detail.setId(id);
		detail.setIsOver(1);
		detail.setModifyTime(new Date());
		detail.setIsCheck(1);
		detail.setScore(score);
		adeser.updateByPrimaryKeySelective(detail);	
	}
	
	public void updateOverStatus(String userid,Answer ans,boolean levelTwoShouldUpdate){
//		if(ans.getRank() != 2 || levelTwoShouldUpdate){
		if(true){
			int totalScore = 0;
			totalScore = calTotalScore(ans.getId(), ans.getTimeLeft());
//			if(ans.getRank() == 1){
//				totalScore = calTotalScore(ans.getId(), ans.getTimeLeft());
//			}else{
//				totalScore = calTotalScoreForRoundTwo(ans.getId(), ans.getTimeLeft());
//			}
			ans.setScore(totalScore);
			//先去更新用户的最终得分表
			User user = new User();
			user.setId(userid);
			user.setRank(ConstantResources.TIME-ans.getTimeLeft());
			user.setScore(totalScore);
			userser.updateSocre(user);
		}
		if(levelTwoShouldUpdate){
			ans.setIsChecked(1);
		}
//		ans.setIsChecked(1);
		ans.setIsOver(1);
		ans.setNowNumber(0);
		ans.setModifyTime(new Date());
		anser.updateByPrimaryKeySelective(ans);
	}
	
	
	
	public int calScore(AnswerDetail detail, String answer){
		int result = 0;
		if(detail.getQuestionKey().equals(answer)){
//			if(ConstantResources.LEVEL == 1){
				switch(detail.getQuestionRank()){
				case 1:
					result = 5;
					break;
				case 2:
					result = 10;
					break;
				case 3:
					result = 10;
					break;
				case 4:
					result = 15;
					break;
				case 5:
					result = 15;
					break;
				default:
					result = 5;
				}
//			}
		}
		return result;
	}
	
	public int calTotalScore(int answerId,int timeLeft){
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("pid", answerId);
		int total = adeser.getTotalScore(map);
		int minute = timeLeft / 60;
		int timeScore = 0;
		if(minute >= 0 && minute <= 10){
			timeScore = 2;
		}else if(minute > 10 && minute <= 20){
			timeScore = 5;
		}else if(minute > 20 && minute <= 30){
			timeScore = 8;
		}else if(minute > 30 && minute <= 40){
			timeScore = 10;
		}else if(minute > 40 && minute <= 58){
			timeScore = 15;
		}else{
			timeScore = 0;
		}
		//
		Answer answer = anser.selectByPrimaryKey(answerId);
		if(answer.getRank() == 1){
			return (10 + total )* 17 / 28 + timeScore;
		}else{
			return (10 + total )* 17 / 25 + timeScore;
		}
		
	}
	
	public int calTotalScoreForRoundTwo(int answerId,int timeLeft){
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("pid", answerId);
		int total = adeser.getTotalScore(map);
		return total;
	}
	
	
	
	public void ajustQuestionArea(List<Question> ques,List<String> qList){
		getQuestionAreas(ques,qList);
	}
	
	public Map<Integer,Integer> getQuestionAreas(List<Question> list,List<String> qList){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		Map<Integer,ArrayList<Question>> m = new HashMap<Integer,ArrayList<Question>>();
		m.put(1, new ArrayList<Question>());
		m.put(2, new ArrayList<Question>());
		m.put(3, new ArrayList<Question>());
		m.put(4, new ArrayList<Question>());
		m.put(5, new ArrayList<Question>());
		map.put(1, 0);
		map.put(2, 0);
		map.put(3, 0);
		map.put(4, 0);
		map.put(5, 0);
		for(Question q : list){
			m.get(q.getArea()).add(q);
//			m.put(q.getArea(), m.get(q.getArea()).add(q));
		}

		Iterator<Map.Entry<Integer, ArrayList<Question>>> itr = m.entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry<Integer,  ArrayList<Question>> entry = (Map.Entry<Integer, ArrayList<Question>>)itr.next();
			map.put(entry.getKey(), entry.getValue().size() - getAreaCountShould(entry.getKey()));
			System.out.print(entry.getKey() + ": " + entry.getValue().size()+"  ||   ");
		}
		System.out.println();
		Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>)it.next();			
			//System.out.print(entry.getKey() + ": " + entry.getValue()+"  ||   ");
		}
		System.out.println();
		return map;
	}
	
	
	
	public int getAreaCountShould(int area){
		int result = 0;
		switch(area){
		case 1:
		case 2:
		case 4:
			result = 3;
			break;
		case 3:
			result = 4;
			break;
		case 5:
			result = 2;
			break;
		default:
			result = 0;	
		}
		return result;
	}
	
	
	
	
}
