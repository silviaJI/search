package com.im.dao;

import com.im.dao.model.AnswerDetail;
import com.im.dao.model.AnswerDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnswerDetailMapper {
    int countByExample(AnswerDetailExample example);

    int deleteByExample(AnswerDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(AnswerDetail record);

    int insertSelective(AnswerDetail record);

    List<AnswerDetail> selectByExample(AnswerDetailExample example);

    AnswerDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AnswerDetail record, @Param("example") AnswerDetailExample example);

    int updateByExample(@Param("record") AnswerDetail record, @Param("example") AnswerDetailExample example);

    int updateByPrimaryKeySelective(AnswerDetail record);

    int updateByPrimaryKey(AnswerDetail record);
}