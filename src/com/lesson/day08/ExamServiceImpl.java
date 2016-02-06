package com.lesson.day08;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class ExamServiceImpl implements ExamService {
    // �����������
    
    private EntityContext entityContext;
    //����user�����ڱ���user��Ϣ
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
	   throw new LoginException("���û�������");
       }
       if(!user.getPassword().equals(pwd)){
	   throw new LoginException("�������");
       }
       this.user = user;
        return user;
    }
    
    //���ɿ���,���ؿ�����Ϣ����
    public  PaperExam start(){
	//���ɿ���
	CreatePaper();
	
	//���ؿ�����Ϣ����
	return GetPaperExam();
    }
    //����һ���������ڱ������ɵ��Ծ��ϵĿ���
    //���ɿ���,�洢��ʱ��paper������
    private List<PaperQuestion> paper = new ArrayList<PaperQuestion>();
    private void CreatePaper(){
	//ÿ���Ѷ���ѡ2��
	Random random = new Random();
	
	
	//�������
	int index = 0;
	for(int i=Question.LEVEL_1; i<=Question.LEVEL_10; i++){
	    //ѭ��ȡ��ÿһ�����׶ȵļ���,
	    List<Question> list = entityContext.findQuestionByLevel(i);
	    //����ӿ��⼯�ϻ����ܹ���ȡ������
	    Question q1 = list.remove(random.nextInt(list.size()));
	    Question q2 = list.remove(random.nextInt(list.size()));
	    
	    //��question����װ����paperQuestion ����
	    //��paperQuestion �������paper������
	    
	    PaperQuestion pq1 = new  PaperQuestion(q1, ++index, new ArrayList<Integer>());
	    PaperQuestion pq2 = new PaperQuestion(q2, ++index, new ArrayList<Integer>());
	    
	    paper.add(pq1);
	    paper.add(pq2);
	     
	    
	    
	}
    }
    
  //���ɿ�����Ϣ�ķ���
    public PaperExam GetPaperExam(){
	PaperExam paperExam = new PaperExam();
	paperExam.setUser(user);
	paperExam.setTitle(entityContext.getTitle());
	paperExam.setTimeLinit(entityContext.getTimeLimit());
	paperExam.setTimeLinit(paper.size());
	
	return paperExam;
	
    }
    
    //����Papaer������±��ȡ�Ծ��ϵ�һ����
    public PaperQuestion getQuestion(int index){
	return paper.get(index);
    }
}
