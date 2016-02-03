package com.homework.day3;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fram extends JFrame implements ActionListener{
    private JTextField textField = new JTextField(20);

  //尝试做出修改东方闪电的说法 实得分
    //点击发送快递放假拉萨宽带缴费拉开距离三的会计法律框架
    //尝试做出修改东方闪电的说法 实得分
    //点击发送快递放假拉萨宽带缴费拉开距离三的会计法律框架
    public Fram() {
	JFrame frame = new JFrame();
	frame.setSize(300,390);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setUndecorated(true);//取消边框
	Panel panel = new Panel();
	frame.add(panel);
	frame.setVisible(true);
	
	
	Font style = new Font("宋体", Font.BOLD, 20);
	textField.setFont(style);
	textField.setEnabled(false);//不可选中
	textField.setText("0");//默认
	textField.setHorizontalAlignment(JTextField.RIGHT);//水平居右显示
	frame.add(textField, BorderLayout.NORTH);
	
    }
    public static void main(String[] args) {
	Fram frame = new Fram();
	
    }
    
}
