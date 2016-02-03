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
//	    System.out.println(questions.get(i));//默认调用tostring 方法
//	}
//	
	//将程序中各个层次中的对象创建出来,
	//调用各个层次相应的set方法,
	//建立层次之间的关系,通知控制层开始的流程
	
	LoginFrame loginFrame = new LoginFrame();
	MenuFrame menuFrame = new MenuFrame();
	ExamFrame examFrame = new ExamFrame();
	//创建控制层对象
	ClientContext clientContext = new ClientContext();
	
	//创建模型层对象
	LoadProperties loadProperties = new LoadProperties(fileName);
	EntityContext entityContext = new EntityContext(loadProperties);
	
	//模型层
	ExamService examService = new ExamServiceImpl();
	/*****************赋值******************/
	//建立视图层域控制层之间的关系
	loginFrame.setClientContext(clientContext);
	menuFrame.setClientContext(clientContext);
	examFrame.setClientContext(clientContext);
	//建立控制层域视图层之间的关系
	clientContext.setLoginFrame(loginFrame);
	clientContext.setMenuFrame(menuFrame);
	clientContext.setExamFrame(examFrame);
	
	//建立控制层与业务层之间的关系
	clientContext.setExamService(examService);
	
	
	//建立业务逻辑层与数据层之间的关系
	((ExamServiceImpl)examService).setEntityContext(entityContext);;
	//调用控制层开始程序
	clientContext.Start();
    }
}
