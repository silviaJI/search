package com.im.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnswerDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnswerDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andQuesionIdIsNull() {
            addCriterion("quesion_id is null");
            return (Criteria) this;
        }

        public Criteria andQuesionIdIsNotNull() {
            addCriterion("quesion_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuesionIdEqualTo(Integer value) {
            addCriterion("quesion_id =", value, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdNotEqualTo(Integer value) {
            addCriterion("quesion_id <>", value, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdGreaterThan(Integer value) {
            addCriterion("quesion_id >", value, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("quesion_id >=", value, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdLessThan(Integer value) {
            addCriterion("quesion_id <", value, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdLessThanOrEqualTo(Integer value) {
            addCriterion("quesion_id <=", value, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdIn(List<Integer> values) {
            addCriterion("quesion_id in", values, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdNotIn(List<Integer> values) {
            addCriterion("quesion_id not in", values, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdBetween(Integer value1, Integer value2) {
            addCriterion("quesion_id between", value1, value2, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuesionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("quesion_id not between", value1, value2, "quesionId");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyIsNull() {
            addCriterion("question_key is null");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyIsNotNull() {
            addCriterion("question_key is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyEqualTo(String value) {
            addCriterion("question_key =", value, "questionKey");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyNotEqualTo(String value) {
            addCriterion("question_key <>", value, "questionKey");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyGreaterThan(String value) {
            addCriterion("question_key >", value, "questionKey");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyGreaterThanOrEqualTo(String value) {
            addCriterion("question_key >=", value, "questionKey");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyLessThan(String value) {
            addCriterion("question_key <", value, "questionKey");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyLessThanOrEqualTo(String value) {
            addCriterion("question_key <=", value, "questionKey");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyLike(String value) {
            addCriterion("question_key like", value, "questionKey");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyNotLike(String value) {
            addCriterion("question_key not like", value, "questionKey");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyIn(List<String> values) {
            addCriterion("question_key in", values, "questionKey");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyNotIn(List<String> values) {
            addCriterion("question_key not in", values, "questionKey");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyBetween(String value1, String value2) {
            addCriterion("question_key between", value1, value2, "questionKey");
            return (Criteria) this;
        }

        public Criteria andQuestionKeyNotBetween(String value1, String value2) {
            addCriterion("question_key not between", value1, value2, "questionKey");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andRankIsNull() {
            addCriterion("rank is null");
            return (Criteria) this;
        }

        public Criteria andRankIsNotNull() {
            addCriterion("rank is not null");
            return (Criteria) this;
        }

        public Criteria andRankEqualTo(Integer value) {
            addCriterion("rank =", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotEqualTo(Integer value) {
            addCriterion("rank <>", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThan(Integer value) {
            addCriterion("rank >", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("rank >=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThan(Integer value) {
            addCriterion("rank <", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThanOrEqualTo(Integer value) {
            addCriterion("rank <=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankIn(List<Integer> values) {
            addCriterion("rank in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotIn(List<Integer> values) {
            addCriterion("rank not in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankBetween(Integer value1, Integer value2) {
            addCriterion("rank between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotBetween(Integer value1, Integer value2) {
            addCriterion("rank not between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andIsOverIsNull() {
            addCriterion("is_over is null");
            return (Criteria) this;
        }

        public Criteria andIsOverIsNotNull() {
            addCriterion("is_over is not null");
            return (Criteria) this;
        }

        public Criteria andIsOverEqualTo(Integer value) {
            addCriterion("is_over =", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotEqualTo(Integer value) {
            addCriterion("is_over <>", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverGreaterThan(Integer value) {
            addCriterion("is_over >", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_over >=", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverLessThan(Integer value) {
            addCriterion("is_over <", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverLessThanOrEqualTo(Integer value) {
            addCriterion("is_over <=", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverIn(List<Integer> values) {
            addCriterion("is_over in", values, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotIn(List<Integer> values) {
            addCriterion("is_over not in", values, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverBetween(Integer value1, Integer value2) {
            addCriterion("is_over between", value1, value2, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotBetween(Integer value1, Integer value2) {
            addCriterion("is_over not between", value1, value2, "isOver");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andQuestionRankIsNull() {
            addCriterion("question_rank is null");
            return (Criteria) this;
        }

        public Criteria andQuestionRankIsNotNull() {
            addCriterion("question_rank is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionRankEqualTo(Integer value) {
            addCriterion("question_rank =", value, "questionRank");
            return (Criteria) this;
        }

        public Criteria andQuestionRankNotEqualTo(Integer value) {
            addCriterion("question_rank <>", value, "questionRank");
            return (Criteria) this;
        }

        public Criteria andQuestionRankGreaterThan(Integer value) {
            addCriterion("question_rank >", value, "questionRank");
            return (Criteria) this;
        }

        public Criteria andQuestionRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("question_rank >=", value, "questionRank");
            return (Criteria) this;
        }

        public Criteria andQuestionRankLessThan(Integer value) {
            addCriterion("question_rank <", value, "questionRank");
            return (Criteria) this;
        }

        public Criteria andQuestionRankLessThanOrEqualTo(Integer value) {
            addCriterion("question_rank <=", value, "questionRank");
            return (Criteria) this;
        }

        public Criteria andQuestionRankIn(List<Integer> values) {
            addCriterion("question_rank in", values, "questionRank");
            return (Criteria) this;
        }

        public Criteria andQuestionRankNotIn(List<Integer> values) {
            addCriterion("question_rank not in", values, "questionRank");
            return (Criteria) this;
        }

        public Criteria andQuestionRankBetween(Integer value1, Integer value2) {
            addCriterion("question_rank between", value1, value2, "questionRank");
            return (Criteria) this;
        }

        public Criteria andQuestionRankNotBetween(Integer value1, Integer value2) {
            addCriterion("question_rank not between", value1, value2, "questionRank");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsIsNull() {
            addCriterion("question_ans is null");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsIsNotNull() {
            addCriterion("question_ans is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsEqualTo(String value) {
            addCriterion("question_ans =", value, "questionAns");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsNotEqualTo(String value) {
            addCriterion("question_ans <>", value, "questionAns");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsGreaterThan(String value) {
            addCriterion("question_ans >", value, "questionAns");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsGreaterThanOrEqualTo(String value) {
            addCriterion("question_ans >=", value, "questionAns");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsLessThan(String value) {
            addCriterion("question_ans <", value, "questionAns");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsLessThanOrEqualTo(String value) {
            addCriterion("question_ans <=", value, "questionAns");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsLike(String value) {
            addCriterion("question_ans like", value, "questionAns");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsNotLike(String value) {
            addCriterion("question_ans not like", value, "questionAns");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsIn(List<String> values) {
            addCriterion("question_ans in", values, "questionAns");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsNotIn(List<String> values) {
            addCriterion("question_ans not in", values, "questionAns");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsBetween(String value1, String value2) {
            addCriterion("question_ans between", value1, value2, "questionAns");
            return (Criteria) this;
        }

        public Criteria andQuestionAnsNotBetween(String value1, String value2) {
            addCriterion("question_ans not between", value1, value2, "questionAns");
            return (Criteria) this;
        }

        public Criteria andIsCheckIsNull() {
            addCriterion("is_check is null");
            return (Criteria) this;
        }

        public Criteria andIsCheckIsNotNull() {
            addCriterion("is_check is not null");
            return (Criteria) this;
        }

        public Criteria andIsCheckEqualTo(Integer value) {
            addCriterion("is_check =", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckNotEqualTo(Integer value) {
            addCriterion("is_check <>", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckGreaterThan(Integer value) {
            addCriterion("is_check >", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_check >=", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckLessThan(Integer value) {
            addCriterion("is_check <", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckLessThanOrEqualTo(Integer value) {
            addCriterion("is_check <=", value, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckIn(List<Integer> values) {
            addCriterion("is_check in", values, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckNotIn(List<Integer> values) {
            addCriterion("is_check not in", values, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckBetween(Integer value1, Integer value2) {
            addCriterion("is_check between", value1, value2, "isCheck");
            return (Criteria) this;
        }

        public Criteria andIsCheckNotBetween(Integer value1, Integer value2) {
            addCriterion("is_check not between", value1, value2, "isCheck");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}