package com.lesson.day09;

import java.util.List;



public class Test {
    public static void main(String[] args) {
	String fileName = "src/com/lesson/day09/client.properties";
//	LoadProperties load = new LoadProperties(fileName);
//	double e = load.getDouble("TimeLimit");
//	EntityContext entity = new EntityContext(load);
//	
//	User user = entity.findUserByID(1001);
//	System.out.println(user.getName());
//	
//	List<Question> questions = 
//	entity.findQuestionByLevel(5);
//	for(int i=0; i<questions.size(); i++){
//	    System.out.println(questions.get(i));//Ĭ�ϵ���tostring ����
//	}
//	
	//�������и�������еĶ��󴴽�����,
	//���ø��������Ӧ��set����,
	//�������֮��Ĺ�ϵ,֪ͨ���Ʋ㿪ʼ������
	
	LoginFrame loginFrame = new LoginFrame();
	MenuFrame menuFrame = new MenuFrame();
	ExamFrame examFrame = new ExamFrame();
	//�������Ʋ����
	ClientContext clientContext = new ClientContext();
	
	//����ģ�Ͳ����
	LoadProperties loadProperties = new LoadProperties(fileName);
	EntityContext entityContext = new EntityContext(loadProperties);
	
	//ģ�Ͳ�
	ExamService examService = new ExamServiceImpl();
	/*****************��ֵ******************/
	//������ͼ������Ʋ�֮��Ĺ�ϵ
	loginFrame.setClientContext(clientContext);
	menuFrame.setClientContext(clientContext);
	examFrame.setClientContext(clientContext);
	//�������Ʋ�����ͼ��֮��Ĺ�ϵ
	clientContext.setLoginFrame(loginFrame);
	clientContext.setMenuFrame(menuFrame);
	clientContext.setExamFrame(examFrame);
	
	//�������Ʋ���ҵ���֮��Ĺ�ϵ
	clientContext.setExamService(examService);
	
	
	//����ҵ���߼��������ݲ�֮��Ĺ�ϵ
	((ExamServiceImpl)examService).setEntityContext(entityContext);;
	//���ÿ��Ʋ㿪ʼ����
	clientContext.Start();
    }
}
