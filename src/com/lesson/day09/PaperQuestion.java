package com.lesson.day09;

import java.util.ArrayList;
import java.util.List;
//试卷上的一道考题(和考题中的题不同),包含考生的答案,题号

public class PaperQuestion {
    //对应试卷上题库中的一题
    private Question question;
    //试卷上的考题题号
    private int questionIndex;
    //试卷上的考题,考生答案,定义集合(可能是多选或单选
    
    
    private List<Integer> userAnswers = new ArrayList<Integer>();
    
    
    public PaperQuestion() {
	super();
    }
    
    
    public PaperQuestion(Question question, int questionIndex,
	    List<Integer> userAnswers) {
	
	this.question = question;
	this.questionIndex = questionIndex;
	this.userAnswers = userAnswers;
    }
    public String toString(){
	return questionIndex+"."+question.toString();
    }

    

    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
    public int getQuestionIndex() {
        return questionIndex;
    }
    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }
    public List<Integer> getUserAnswers() {
        return userAnswers;
    }
    public void setUserAnswers(List<Integer> userAnswers) {
        this.userAnswers = userAnswers;
    }
    
    
}
