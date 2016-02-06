package com.lesson.day09;

import java.util.List;


//���Ʋ�(mvc),���ฺ����������ҵ����������̿���
//�����еķ���������ҵ��ϸ��,ֵ��עҵ������,Э��V���е����M���е���

public class ClientContext {
    
    
    //����V������Ҫ����
    private LoginFrame loginFrame;
    
    
    
    
    public LoginFrame getLoginFrame() {
        return loginFrame;
    }
    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }
    
    //��½����
    private  ExamService examService; 
    
    private ExamFrame examFrame = new ExamFrame();
    
    
    private PaperQuestion currentQuestion;//��ǰ����,ȷ���л��������̵�����ִ��
    //�����¼���̵ķ���
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
    //�˵�����
    private MenuFrame menuFrame;
   
    public MenuFrame getMenuFrame() {
        return menuFrame;
    }
    public void setMenuFrame(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }
    public void Login(){
	//1.��loginFrame ��ȡ���û������ID,����
	//2.���û������ID�������Բ�������ʽ����exameservice
	//��login()�ķ���,���е�¼��֤
	//3.�����֤��¼��ȷ,����user����,��LoginFrame��������(���ɼ�)
	//��MenuFrame������ʾ
	//4.�����֤��¼����,��ͣ���ڵ�¼����,����ʾʧ��ԭ��
	int id = loginFrame.GetUserId();
	String pwd = loginFrame.GetUserPwd();
	try {
	    //��Id �� pwd ���� ���ݲ��м���Ƿ���ȷ,
	    User user = examService.Login(id, pwd);
	    //��½�ɹ������ص�½����
	    loginFrame.setVisible(false);
	    
	    //�˵�������ʾ��ӭXXX
	    menuFrame.UpdateView(user);
	    //�˵�������ʾ
	    menuFrame.setVisible(true);
	} catch (LoginException le) {
	    loginFrame.showError(le.getMessage());
	}
    }
    

    
    public void Start(){
	//����½������ʾ����
	loginFrame.setVisible(true);
    }
    
    //��ʼ��������
    //����ҵ���߼�,���ɿ���,�����ؿ�����Ϣ
    //����ҵ���߼����ȡ��һ������,��ʾ�����Խ���
    //��������Ϣ����ExamFrame��ʾ
    //���˵���������,���Դ�����ʾ
    
    
    public void  StartExam(){
	
	PaperExam paperExam = examService.start();
	currentQuestion = examService.getQuestion(0);
	examFrame.updataPaperExam(paperExam);
	examFrame.updataQuestion(currentQuestion);
	
	menuFrame.setVisible(false);
	//��ʼ���Ե���ʱ,�˵�������ʧ,���Դ���
	examFrame.StartTimer();
	examFrame.setVisible(true);
    }
    
    
    //�л���һ�������(��Ե�ǰ��˵��
    public void prevQuestion(){
	getQuestion(-1);
    }
    
    //��������
    public void examOver(){
	//��ʾ��,
	//ѯ���û��Ƿ񽻾�,��ȷ����ִ�н�������,
	if(examFrame.showConfirm("ȷ��������?")){
	    sendPaper();
	}else{
	    
	}
    }
    
    
   
  //ʵ�ʽ�������
    public void sendPaper(){
	//���浱ǰ�����û��Ĵ�
	//����ҵ���߼���������Է���
	//����ȡ�÷�,�ڿ��Դ�����ʾ�û��÷�,���Դ�������,��ʾ�˵�����
	int questionID = currentQuestion.getQuestionIndex();
	List<Integer> userAnswers = examFrame.GetUserAnswers();
	
	examService.saveUserAnswers(questionID, userAnswers);
	
	int score = examService.getScore();
	
	examFrame.ShowMessage("��ķ���:"+score);
	menuFrame.setScoreOfAll(score);
	//���ò˵������п�ʼ��ť���ɵ��
	menuFrame.CouldNotExamAgain();
	
	examFrame.setVisible(false);
	menuFrame.setVisible(true);
    }
    
    
    
    //�л���һ�������
    public void nextQuestion(){
	getQuestion(1);
    }
    
    
    //�л��� ������ʵ�����̷���
    private void  getQuestion(int mode){
	//��ȡ��ǰ��������S
	//�û��Ĵ𰸣�����ҵ���߼��㣨examService)���浱ǰ�����û��Ĵ�
	//����mode������һ����һ�����,��ҵ���߼�����ݿ�����±��ȡ����
	//������Ϊ��ǽ����,��������ʾ�ڿ��Խ���
	int currentQuestionID = currentQuestion.getQuestionIndex();//��ǰ���
	List<Integer> userAnswers = examFrame.GetUserAnswers();
	examService.saveUserAnswers(currentQuestion.getQuestionIndex(),userAnswers);
	
	
	currentQuestionID += mode;//�µĵ�ǰ������
	PaperQuestion paperQuestion = examService.getQuestion(currentQuestionID-1);
	currentQuestion = paperQuestion;
	examFrame.updataQuestion(currentQuestion);
	
    }
    
    
    
}
