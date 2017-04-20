package com.im.daoEx;

import java.util.List;
import java.util.Map;

import com.im.dao.model.Question;

public interface QuestionMapperEx {
	List<Question> selectForRoundOne(Map<?,?> map);
	List<Question> selectForRoundTwo(Map<?,?> map);
	Question selectByAreaAndRank(Map<?,?> map);
}
