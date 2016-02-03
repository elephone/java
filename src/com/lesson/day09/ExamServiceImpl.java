package com.lesson.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class ExamServiceImpl implements ExamService {
    // 定义类的属性
    
    private EntityContext entityContext;
    //创建user，用于保存user信息
    private User user;

    public EntityContext getEntityContext() {
	return entityContext;
    }

    public void setEntityContext(EntityContext entityContext) {
	this.entityContext = entityContext;
    }
    
    @Override
    public User Login(int id, String pwd) throws LoginException{
       User user = entityContext.findUserByID(id);
       if(user == null){
	   throw new LoginException("此用户不存在");
       }
       if(!user.getPassword().equals(pwd)){
	   throw new LoginException("密码错误");
       }
       this.user = user;
        return user;
    }
    
    //生成考题,返回考试信息对象
    public  PaperExam start(){
	//生成考题
	CreatePaper();
	
	//返回考试信息对象
	return GetPaperExam();
    }
    //创建一个集合用于保存生成的试卷上的考题
    //生成考题,存储到时间paper集合中
    private List<PaperQuestion> paper = new ArrayList<PaperQuestion>();
    private void CreatePaper(){
	//每个难度任选2到
	Random random = new Random();
	
	
	//定义题号
	int index = 0;
	for(int i=Question.LEVEL_1; i<=Question.LEVEL_10; i++){
	    //循环取出每一个难易度的集合,
	    List<Question> list = entityContext.findQuestionByLevel(i);
	    //随机从考题集合或者能够抽取两道题
	    Question q1 = list.remove(random.nextInt(list.size()));
	    Question q2 = list.remove(random.nextInt(list.size()));
	    
	    //将question对象装换成paperQuestion 对象
	    //将paperQuestion 对象放入paper集合中
	    
	    PaperQuestion pq1 = new  PaperQuestion(q1, ++index, new ArrayList<Integer>());
	    PaperQuestion pq2 = new PaperQuestion(q2, ++index, new ArrayList<Integer>());
	    
	    paper.add(pq1);
	    paper.add(pq2);
	     
	    
	    
	}
    }
    
  //生成考试信息的方法
    public PaperExam GetPaperExam(){
	PaperExam paperExam = new PaperExam();
	paperExam.setUser(user);
	paperExam.setTitle(entityContext.getTitle());
	paperExam.setTimeLinit(entityContext.getTimeLimit());
	paperExam.setTimeLinit(paper.size());
	
	return paperExam;
	
    }
    
    //根据Papaer结合中下标获取试卷上的一道题
    public PaperQuestion getQuestion(int index){
	return paper.get(index);
    }
    
  //根据考题题号,保存用户答案
    public void saveUserAnswers(int questionID,List<Integer> userAnswers){
	//根据考题下标,从paer集合中获取对应考题paperquestion
	//将用户的答案,设置到paperquestion的useranswers属性下
	PaperQuestion paperQuestion = paper.get(questionID-1);
	paperQuestion.setUserAnswers(userAnswers);
	
	
    }
  //考试结束,判断用户答案和正确答案,最终返回用户得分,
    public int   getScore(){
	//循环Paper中的每一题,判断用户的答案与正确答案,
	//若答案正确,将本题的得分累加到总分中,返回分数
	int score = 0;
	for(PaperQuestion paperQuestion:paper){
	     if(paperQuestion.getQuestion().getAnswer().equals(paperQuestion.getUserAnswers())){//题库中的题有正确答案
		 score+= paperQuestion.getQuestion().getScore();
	     }  
	}
	return score;
    }
}
