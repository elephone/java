package com.lesson.day01;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



//星星窗体面板类
public class MystarPanel extends JPanel implements Runnable{

    //保存星星x坐标
    private int []xx;
    
    //保存星星y坐标
    private int []yy;
    
    private int circleX=550;
    private int circleY=150;
    //加载图片
    private static Image image = new ImageIcon("src/com/lesson/day01/23.jpg").getImage();
    
    //构造方法
    public MystarPanel() {
	xx=new int [100000];
	yy=new int [100000];
	//创建随机生成器对象
        Random random = new Random();
	for(int i=0; i<200; i++){
            xx[i]= random.nextInt(750);
            yy[i]= random.nextInt(550);
            
        }
    }
    //重写绘图方法(子类 重写 父类）
    @Override
    public void paint(Graphics g) {
        
        super.paint(g);
        //设置面板背景颜色
        g.drawImage(image, 0, 0, this);
        //this.setBackground(Color.black);
      //改变画笔颜色
        g.setColor(Color.white);
      //画白色的圆
        g.fillArc(600, 100, 50, 50, 0, 360);
      
       
        
        for(int i=0; i<200; i++){
            g.drawString("★", xx[i], yy[i]);
        }
        
        
        
//        for(int i=0; i<200; i++){
//            g.drawString("★", (int)(Math.random()*750), (int)(Math.random()*550));
//        }
        //画一颗星星
//        g.drawString("★", 400, 300);
        
        //改变画笔颜色
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
	    //画板重画
	    repaint();
	}
	
	
	
    }
}
