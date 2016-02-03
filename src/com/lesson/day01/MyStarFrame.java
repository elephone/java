package com.lesson.day01;

import java.lang.annotation.Target;

import javax.management.remote.TargetedNotification;
import javax.swing.JFrame;


//星星窗体类
public class MyStarFrame extends JFrame{
    //showMe()显示窗口对象基本信息
    public void showMe()
    {
	//设置窗体对象的标题
	this.setTitle("明星");
	//窗体大小
	this.setSize(800, 600);
	//设置窗体对象位置
//	this.setLocation(20, 10);
	this.setLocationRelativeTo(null);//当前组件和副组件之间的关系
	
	//设置窗体是否可见
	this.setVisible(true);
	//设置窗体对象关闭，程序默认退出
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //main
    public static void main(String [] args)
    {
	//创建窗体对象
	MyStarFrame frame = new MyStarFrame();
	//创建面板对象
	MystarPanel panel = new MystarPanel();
	//创建线程对象
	Thread th = new Thread(panel);
	//启动线程
	th.start();
	//把面板对象添加到窗体对象中
	frame.add(panel);
	//调用窗体对象showMe()显示信息
	
	frame.showMe();
	
	
	
    }

}
