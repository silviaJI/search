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
import com.im.dao.model.Answer;
import com.im.dao.model.AnswerDetail;
import com.im.dao.model.AnswerDetailExample;
import com.im.dao.model.AnswerExample;
import com.im.dao.model.AnswerExample.Criteria;
import com.im.dao.model.Question;
import com.im.dao.model.User;
import com.im.util.*;
import com.im.service.AnswerDetailService;
import com.im.service.AnswerService;
import com.im.service.QuestionService;
@Controller
public class AnswersAction {
	
	@Autowired
	private AnswerService qser;
	
	@Autowired
	private AnswerDetailService adser;
	
	@Autowired
	private QuestionService quetionser;
	
	@RequestMapping("/listAnswer")
	@ResponseBody
	public String list(Page param,Answer answer) throws Exception{
		AnswerExample example = new AnswerExample();
		PaginationSupport.setContext(param.getPageStart(), param.getPageSize(), null);
		Criteria criteria = example.createCriteria();
		//只选择复赛的答题情况
		criteria.andRankEqualTo(2);
		
		if(StringUtils.isNotBlank(answer.getName())){
			criteria.andNameLike("%"+answer.getName()+"%");
		}
		if(answer.getIsOver() != null){
			criteria.andIsOverEqualTo(answer.getIsOver());
		}
		if(answer.getIsChecked() != null){
			criteria.andIsCheckedEqualTo(answer.getIsChecked());
		}
		example.setOrderByClause("create_time DESC");
		List<Answer> list = qser.selectByExample(example);
		for(Answer ans:list){
			ans.setName("xxx");
		}
		return JSONRepUtils.serialize(new Page(PaginationSupport.getContext().getTotalCount(), list), 0);
	}
	
	@RequestMapping("/showlistAnswer")
	public String list() {
		return "answers/answerList";
	}
	
	@RequestMapping("/toEditAnswer")
	public String toEdit()throws Exception{
		return "answers/answerEdit";
	}
	
	@RequestMapping(value="/saveAnswer",method=RequestMethod.POST)
	@ResponseBody
	public String save(String contextReal,Answer answer, Integer isTop, HttpSession session)throws Exception{
		//WebPageFileController fileController = new WebPageFileController("bs", "news", "utf8");
		answer.setCreateTime(new Date());
		try{
			if(answer.getId() == null){
				qser.insert(answer);
			}else{
				answer.setModifyTime(new Date());
				qser.updateByPrimaryKeySelective(answer);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "{\"success\":false}";
		}
		return "{\"success\":true}";
	}
	
	@RequestMapping(value="/findByIdAnswer")
	@ResponseBody
	public String findById(Integer id,HttpSession session, Integer isM)throws Exception{		
		Answer n = qser.selectByPrimaryKey(id);
		String result = "";
		StringBuilder sb = new StringBuilder();
		if(n.getIsOver() == 1){
			AnswerDetailExample adex = new AnswerDetailExample();
			adex.createCriteria().andPidEqualTo(id).andRankEqualTo(6);
			List<AnswerDetail> details = adser.selectByExample(adex);
			//再去拿问题
			for(AnswerDetail detail : details){
				sb.setLength(0);
				Question q = quetionser.selectByPrimaryKey(detail.getQuesionId());
				detail.setScore(q.getPoint());
				detail.setQuestionKey(sb.append("<b>题目背景</b><br/>&nbsp;&nbsp;&nbsp;&nbsp;").append(q.getBackground()).append("<br/><b>题目要求</b>：<br/>&nbsp;&nbsp;&nbsp;&nbsp;").append(q.getContent()).append("<br/><b/>参考答案：</b><br/>&nbsp;&nbsp;&nbsp;&nbsp;<br/>").append(q.getAnswer()).toString());
			}
			result = JSONRepUtils.serialize(details, isM);
		}
		return result;
	}
	
	@RequestMapping("/delAnswer")
	@ResponseBody
	public String del(String id,String url,HttpSession session)throws Exception{
//		WebPageFileController fileController = new WebPageFileController();
		String[] ids = id.split(",");
		int len = ids.length;
		for(int i = 0; i < len; i++) {
			qser.deleteByPrimaryKey(Integer.parseInt(ids[i]));
//			fileController.deleteFile(urls[i]);
		}
		return "{\"success\":true}";
	}	
}
