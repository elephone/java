package com.lesson.day09;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.xml.crypto.Data;

public class ExamFrame extends JFrame{
    
    private JLabel examMsgLabel;//������Ϣ��JLabel
    private JTextArea quesetionArea;//��ʾ������Ϣ�� �ı���

    private JLabel txt;//��������
    private Option[] options;//�����ѡ����
    private JLabel questionNumber;//��ʾ��ǰ�������
    private JButton previous, next, send;//��һ��,��һ��,����ť
    private JLabel timerLabel;//���Ե���ʱJLabel
    
    
    private PaperExam paperExam;//������Ϣ
    
    
    private Timer timer;//���Ե���ʱ
    
    
    
    
    private ClientContext clientContext;
    
    public ClientContext getClientContext() {
        return clientContext;
    }
    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }
    //���Դ���
    public ExamFrame() {
	ShowMe();
    }
    public void ShowMe(){
	this.setTitle("���Դ���");
	this.setSize(600, 400);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setContentPane(CreateContentPanel());
	this.setUndecorated(true);
	
    }
    
    public JPanel CreateContentPanel(){
	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(new EmptyBorder(10,0,10,0));//TODO:
	//����,ͼƬ
	ImageIcon image = new ImageIcon(getClass().getResource("exam_title.png"));
	panel.add(new JLabel(image,JLabel.CENTER),BorderLayout.NORTH);
	
	//�м�  :���� ,�Ծ���ʱ��     ����  ��Ŀ��jlabel��ѡ��    ��ѡ��
	panel.add(CreateCenterPanel(),BorderLayout.CENTER);
	
	//����:���  ,3����ť  ,ʣ��ʱ��
	panel.add(CreateSouthPanel(),BorderLayout.SOUTH);
	
	return panel;
    }
    //����������Ϣ,��ɺ�ѡ��,4��ѡ���
    private JPanel CreateCenterPanel(){
	JPanel panel = new JPanel(new BorderLayout());
	
	//����: ���� ,�Ծ���ʱ��  
	
	examMsgLabel = new JLabel("����:xxx ���:10000",JLabel.CENTER);
	panel.add(examMsgLabel,BorderLayout.NORTH);
	
	
	//�м�: һ������������jPanel  �����ɺ�ѡ����ı���
	panel.add(CreateQuestionPanel(),BorderLayout.CENTER);
	
	
	//����:�����������ܰ�ť,�������,����ʣ��ʱ���JPanel
	panel.add(CreateToolsPanel(),BorderLayout.SOUTH);
	
	return panel;
    }
    //һ������������jPanel
    private JScrollPane CreateQuestionPanel(){
	JScrollPane panel =  new JScrollPane();
	panel.setBorder(new TitledBorder("��Ŀ"));
	
	quesetionArea = new JTextArea();//�ı���
	quesetionArea.setText("1.ѧ�ñ��?ddddddddd"
		+ "dddddddddddddddddddddddd\n A.\nB.\nC.\nD.\n");
	
	//���������ʾ
	quesetionArea.setLineWrap(true);
	//�ı��򲻿ɱ༭
	quesetionArea.setEditable(false);
	//note:������ֱ�ӽ��ı�����ӵ�pan��
	//��ǰ��panel�Ǵ���������,�����Ҫ��panel��ע
	//�ı������,���ı�����panelʱ,����ʾ������
	panel.getViewport().add(quesetionArea);
	return panel;
    }
    private JPanel CreateSouthPanel(){
	JPanel panel = new JPanel();
	options = new Option[4];
	for(int i=0; i<options.length; i++){
	    options[i] = new Option(i, (char)('A'+i)+"");
	    panel.add(options[i]);
	}
	return panel;
    }
    class Option extends JCheckBox{
	//�Զ���һ������,���ڼ�¼��ǰѡ������ֵ
	private int value;
	
	public Option(int Value,String msg){
	    super(msg);
	    this.value = value;
	}
    }
    //�����������ܰ�ť,�������,����ʣ��ʱ���JPanel
    private JPanel CreateToolsPanel(){
	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(new EmptyBorder(0, 6, 0, 6));
	//����:��ŵ�JLagel
	questionNumber = new JLabel("��Ŀ:��һ��");
	panel.add(questionNumber,BorderLayout.WEST);
	
	//�м�������ܰ�ť��jpanel
	panel.add(CreateBtnPanel(),BorderLayout.CENTER);
	
	//����:���Ե���ʱ�� JLabel
	timerLabel = new JLabel("ʣ��ʱ��:00:23:00");
	panel.add(timerLabel,BorderLayout.EAST);
	
	return panel;
	
    }
    
    //����3 �����ܰ�ť��jpanel
    private JPanel CreateBtnPanel(){
	JPanel panel =  new JPanel();
	
	previous = new JButton("��һ��");
	previous.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		clientContext.prevQuestion();
		
	    }
	});
	next = new JButton("��һ��");
	next.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		clientContext.nextQuestion();
		
	    }
	});
	send = new JButton("����");
	send.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		  clientContext.examOver();
		
	    }
	});
	panel.add(previous);
	panel.add(next);
	panel.add(send);
	return panel;
    }
    //���¿�����Ϣ�ķ���
    public void updataPaperExam(PaperExam paperExam){
	//��������Ϣ���µ���ʾ������Ϣ��jlabel
	examMsgLabel.setText(paperExam.toString());
	//��paperexam����Ϊ����
	this.paperExam = paperExam;
    }
    //��������µ����Դ��ڵķ���
    public void updataQuestion(PaperQuestion paperQuestion){
	
	//��������Ϣ��ʾ�����Ե��ı�������
	quesetionArea.setText(paperQuestion.toString());
	//����ǰ������Ÿ���
	UpdataQuestionCount(paperQuestion.getQuestionIndex());
	//������һ�����һ�ⰴť�Ƿ�ɵ��
	UpdataButton(paperQuestion.getQuestionIndex());
	//����ǰ��ʾ������û��� �봰���е�4��ѡ���������
	UpdataOptions(paperQuestion.getUserAnswers());
    }
    
    private  void UpdataQuestionCount(int index){
	String msg = "";
	if(index < 10){
	    msg = "��Ŀ:��"+"0"+index+"��";
	}else{
	    msg = "��Ŀ:��"+index+"��";
	}
	questionNumber.setText(msg);
    }
    
    //���ݸ������,������һ�����һ�ⰴť�Ƿ�ɵ��
    
    public void UpdataButton(int questionID){
	//��һ���Ƿ�ɵ��
	previous.setEnabled(questionID != 1);
	//����
	next.setEnabled(questionID != paperExam.getQuestionCount());
    }
    
    //���û��Ĵ��봰����4��ѡ�����
    public void UpdataOptions(List<Integer> userAnswers){
	//ѭ���ж� �ж�ÿһ��operationֵ�ڲ���operation����
	//������,���õ�ǰoptionѡ��
	for(Option option: options){
//	    if(userAnswers.contains(option.value)){
//		option.setSelected(true);
//	    }else{
//		option.setSelected(false);
//	    }
	    option.setSelected(userAnswers.contains(option.value));
	}
    }
    
   
    //��ʼ���Ե���ʱ
    public void StartTimer(){
	timer = new Timer();
	
	int min = paperExam.getTimeLinit();
	final long endTime = System.currentTimeMillis()+min*60*1000;//��ȡ��ǰϵͳʱ��,ȡ�ÿ��Խ���ʱ��
	timer.schedule(new TimerTask() {
	    
	    @Override
	    public void run() {
		long show = endTime - System.currentTimeMillis();
		long h = show/1000/60/60;
		long m = show/1000/60%60;
		long s = show/1000%60;
		
		
		//��ʾ����ʱ��jlable��
		timerLabel.setText("ʣ��ʱ��:"+(h<10?"0"+h:h)+":"+(m<10?"0"+m:m)+":"+(s<10?"0"+s:s));
	    }
	}, 0, 1000);//1000ms
	
	//��ǰ����
	
	//���Խ���,�Զ�����
	timer.schedule(new TimerTask() {
	    
	    @Override
	    public void run() {
		//ǿ��ִ�н���
		clientContext.sendPaper();
//		timer.cancel();
	    }
	},new Date(endTime));
	//ֹͣ���Ե���ʱ
	

    }
    
    
    public void CancelTimer(){
	    timer.cancel();
	}
    //��ȡ���Դ��ڿ����
	public List<Integer> GetUserAnswers(){
	    //�����������ڱ����û��Ĵ�,ѭ���ĸ�ѡ��
	    //��ѡ�ѡ��,��ѡ��operation��vaule����ֵ���뼯����,���ر����û��𰸵ļ���
	    List<Integer> userAnswers = new ArrayList<Integer>();
	    for(Option option:options){
		if(option.isSelected()){
		    userAnswers.add(option.value);
		    
		}
	    }
	    return userAnswers;//��ǰһ����Ĵ��ѱ���¼����
	}
	
    //������������Ϊ��ʾ���ѯ����Ϣ,�����û������Ľ��
    public boolean showConfirm(String msg){
	int val = JOptionPane.showConfirmDialog(this, msg);
	return val == JOptionPane.YES_OPTION;//ȷ������
	
    }
    
    //��������Ϣ��,��ʾ��������
    public void ShowMessage(String msg){
	JOptionPane.showMessageDialog(this, msg);
    }
}
