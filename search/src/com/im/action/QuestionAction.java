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
import com.im.dao.model.Question;
import com.im.dao.model.QuestionExample;
import com.im.dao.model.QuestionExample.Criteria;
import com.im.service.QuestionService;
import com.im.util.JSONRepUtils;
import com.im.util.MyStringUtils;
@Controller
public class QuestionAction {
	
	@Autowired
	private QuestionService qser;
	
	@RequestMapping("/getQuestion")
	@ResponseBody
	public String findIdQuestion(Integer id){
		Question ques = qser.selectByPrimaryKey(id);
		return JSONRepUtils.success(ques, 0);
	}
	
	
	@RequestMapping("/listQuestion")
	@ResponseBody
	public String list(Page param,Question ques) throws Exception{
		QuestionExample example = new QuestionExample();
		PaginationSupport.setContext(param.getPageStart(), param.getPageSize(), null);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(ques.getContent())){
			criteria.andContentLike("%"+ques.getContent()+"%");
		}
		if(StringUtils.isNotBlank(ques.getBackground())){
			criteria.andBackgroundLike("%"+ques.getBackground()+"%");
		}
		if(ques.getType() != null){
			criteria.andTypeEqualTo(ques.getType());
		}
		if(ques.getRank() != null){
			criteria.andRankEqualTo(ques.getRank());
		}
		if(ques.getArea() != null){
			criteria.andAreaEqualTo(ques.getArea());
		}
		example.setOrderByClause("id ASC,type ASC");
		List<Question> list = qser.selectByExample(example);
		
		return JSONRepUtils.serialize(new Page(PaginationSupport.getContext().getTotalCount(), list), 0);
	}
	
	@RequestMapping("/showlistQuestion")
	public String list() {
		return "question/questionList";
	}
	
	@RequestMapping("/toEditQuestion")
	public String toEdit()throws Exception{
		return "question/questionEdit";
	}
	
	@RequestMapping(value="/saveQuestion",method=RequestMethod.POST)
	@ResponseBody
	public String save(String contextReal,Question question, Integer isTop, HttpSession session)throws Exception{
		//WebPageFileController fileController = new WebPageFileController("bs", "news", "utf8");
		question.setCreateTime(new Date());
		question.setAnswer(question.getAnswer().length() > 10 ? question.getAnswer() :question.getAnswer().toUpperCase());
		question.setContent(MyStringUtils.replaceColon(question.getContent()));
		question.setBackground(MyStringUtils.replaceColon(question.getBackground()));
		question.setOption1(MyStringUtils.replaceColon(question.getOption1()));
		question.setOption2(MyStringUtils.replaceColon(question.getOption2()));
		question.setOption3(MyStringUtils.replaceColon(question.getOption3()));
		question.setOption4(MyStringUtils.replaceColon(question.getOption4()));
		question.setOption5(MyStringUtils.replaceColon(question.getOption5()));
		question.setOption6(MyStringUtils.replaceColon(question.getOption6()));
		try{
			if(question.getId() == null){
				question.setTimes(0);		
				qser.insert(question);
			}else{
				question.setModifyTime(new Date());
				qser.updateByPrimaryKeySelective(question);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "{\"success\":false}";
		}
		return "{\"success\":true}";
	}
	
	@RequestMapping(value="/findByIdQuestion")
	@ResponseBody
	public String findById(Integer id,HttpSession session, Integer isM)throws Exception{		
		Question n = qser.selectByPrimaryKey(id);
		return JSONRepUtils.serialize(n, isM);
	}
	
	@RequestMapping("/delQuestion")
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
	
	@RequestMapping("/topSetQuestion")
	@ResponseBody
	public String topSet(String id,HttpSession session) throws Exception {
		return "{\"success\":true}";
	}
	
	@RequestMapping("/topDelQuestion")
	@ResponseBody
	public String topDel(String id,HttpSession session) throws Exception {
		return "{\"success\":true}";
	}
	
}
