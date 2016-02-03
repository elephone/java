package com.lesson.day2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{
    
    Ball []balls = new Ball[10];
    public Panel(){
	for(int i=0; i<balls.length; i++)
	{
	    int r = (int)(Math.random()*30+20);
	    int ballX = (int)(Math.random()*(800-2*r)+r);
	    int ballY = (int)(Math.random()*(600-2*r)+r);
	    
	    int red = (int)(Math.random()*255);
	    int green = (int)(Math.random()*255);
	    int blue = (int)(Math.random()*255);
	    Color color = new Color(red, green, blue);
	    
	    int ballDirection = (int)(Math.random()*3);
	    int ballSpeed = (int)(Math.random()*5+1);
	    
	    balls[i] = new Ball(ballX, ballY, r, color, ballDirection, ballSpeed,this);
	}
    }
    @Override
    public void paint(Graphics g) {
        
        super.paint(g);
        
        //±³¾°
        this.setBackground(Color.black);
        
        for(int i=0; i<balls.length; i++)
	{
            Ball ball = balls[i];
            //ÉèÖÃ»­±ÊÑÕÉ«
            g.setColor(ball.getColor());
            g.fillArc(ball.getCenterX()-ball.getR(), ball.getCenterY()-ball.getR(), 2*ball.getR(), 2*ball.getR(), 0, 360);
	}
    }
    @Override
    public void run() {
        while(true){
            for(int i=0; i<balls.length; i++)
            {
        	Ball ball = balls[i];
        	ball.move();
        	for(int j=0; j<i; j++){
        	    ball.BallConflict(balls[i], balls[j]);
        	}
            }
            
            try {
		Thread.sleep(10);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    //»­°åÖØ»­
	    repaint();
        }
        
    }
    
}
