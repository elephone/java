package com.lesson.day01;

import java.lang.annotation.Target;

import javax.management.remote.TargetedNotification;
import javax.swing.JFrame;


//���Ǵ�����
public class MyStarFrame extends JFrame{
    //showMe()��ʾ���ڶ��������Ϣ
    public void showMe()
    {
	//���ô������ı���
	this.setTitle("����");
	//�����С
	this.setSize(800, 600);
	//���ô������λ��
//	this.setLocation(20, 10);
	this.setLocationRelativeTo(null);//��ǰ����͸����֮��Ĺ�ϵ
	
	//���ô����Ƿ�ɼ�
	this.setVisible(true);
	//���ô������رգ�����Ĭ���˳�
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //main
    public static void main(String [] args)
    {
	//�����������
	MyStarFrame frame = new MyStarFrame();
	//����������
	MystarPanel panel = new MystarPanel();
	//�����̶߳���
	Thread th = new Thread(panel);
	//�����߳�
	th.start();
	//����������ӵ����������
	frame.add(panel);
	//���ô������showMe()��ʾ��Ϣ
	
	frame.showMe();
	
	
	
    }

}
