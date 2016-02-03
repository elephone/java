package com.homework.day3;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel extends JPanel{
    
    private static Image bakeground = new ImageIcon("src/com/homework/day3/可爱/back.png").getImage();
    String add = "src/com/homework/day3/可爱/";
    Image imgs = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("but0_0.png"));
@Override
    public void paint(Graphics g) {
    	
    	super.paint(g);
    	g.drawImage(bakeground, 0, 0, 300, 390, this);
    	this.setLayout(new GridLayout(5, 4));
	for(int i=0; i<20; i++){
	    //创建按钮
	    JButton button = new JButton();
	    button.setFont(style);
	    button.addActionListener(this);
	    panel.add(button);
	}
    }
}
