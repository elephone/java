package com.lesson.day2;

import java.awt.Color;


import javax.swing.JPanel;

public class Ball {
    public Ball() {
	
	
    }
    
    
    
    public int getCenterX() {
        return centerX;
    }



    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }



    public int getCenterY() {
        return centerY;
    }



    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }



    public int getR() {
        return r;
    }



    public void setR(int r) {
        this.r = r;
    }



    public Color getColor() {
        return color;
    }



    public void setColor(Color color) {
        this.color = color;
    }



    public int getDirection() {
        return direction;
    }



    public void setDirection(int direction) {
        this.direction = direction;
    }



    public int getSpeed() {
        return speed;
    }



    public void setSpeed(int speed) {
        this.speed = speed;
    }



    public JPanel getPanel() {
        return panel;
    }



    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void move(){
	
	if(this.direction == 0){//↖
	    
	    this.setCenterX(this.centerX-speed);
	    this.setCenterY(this.centerY-speed);
	    this.Conflict();
	}
	else if(this.getDirection() == 1){//↙
	    this.setCenterX(this.getCenterX()-speed);
	    this.setCenterY(this.getCenterY()+speed);
	    this.Conflict();
	}
	else if(this.getDirection() == 2){//↗
	    this.setCenterX(this.getCenterX()+speed);
	    this.setCenterY(this.getCenterY()-speed);
	    this.Conflict();
	}
	else {//↘
	    this.setCenterX(this.getCenterX()+speed);
	    this.setCenterY(this.getCenterY()+speed);
	    this.Conflict();
	}
	
    }
    private void Conflict(){
	if((this.centerY-this.r)<0){
	    if(this.direction == 0){
		this.direction = 1;
	    }
	    else{//dircetion  = 2;
		this.direction = 3;
	    }
	}
	else if((this.centerY+this.r)>700 ){
	    if(this.direction == 1){
		this.direction = 0;
	    }
	    else if(this.direction == 3){//dircetion  = 3;
		this.direction = 2;
	    }
	    else{;}
	}
	else if((this.centerX-this.r)<0){
	    if(this.direction == 1){
		this.direction = 3;
	    }
	    else if(this.direction == 0){//dircetion  = 0;
		this.direction = 2;
	    }
	    else{;}
	}
	else if ((this.centerX+this.r)>900){
	    if(this.direction == 2){
		this.direction = 0;
	    }
	    else if(this.direction == 3){//dircetion  = 3;
		this.direction = 1;
	    }
	    else{;}
	}
	else{;}
    }
    public void BallConflict(Ball ball1, Ball ball2){
	if(((ball1.centerX-ball2.centerX)
		*(ball1.centerX-ball2.centerX)
		+(ball1.centerY-ball2.centerY)
		*(ball1.centerY-ball2.centerY))
		<((ball1.r+ball2.r)
		*(ball1.r+ball2.r)-20)){
	    ball1.direction = 3-ball1.direction;
	    ball2.direction = 3-ball1.direction;
	    if(ball1.speed<2)
		ball1.speed++;
	    if(ball2.speed<2)
		ball2.speed++;
	    int temp = ball1.speed+ball2.speed;
	    ball1.speed = temp/2;
	    ball2.speed = temp/2;
	}
    }
    public Ball(int centerX, int centerY, int r, Color color, int direction,
	    int speed, JPanel panel) {
	super();
	this.centerX = centerX;
	this.centerY = centerY;
	this.r = r;
	this.color = color;
	this.direction = direction;
	this.speed = speed;
	this.panel = panel;
    }
    private int centerX,centerY;//圆心位置坐标
    private int  r;//半径
    private Color color;
    private int direction;//运动方向
    private int speed;
    private JPanel panel;//所在面板
}
