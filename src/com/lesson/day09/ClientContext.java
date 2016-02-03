package com.lesson.day09;

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
	//开始考试倒计时,菜单窗口消失,考试窗口
	examFrame.StartTimer();
	examFrame.setVisible(true);
    }
    
    
    //切换上一题的流程(针对当前来说）
    public void prevQuestion(){
	getQuestion(-1);
    }
    
    //交卷流程
    public void examOver(){
	//提示框,
	//询问用户是否交卷,若确认则执行交卷流程,
	if(examFrame.showConfirm("确定交卷吗?")){
	    sendPaper();
	}else{
	    
	}
    }
    
    
   
  //实际交卷流程
    public void sendPaper(){
	//保存当前考题用户的答案
	//调用业务逻辑层结束考试方法
	//并获取得分,在考试窗口显示用户得分,考试窗口隐藏,显示菜单窗口
	int questionID = currentQuestion.getQuestionIndex();
	List<Integer> userAnswers = examFrame.GetUserAnswers();
	
	examService.saveUserAnswers(questionID, userAnswers);
	
	int score = examService.getScore();
	
	examFrame.ShowMessage("你的分数:"+score);
	menuFrame.setScoreOfAll(score);
	//设置菜单窗口中开始按钮不可点击
	menuFrame.CouldNotExamAgain();
	
	examFrame.setVisible(false);
	menuFrame.setVisible(true);
    }
    
    
    
    //切换下一题的流程
    public void nextQuestion(){
	getQuestion(1);
    }
    
    
    //切换上 或下体实际流程方法
    private void  getQuestion(int mode){
	//获取当前考题的题号S
	//用户的答案，调用业务逻辑层（examService)保存当前考题用户的答案
	//根据mode计算上一题下一题题号,从业务逻辑层根据考题的下标获取考题
	//并保存为挡墙考题,将考题显示在考试界面
	int currentQuestionID = currentQuestion.getQuestionIndex();//当前题号
	List<Integer> userAnswers = examFrame.GetUserAnswers();
	examService.saveUserAnswers(currentQuestion.getQuestionIndex(),userAnswers);
	
	
	currentQuestionID += mode;//新的当前题的题号
	PaperQuestion paperQuestion = examService.getQuestion(currentQuestionID-1);
	currentQuestion = paperQuestion;
	examFrame.updataQuestion(currentQuestion);
	
    }
    
    
    
}
