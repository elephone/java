package com.lesson.day06;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame{
    
    
    private JLabel message;//显示登陆信息的jlabel'
    //账号和密码输入框
    private JTextField idField;
    private JPasswordField pwdFild;//密码输入框
    public LoginFrame() {//初始化登陆界面的基本信息
	ShowMe();
    }
    
    public void ShowMe(){//显示窗口的基本信息
	this.setTitle("登录系统");
	this.setSize(260, 200);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setContentPane(CreateContentPanel());//添加窗口内部组件
	
    }
    //创建窗口主panel方法
    public JPanel CreateContentPanel(){
	//创建面板对象,并给panel选取布局,borderlayout把面板划分东南西北中
	JPanel panel = new JPanel(new BorderLayout());//默认布局floatLayout
	//panel.setBorder(new  EmptyBorder(10, 10, 10, 10));//面板边框,参数为上左下右,表示距离上xxx的距离
	
	//北面,一个jlabel显示登陆系统信息
	panel.add(new JLabel("登陆系统",JLabel.CENTER),BorderLayout.NORTH);
	//中间存放3个jLabel 和 输入框的 jpanel，
	panel.add(CreateCenterPanel(),BorderLayout.CENTER);
	//南边：存放2个按钮的jpanel
	panel.add(CreateBtnPanel(),BorderLayout.SOUTH);
	return panel;
	
	
	
    }
    //创建3个jLabel 和 输入框的 jpanel
    private JPanel CreateCenterPanel(){
	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(new EmptyBorder(8,8,8,8));
	
	//北边：存放2个jpanel和输入框
	panel.add(createIdOrPwdPanel(), BorderLayout.NORTH);
	
	//中间:存放现实登陆提示信息的jlabel
	message = new JLabel("",JLabel.CENTER);
	panel.add(message,BorderLayout.CENTER);
	return panel;
    }
    
    //创建jpanel（包含账号jlabel 密码jlabel 账号输入框  密码输出框）
    //将panel内部分成表格结构,2行1列
    //第一行放包含账号和输入框的组件
    //第二行放含有密码和输入框 的组件
    public JPanel createIdOrPwdPanel(){
	JPanel panel = new JPanel(new GridLayout(2, 1, 0, 6));
	
	
	//第一行
	panel.add(CreateIdPanel());
	
	//第二行
	panel.add(CreatePwdPanel());
	return panel;
    }
    
    public JPanel CreateIdPanel(){
	JPanel panel = new JPanel(new BorderLayout(6,0));
	//西边存放“账号”jlabel
	panel.add(new JLabel("账号:",JLabel.CENTER),BorderLayout.WEST);
	//中间存放"账号"输入框
	idField = new JTextField();//创建对象
	panel.add(idField,BorderLayout.CENTER);//居中
	return panel;
    }
    
    //创建密码
    public JPanel CreatePwdPanel(){
	
	JPanel panel = new JPanel(new BorderLayout(6,0));
	//西边存放密码 的jLabel
	panel.add(new JLabel("密码:",JLabel.CENTER),BorderLayout.WEST);
	//中间存放密码输入框
	pwdFild = new JPasswordField();
	panel.add(pwdFild,BorderLayout.CENTER);
	return panel;
    }
    
    //创建包含2个功能按钮的jpanel
    public JPanel CreateBtnPanel(){
	JPanel panel = new JPanel();//面板 默认居中
	JButton login = new JButton("登录");
	JButton cancel = new JButton("取消");
	panel.add(login);
	panel.add(cancel);
	return panel;
    }
}
