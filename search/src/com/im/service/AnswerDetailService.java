package com.im.service;

import com.im.dao.AnswerDetailMapper;
import com.im.dao.model.AnswerDetail;
import com.im.dao.model.AnswerDetailExample;
import com.im.daoEx.AnswerDetailMapperEx;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerDetailService {
    @Autowired
    private AnswerDetailMapper answerDetailMapper;
    
    @Autowired
    private AnswerDetailMapperEx answerDetailMapperEx;

    private static final Logger logger = Logger.getLogger(AnswerDetailService.class);

    public int countByExample(AnswerDetailExample example) {
        return this.answerDetailMapper.countByExample(example);
    }

    public AnswerDetail selectByPrimaryKey(String id) {
        return this.answerDetailMapper.selectByPrimaryKey(id);
    }

    public List<AnswerDetail> selectByExample(AnswerDetailExample example) {
        return this.answerDetailMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.answerDetailMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(AnswerDetail record) {
        return this.answerDetailMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(AnswerDetail record) {
        return this.answerDetailMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(AnswerDetailExample example) {
        return this.answerDetailMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(AnswerDetail record, AnswerDetailExample example) {
        return this.answerDetailMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(AnswerDetail record, AnswerDetailExample example) {
        return this.answerDetailMapper.updateByExample(record, example);
    }

    public int insert(AnswerDetail record) {
        return this.answerDetailMapper.insert(record);
    }

    public int insertSelective(AnswerDetail record) {
        return this.answerDetailMapper.insertSelective(record);
    }
    
    public int getTotalScore(Map<?,?> map){
    	return this.answerDetailMapperEx.getTotalScore(map);
    }
}