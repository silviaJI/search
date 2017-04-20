package com.im.service;

import com.im.dao.QuestionMapper;
import com.im.dao.model.Question;
import com.im.dao.model.QuestionExample;
import com.im.daoEx.QuestionMapperEx;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    
    @Autowired
    private QuestionMapperEx questionMapperEx;

    private static final Logger logger = Logger.getLogger(QuestionService.class);

    public int countByExample(QuestionExample example) {
        return this.questionMapper.countByExample(example);
    }

    public Question selectByPrimaryKey(Integer id) {
        return this.questionMapper.selectByPrimaryKey(id);
    }

    public List<Question> selectByExample(QuestionExample example) {
        return this.questionMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.questionMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Question record) {
        return this.questionMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Question record) {
        return this.questionMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    public int deleteByExample(QuestionExample example) {
        return this.questionMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Question record, QuestionExample example) {
        return this.questionMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Question record, QuestionExample example) {
        return this.questionMapper.updateByExampleWithBLOBs(record, example);
    }

    public int insert(Question record) {
        return this.questionMapper.insert(record);
    }

    public int insertSelective(Question record) {
        return this.questionMapper.insertSelective(record);
    }
    
    public List<Question> selectForRoundOne(Map<?,?> map){
    	return this.questionMapperEx.selectForRoundOne(map);
    }
    public List<Question> selectForRoundTwo(Map<?,?> map){
    	return this.questionMapperEx.selectForRoundTwo(map);
    }
    
    public Question selectByAreaAndRank(Map<?,?> map){
    	return this.questionMapperEx.selectByAreaAndRank(map);
    }
}