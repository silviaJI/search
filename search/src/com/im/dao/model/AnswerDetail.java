package com.im.dao.model;

import java.util.Date;

public class AnswerDetail {
    private String id;

    private Integer pid;

    private Integer quesionId;

    private String questionKey;

    private Integer score;

    private Integer rank;

    private Integer isOver;

    private Date createTime;

    private Integer questionRank;

    private Date modifyTime;

    private String questionAns;

    private Integer isCheck;
    
    private Integer lastStatus;

    public Integer getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(Integer lastStatus) {
		this.lastStatus = lastStatus;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getQuesionId() {
        return quesionId;
    }

    public void setQuesionId(Integer quesionId) {
        this.quesionId = quesionId;
    }

    public String getQuestionKey() {
        return questionKey;
    }

    public void setQuestionKey(String questionKey) {
        this.questionKey = questionKey == null ? null : questionKey.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getIsOver() {
        return isOver;
    }

    public void setIsOver(Integer isOver) {
        this.isOver = isOver;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getQuestionRank() {
        return questionRank;
    }

    public void setQuestionRank(Integer questionRank) {
        this.questionRank = questionRank;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getQuestionAns() {
        return questionAns;
    }

    public void setQuestionAns(String questionAns) {
        this.questionAns = questionAns == null ? null : questionAns.trim();
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }
}