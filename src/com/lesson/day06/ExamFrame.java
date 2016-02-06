package com.lesson.day06;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ExamFrame extends JFrame{
    
    private JLabel examMsgLabel;//������Ϣ��JLabel
    private JTextArea quesetionArea;//��ʾ������Ϣ�� �ı���

    private JLabel txt;//��������
    private Option[] options;//�����ѡ����
    private JLabel questionNumber;//��ʾ��ǰ�������
    private JButton previous, next, send;//��һ��,��һ��,����ť
    private JLabel timerLabel;//���Ե���ʱJLabel
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
	next = new JButton("��һ��");
	send = new JButton("����");
	
	panel.add(previous);
	panel.add(next);
	panel.add(send);
	return panel;
    }
}
