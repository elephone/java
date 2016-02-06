package com.lesson.day09;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame{
    
    
    private JLabel message;//��ʾ��½��Ϣ��jlabel'
    //�˺ź����������
    private JTextField idField;
    private JPasswordField pwdFild;//���������
    
    //���Ʋ�
    private ClientContext clientContext;
    
    
    public ClientContext getClientContext() {
        return clientContext;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public LoginFrame() {//��ʼ����½����Ļ�����Ϣ
	ShowMe();
    }
    
    public void ShowMe(){//��ʾ���ڵĻ�����Ϣ
	this.setTitle("��¼ϵͳ");
	this.setSize(260, 200);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setContentPane(CreateContentPanel());//��Ӵ����ڲ����
	this.setUndecorated(true);
    }
    //����������panel����
    public JPanel CreateContentPanel(){
	//����������,����panelѡȡ����,borderlayout����廮�ֶ���������
	JPanel panel = new JPanel(new BorderLayout());//Ĭ�ϲ���floatLayout
	panel.setBorder(new  EmptyBorder(10, 10, 10, 10));//���߿�,����Ϊ��������,��ʾ������xxx�ľ���
	
	//����,һ��jlabel��ʾ��½ϵͳ��Ϣ
	panel.add(new JLabel("��½ϵͳ",JLabel.CENTER),BorderLayout.NORTH);
	//�м���3��jLabel �� ������ jpanel��
	panel.add(CreateCenterPanel(),BorderLayout.CENTER);
	//�ϱߣ����2����ť��jpanel
	panel.add(CreateBtnPanel(),BorderLayout.SOUTH);
	return panel;
	
	
	
    }
    //����3��jLabel �� ������ jpanel
    private JPanel CreateCenterPanel(){
	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(new EmptyBorder(8,8,8,8));
	
	//���ߣ����2��jpanel�������
	panel.add(createIdOrPwdPanel(), BorderLayout.NORTH);
	
	//�м�:�����ʵ��½��ʾ��Ϣ��jlabel

	message = new JLabel("",JLabel.CENTER);
	panel.add(message,BorderLayout.CENTER);
	
	return panel;
    }
    
    //����jpanel�������˺�jlabel ����jlabel �˺������  ���������
    //��panel�ڲ��ֳɱ��ṹ,2��1��
    //��һ�зŰ����˺ź����������
    //�ڶ��зź������������� �����
    public JPanel createIdOrPwdPanel(){
	JPanel panel = new JPanel(new GridLayout(2, 1, 0, 6));
	
	
	//��һ��
	panel.add(CreateIdPanel());
	
	//�ڶ���
	panel.add(CreatePwdPanel());
	return panel;
    }
    
    public JPanel CreateIdPanel(){
	JPanel panel = new JPanel(new BorderLayout(6,0));
	//���ߴ�š��˺š�jlabel
	panel.add(new JLabel("�˺�:",JLabel.CENTER),BorderLayout.WEST);
	//�м���"�˺�"�����
	idField = new JTextField();//��������
	panel.add(idField,BorderLayout.CENTER);//����
	return panel;
    }
    
    //��������
    public JPanel CreatePwdPanel(){
	
	JPanel panel = new JPanel(new BorderLayout(6,0));
	//���ߴ������ ��jLabel
	panel.add(new JLabel("����:",JLabel.CENTER),BorderLayout.WEST);
	//�м������������
	pwdFild = new JPasswordField();
	panel.add(pwdFild,BorderLayout.CENTER);
	return panel;
    }
    
    //��������2�����ܰ�ť��jpanel
    public JPanel CreateBtnPanel(){
	JPanel panel = new JPanel();//��� Ĭ�Ͼ���
	JButton login = new JButton("��¼");
	login.addActionListener(new  ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		//���û������¼��ť���������ÿ��Ʋ�clientContext
		//��login()������ִ�е�¼����
		clientContext.Login();
	    }
	});//��Ӽ���
	JButton cancel = new JButton("ȡ��");
	cancel.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		System.exit(-1);
		
	    }
	});
	panel.add(login);
	panel.add(cancel);
	return panel;
    }
    
    
    //��ȡ��¼�˺ŵķ���
    public int GetUserId(){
	//��ȡID����������
	String id = idField.getText();
	//��IDת��Ϊint ����
	return Integer.parseInt(id);
    }
    
    //��ȡ��¼����ķ���,��ȡ���������ķ���
    public String GetUserPwd(){
	return new String(pwdFild.getPassword());
    }
    
   //�ڵ�½��������ʾʧ���쳣��Ϣ
    public void showError (String msg){
	//����ʾ�����liabel��ֵ]
	message.setText(msg);
	//��jlabel����ʾ������ɫ��ɺ�ɫ
	message.setForeground(Color.red);
    }
}
