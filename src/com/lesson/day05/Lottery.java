package com.lesson.day05;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Lottery extends JFrame{
    
    static Map<String, String> users = new HashMap<String, String>();
    public Lottery() {
	this.setSize(868,552);
	this.setLocationRelativeTo(null);
	
	this.setUndecorated(true);//取消边框
	
	
	LotteryPanel panel = this.new LotteryPanel();
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.add(panel);
	this.setVisible(true);
	
	
    }
    //读取配置文件的方法
    public static void read(){//静态方法中只能调用静态方法和属性
	Properties ps = new Properties();
	File fileName = new File("src/com/lesson/day05/Lottery.properties");
	try {
	    ps.load(new FileInputStream(fileName));//字节输入流
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}
	users.put("张三",ps.getProperty("张三"));
	users.put("李四",ps.getProperty("李四"));
	users.put("王五",ps.getProperty("王五"));
	users.put("赵六",ps.getProperty("赵六"));
	
	
	try {
	    InputStream is = new  FileInputStream(fileName);
	    InputStreamReader isr = new InputStreamReader(is);
	    BufferedReader br = new BufferedReader(isr);
	    String msg = "";
	    
	    while((msg = br.readLine())!=null){//读取到一行字符串
		String[] msgs = msg.split("=");
		System.out.println(msgs[0]);
		System.out.println(msgs[1]);
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	
	
    }
    public static void main(String[] args) {
	new Lottery();
	read();
	System.out.print(users.get("张三"));
	
	
    }
    class LotteryPanel extends JPanel{
	
	
	public void paint(Graphics g){
	    super.paint(g);
	    
	    g.drawImage(new ImageIcon("src/com/lesson/day05/back.png").getImage(), 0, 0, this);
	    g.drawImage(new ImageIcon("src/com/lesson/day05/close.png").getImage(), 840, 12, this);
	    g.drawImage(new ImageIcon("src/com/lesson/day05/man.png").getImage(), 795, 0, this);
	    g.drawImage(new ImageIcon("src/com/lesson/day05/start.png").getImage(), 334, 311, this);
	    g.drawImage(new ImageIcon("src/com/lesson/day05/again.png").getImage(), 725, 426, this);
	    
	    
	    
	}
    }
}
