package com.lesson.day2;

import javax.swing.JFrame;


public class MoveBallFrame extends JFrame{
    public void ShowMe(){
	this.setTitle("moveBall");
	this.setSize(900,700);
	this.setLocationRelativeTo(null);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
	
	MoveBallFrame frame = new MoveBallFrame();
	Panel panel = new Panel();
	frame.add(panel);
	
	Thread thread = new Thread(panel);
	thread.start();
	frame.ShowMe();
    }
}