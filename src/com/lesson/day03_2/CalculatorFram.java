package com.lesson.day03_2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lesson.day01.MyStarFrame;

public class CalculatorFram implements ActionListener{
    private boolean append = false;//标记
    private String s1 = "0";
    private String s2 = "0";
    private String operator = "+";
//    String  now = new String();
//    double num1=0, num2=0;
    
    private JTextField textField = new JTextField(20);
    
    public CalculatorFram() {
	JFrame frame = new JFrame("计算器");
	
	frame.setSize(300,200);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	JPanel panel = new JPanel();
	frame.add(panel);
	
	//按钮上的文字
	String []msgs = {"BK","CK","C","+","7","8","9","-","4","5","6","*","1","2","3","/","0","+/-",".","=",};
	Font style = new Font("宋体", Font.BOLD, 20);
	textField.setFont(style);
	textField.setEnabled(false);//不可选中
	textField.setText("0");//默认
	textField.setHorizontalAlignment(JTextField.RIGHT);//水平居右显示
	
	frame.add(textField, BorderLayout.NORTH);
	
	panel.setLayout(new GridLayout(5, 4));
	for(int i=0; i<20; i++){
	    //创建按钮
	    JButton button = new JButton(msgs[i]);
	    button.setFont(style);
	    button.addActionListener(this);
	    panel.add(button);
	}
	frame.setVisible(true);
    }
    public static void main(String[] args) {
	CalculatorFram frame = new CalculatorFram();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
	String comm = e.getActionCommand();//拿到事件触发的按钮
	
	if("0123456789".indexOf(comm)!= -1){
	    //执行数字操作
	    if(append){//标记表示追加
		String temp = textField.getText();
		textField.setText(temp+comm);
	    }else{//默认覆盖，当按操作符时，覆盖
		textField.setText(comm);
		
		if("0".equals(comm)){
		    append=false;
		}
		else{
		    append = true;
		}
	    }
//	    now += comm;
//	    num1 = num1*10+(double)comm;
//	    textField.setText(now);
	}else if ("+-*/".indexOf(comm)!= -1){
	    //执行操作符的操作
	    s1 = textField.getText();
	    operator = comm;
	    append = false;
//	    now += comm;
//	    textField.setText(now);
	    
	}else if(".".equals(comm)){
	    //执行小数点的操作
	    
	    String temp = textField.getText();//1.小数点前为0时需要显示出来 2.不能出现多个小数点
	    
	    if(temp.indexOf(".") == -1){
		
		textField.setText(temp+comm);
		append = true;
	    }
	    
	    
	    
	    
//	    now += comm;
//	    textField.setText(now);
	}else if("+/-".equals(comm)) {
	    //执行正负数的操作
	    String temp = textField.getText();
	    if(temp.startsWith("-")){
		textField.setText(temp.substring(1));//字符串截取到末尾
	    }else{
		textField.setText("-"+temp);
	    }
//	    now += "-";
//	    textField.setText(now);
	    
	}else if("=".equals(comm)){/****************/
	    //计算
	    s2 = textField.getText();
	    double d1 = Double.parseDouble(s1);
	    double d2 = Double.parseDouble(s2);
	    double result=0;
	    if("+".equals(operator)){
		result = d1+d2;
	    }else if("-".equals(operator)){
		result = d1-d2;
	    }else if("*".equals(operator)){
		result = d1*d2;
	    }else if("/".equals(operator)){
		result = d1/d2;
	    }
	    textField.setText(result+ "");
//	    count(now);
	}else if ("BK".equals(comm)){
	    //执行回退的操作
	    String temp = textField.getText();
	    if(temp.length()>1){
		textField.setText(temp.substring(0, temp.length()-1));//半闭区间，最后个字符取不到
	    }else{
		textField.setText("0");
		append = false;
	    }
	}else if("CK".equals(comm)|| "C".equals(comm)){
	    //执行清零的操作
	    textField.setText("0");
	    append = false;
	    s1 = "0" ;
	    s2 = "0";
	}else{
	    ;
	}
	
	
    }
    
}
