package com.lesson.day09;
//考试信息类,包含考生 ,考试时间, 考试科目,考题数量
public class PaperExam {
    private User user;//考生
    private int timeLinit;//考试时间
    private String title;//考试科目
    private int questionCount=0;//考题数量
    
    public  PaperExam(){
    }
    
    public PaperExam(User user, int timeLinit, String title, int questionCount) {
	this.user = user;
	this.timeLinit = timeLinit;
	this.title = title;
	this.questionCount = questionCount;
    }

    //显示考试信息到examframe的jLabel中
    public String toString(){
	if(user == null){
	    return "";
	}else{
	    return "姓名:"+user.getName()+" 编号:"+user.getId()+" 考试科目:"+title+" 考试时间:"+timeLinit+" 考题数量:"+questionCount;
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
