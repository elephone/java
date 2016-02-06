package com.lesson.day06;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuFrame extends JFrame{
    
    
    private JLabel message;//��ʾ��ӭ��Ϣ��jlabel
    private JButton start;//���Կ�ʼ
    private JButton score;//������ѯ
    private JButton msg;//���Թ���
    private JButton exit;//�����뿪
    
    
    
    public MenuFrame() {
	ShowMe();
    }
    public void ShowMe(){
	this.setTitle("�������߲���");
	this.setSize(600, 400);
	this.setLocationRelativeTo(null);
	//this.setEnabled(false);//���ɱ༭
	
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setContentPane(CreateContentPanel());
    }
    
    //���������е������
    public JPanel CreateContentPanel(){
	JPanel panel = new JPanel(new BorderLayout());
	
	
	
	//����:ͼƬ����
	ImageIcon image = new ImageIcon(this.getClass().getResource("title.png"));
	panel.add(new JLabel(image),BorderLayout.NORTH);
	
	
	//�м��Ż�ӭ��Ϣ��jlabel ��4��ͼƬ��ť
	panel.add(CreateCenterPanel(),BorderLayout.CENTER);
	
	// �ϱ�����ʾ��Ȩ��Ϣ��jlabel
	panel.add(new JLabel("����ʱ���Ƽ�",JLabel.RIGHT),BorderLayout.SOUTH);
	
	
	return panel;
    }
    
    
    //����������ӭ��Ϣjlabel��ͼƬ��ť��jpanel
    private JPanel CreateCenterPanel(){
	JPanel panel = new JPanel(new BorderLayout());
	//������ʾ��ӭ��Ϣ
	message = new JLabel("xxxͬѧ,���!",JLabel.CENTER);
	panel.add(message,BorderLayout.NORTH);
	
	
	//�м����4��ͼƬ
	panel.add(CreateBtnPanel(),BorderLayout.CENTER);
	
	
	
	return panel;
    }
    
    
    //��������4���˵���ť��jpanel
    private  JPanel CreateBtnPanel(){
	JPanel panel = new JPanel();
	//��װ������ť�ķ���
	start = CreatImgBtn("��ʼ","exam.png");
	score =	CreatImgBtn("����","result.png");
	msg = CreatImgBtn("���Թ���","message.png");
	exit = CreatImgBtn("�뿪","exit.png");
	
	panel.add(start);
	panel.add(score);
	panel.add(msg);
	panel.add(exit);
	return panel;
    }
    
    //����ͼƬ��ť,2������(��ť��ͼƬ������,��ťͼƬ������)
    private JButton CreatImgBtn(String txt,String img){
	
	ImageIcon  image = new ImageIcon(this.getClass().getResource(img));
	JButton btn = new JButton(txt,image);
	//�����ڰ�ť��ˮƽλ��
	btn.setHorizontalTextPosition(JButton.CENTER);
	
	
	//�����ڰ�ť�Ĵ�ֱλ��
	btn.setVerticalTextPosition(JButton.BOTTOM);
	return btn;
    }
}
