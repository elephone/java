package com.lesson.day01;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



//���Ǵ��������
public class MystarPanel extends JPanel implements Runnable{

    //��������x����
    private int []xx;
    
    //��������y����
    private int []yy;
    
    private int circleX=550;
    private int circleY=150;
    //����ͼƬ
    private static Image image = new ImageIcon("src/com/lesson/day01/23.jpg").getImage();
    
    //���췽��
    public MystarPanel() {
	xx=new int [100000];
	yy=new int [100000];
	//�����������������
        Random random = new Random();
	for(int i=0; i<200; i++){
            xx[i]= random.nextInt(750);
            yy[i]= random.nextInt(550);
            
        }
    }
    //��д��ͼ����(���� ��д ���ࣩ
    @Override
    public void paint(Graphics g) {
        
        super.paint(g);
        //������屳����ɫ
        g.drawImage(image, 0, 0, this);
        //this.setBackground(Color.black);
      //�ı仭����ɫ
        g.setColor(Color.white);
      //����ɫ��Բ
        g.fillArc(600, 100, 50, 50, 0, 360);
      
       
        
        for(int i=0; i<200; i++){
            g.drawString("��", xx[i], yy[i]);
        }
        
        
        
//        for(int i=0; i<200; i++){
//            g.drawString("��", (int)(Math.random()*750), (int)(Math.random()*550));
//        }
        //��һ������
//        g.drawString("��", 400, 300);
        
        //�ı仭����ɫ
        g.setColor(Color.black);
        g.fillArc(circleX, circleY, 50, 50, 0, 360);
      
        
    }
    @Override
    public void run() {
	
	while(true)
	{
	    for(int i=0; i<200; i++)
	    yy[i]= (yy[i]+1)%560;
	    
	    if(circleY>30&&circleX<640){
		circleY--;
		circleX=(circleX+1)%650;
	    }
	    else{
		circleY=150;
		circleX=550;
	    }
	    
	    try {
		Thread.sleep(1);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    //�����ػ�
	    repaint();
	}
	
	
	
    }
}
