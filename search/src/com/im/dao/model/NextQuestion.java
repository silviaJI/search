package com.im.dao.model;

public class NextQuestion {
	private Question question;
	private String detailId;
	private AnswerDetail detail;
	public AnswerDetail getDetail() {
		return detail;
	}
	public void setDetail(AnswerDetail detail) {
		this.detail = detail;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	

}
