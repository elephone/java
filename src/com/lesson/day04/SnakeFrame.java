package com.lesson.day04;

import javax.swing.JFrame;

import com.homework.day3.Panel;

public class SnakeFrame extends JFrame{
    public SnakeFrame() {
	this.setTitle("eatingSnake");
	this.setBounds(403,200,470,480);
	
	
//	this.setUndecorated(true);//È¡Ïû±ß¿ò
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
    }
    public static void main(String[] args) {
	SnakeFrame frame = new SnakeFrame();
	SnakePanel  panel= new SnakePanel();
	frame.add(panel);
	
	panel.move();
    }
}
