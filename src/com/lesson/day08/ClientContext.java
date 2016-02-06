package com.lesson.day08;

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
	examFrame.setVisible(true);
    }
    
    
    
   
}
