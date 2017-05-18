package com.strangeman.entity;

public class QuestionPreview {
	private int questionNum;
	private String questionOne;
	private int answerOneNum;
	private String questionTwo;
	private int answerTwoNum;
	public QuestionPreview(int questionNum, String questionOne, int answerOneNum, String questionTwo,
			int answerTwoNum) {
		super();
		this.questionNum = questionNum;
		this.questionOne = questionOne;
		this.answerOneNum = answerOneNum;
		this.questionTwo = questionTwo;
		this.answerTwoNum = answerTwoNum;
	}
	public int getQuestionNum() {
		return questionNum;
	}
	public String getQuestionOne() {
		return questionOne;
	}
	public int getAnswerOneNum() {
		return answerOneNum;
	}
	public String getQuestionTwo() {
		return questionTwo;
	}
	public int getAnswerTwoNum() {
		return answerTwoNum;
	}
}
