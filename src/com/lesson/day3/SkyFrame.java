package com.lesson.day3;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lesson.day2.Panel;

public class SkyFrame extends JFrame{
    private double width;
    private double height;
    //private final static  Image background = new ImageIcon("src/com/lesson/day3/background.jpg").getImage();
    final Image background = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("background.jpg"));
    private static SkyPanel panel;
    final Image bar = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("bar.png"));
    final Image m = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("m_03.png"));
    public SkyFrame() {
	Toolkit tk = Toolkit.getDefaultToolkit();
	width = tk.getScreenSize().getWidth();
	height = tk.getScreenSize().getHeight();
	
	this.setTitle("Sky");
	this.setBounds(0,0,(int)width,(int)height);//相当于下面2行代码
//	this.setSize((int)width,(int)height);
//	this.setLocationRelativeTo(null);
	panel = new SkyPanel();
	this.add(panel);
	this.setUndecorated(true);//取消边框
	this.setVisible(true);
	
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
    }
    public static void main(String[] args) {
	SkyFrame frame = new SkyFrame();
	Thread th = new Thread(panel);
	th.start();
	//面板对象添加鼠标监听
	panel.addMouseListener(new MouseListener() {
	    
	    @Override
	    public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	    }
	    
	    @Override
	    public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	    }
	    
	    @Override
	    public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	    }
	    
	    @Override
	    public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	    }
	    
	    @Override
	    public void mouseClicked(MouseEvent arg0) {
		System.exit(0);
		
	    }
	});
    }
    class SkyPanel extends JPanel implements Runnable{
	    int count = 5;
	    int []starX = new int [count];
	    int []starY = new int [count];
	   
	    
	    @Override
	    public void paint(Graphics g) {
	        // TODO Auto-generated method stub
	        super.paint(g);
	        
	        g.drawImage(background, 0, 0, (int)width,(int)height,this);
	        
	        g.drawImage(bar, 700, 100, this);
	        for(int i=0; i<count; i++){
	            g.drawImage(m, starX[i], starY[i], this);
	        }
	    }
	    @Override
	    public void run() {
	    // TODO Auto-generated method stub
		
			
		while(true){
		    for(int i=0; i<count; i++){
			starX[i] = (int)(Math.random()*width);
			starY[i] = (int)(Math.random()*60);
		    }
		  //控制移动
	  		for(int x=0; x<100; x++){
	  		    for(int i=0; i<count; i++){
	  			starX[i]= starX[i] - 5;
	  			starY[i]++;
	  		    }
	  		    try {
	  			Thread.sleep(10);
	  		    } catch (InterruptedException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		    }
	  		    repaint();
	  		}
	  		count = (int)(Math.random()*10);
	  		starX = new int [count];
	  		starY = new int [count];
	  		
		}
		
	    }
	  	
	    
	}
}
