package com.lesson.day08;

import java.util.ArrayList;
import java.util.List;
//�Ծ��ϵ�һ������(�Ϳ����е��ⲻͬ),���������Ĵ�,���

public class PaperQuestion {
    //��Ӧ�Ծ�������е�һ��
    private Question question;
    //�Ծ��ϵĿ������
    private int questionIndex;
    //�Ծ��ϵĿ���,������,���弯��(�����Ƕ�ѡ��ѡ
    
    
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
