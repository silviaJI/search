package com.im.service;

import com.im.dao.AnswerMapper;
import com.im.dao.model.Answer;
import com.im.dao.model.AnswerExample;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    private static final Logger logger = Logger.getLogger(AnswerService.class);

    public int countByExample(AnswerExample example) {
        return this.answerMapper.countByExample(example);
    }

    public Answer selectByPrimaryKey(Integer id) {
        return this.answerMapper.selectByPrimaryKey(id);
    }

    public List<Answer> selectByExample(AnswerExample example) {
        return this.answerMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.answerMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Answer record) {
        return this.answerMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Answer record) {
        return this.answerMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(AnswerExample example) {
        return this.answerMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Answer record, AnswerExample example) {
        return this.answerMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Answer record, AnswerExample example) {
        return this.answerMapper.updateByExample(record, example);
    }

    public int insert(Answer record) {
        return this.answerMapper.insert(record);
    }

    public int insertSelective(Answer record) {
        return this.answerMapper.insertSelective(record);
    }
}