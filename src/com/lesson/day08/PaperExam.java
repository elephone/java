package com.lesson.day08;
//������Ϣ��,�������� ,����ʱ��, ���Կ�Ŀ,��������
public class PaperExam {
    private User user;//����
    private int timeLinit;//����ʱ��
    private String title;//���Կ�Ŀ
    private int questionCount;//��������
    
    public  PaperExam(){
    }
    
    public PaperExam(User user, int timeLinit, String title, int questionCount) {
	this.user = user;
	this.timeLinit = timeLinit;
	this.title = title;
	this.questionCount = questionCount;
    }

    //��ʾ������Ϣ��examframe��jLabel��
    public String toString(){
	if(user == null){
	    return "";
	}else{
	    return "����:"+user.getName()+" ���:"+user.getId()+" ���Կ�Ŀ:"+title+" ����ʱ��:"+timeLinit+" ��������:"+questionCount;
	}
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public int getTimeLinit() {
        return timeLinit;
    }
    public void setTimeLinit(int timeLinit) {
        this.timeLinit = timeLinit;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getQuestionCount() {
        return questionCount;
    }
    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    
}
