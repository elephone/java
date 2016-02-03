package com.lesson.day08;

import java.util.List;

//控制层(mvc),此类负责管理考试相关业务需求的流程控制
//此类中的方法不处理业务细节,值关注业务流程,协调V层中的类和M层中的类

public class ClientContext {
    
    
    //定义V层中需要的类
    private LoginFrame loginFrame;
    
    
    public LoginFrame getLoginFrame() {
        return loginFrame;
    }
    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }
    
    //登陆测试
    private  ExamService examService; 
    
    private ExamFrame examFrame = new ExamFrame();
    
    
    private PaperQuestion currentQuestion;//当前考题,确保切换考题流程的正常执行
    //定义登录流程的方法
    public ExamService getExamService() {
        return examService;
    }
    public void setExamService(ExamService examService) {

        this.examService = examService;

    }
   
    public ExamFrame getExamFrame() {
        return examFrame;
    }
    public void setExamFrame(ExamFrame examFrame) {
        this.examFrame = examFrame;
    }
    //菜单窗口
    private MenuFrame menuFrame;
   
    public MenuFrame getMenuFrame() {
        return menuFrame;
    }
    public void setMenuFrame(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }
    public void Login(){
	//1.从loginFrame 中取到用户输入的ID,密码
	//2.将用户输入的ID和密码以参数的形式传给exameservice
	//的login()的方法,进行登录验证
	//3.如果验证登录正确,保存user对象,将LoginFrame窗口隐藏(不可见)
	//将MenuFrame窗口显示
	//4.如果验证登录错误,则停留在登录窗口,并提示失败原因
	int id = loginFrame.GetUserId();
	String pwd = loginFrame.GetUserPwd();
	try {
	    //将Id 和 pwd 传到 数据层中检查是否正确,
	    User user = examService.Login(id, pwd);
	    //登陆成功后，隐藏登陆窗口
	    loginFrame.setVisible(false);
	    
	    //菜单窗口显示欢迎XXX
	    menuFrame.UpdateView(user);
	    //菜单窗口显示
	    menuFrame.setVisible(true);
	} catch (LoginException le) {
	    loginFrame.showError(le.getMessage());
	}
    }
    
    
    
    
    
    public void Start(){
	//将登陆窗口显示出来
	loginFrame.setVisible(true);
    }
    
    //开始考试流程
    //调用业务逻辑,生成考题,并返回考试信息
    //调用业务逻辑层获取第一道考题,显示到考试界面
    //将考试信息交给ExamFrame显示
    //将菜单窗口隐藏,考试窗口显示
    
    
    public void  StartExam(){
	PaperExam paperExam = examService.start();
	currentQuestion = examService.getQuestion(0);
	examFrame.updataPaperExam(paperExam);
	examFrame.updataQuestion(currentQuestion);
	
	menuFrame.setVisible(false);
	examFrame.setVisible(true);
    }
    
    
    
   
}
