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
    private boolean append = false;//���
    private String s1 = "0";
    private String s2 = "0";
    private String operator = "+";
//    String  now = new String();
//    double num1=0, num2=0;
    
    private JTextField textField = new JTextField(20);
    
    public CalculatorFram() {
	JFrame frame = new JFrame("������");
	
	frame.setSize(300,200);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	JPanel panel = new JPanel();
	frame.add(panel);
	
	//��ť�ϵ�����
	String []msgs = {"BK","CK","C","+","7","8","9","-","4","5","6","*","1","2","3","/","0","+/-",".","=",};
	Font style = new Font("����", Font.BOLD, 20);
	textField.setFont(style);
	textField.setEnabled(false);//����ѡ��
	textField.setText("0");//Ĭ��
	textField.setHorizontalAlignment(JTextField.RIGHT);//ˮƽ������ʾ
	
	frame.add(textField, BorderLayout.NORTH);
	
	panel.setLayout(new GridLayout(5, 4));
	for(int i=0; i<20; i++){
	    //������ť
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
	String comm = e.getActionCommand();//�õ��¼������İ�ť
	
	if("0123456789".indexOf(comm)!= -1){
	    //ִ�����ֲ���
	    if(append){//��Ǳ�ʾ׷��
		String temp = textField.getText();
		textField.setText(temp+comm);
	    }else{//Ĭ�ϸ��ǣ�����������ʱ������
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
	    //ִ�в������Ĳ���
	    s1 = textField.getText();
	    operator = comm;
	    append = false;
//	    now += comm;
//	    textField.setText(now);
	    
	}else if(".".equals(comm)){
	    //ִ��С����Ĳ���
	    
	    String temp = textField.getText();//1.С����ǰΪ0ʱ��Ҫ��ʾ���� 2.���ܳ��ֶ��С����
	    
	    if(temp.indexOf(".") == -1){
		
		textField.setText(temp+comm);
		append = true;
	    }
	    
	    
	    
	    
//	    now += comm;
//	    textField.setText(now);
	}else if("+/-".equals(comm)) {
	    //ִ���������Ĳ���
	    String temp = textField.getText();
	    if(temp.startsWith("-")){
		textField.setText(temp.substring(1));//�ַ�����ȡ��ĩβ
	    }else{
		textField.setText("-"+temp);
	    }
//	    now += "-";
//	    textField.setText(now);
	    
	}else if("=".equals(comm)){/****************/
	    //����
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
	    //ִ�л��˵Ĳ���
	    String temp = textField.getText();
	    if(temp.length()>1){
		textField.setText(temp.substring(0, temp.length()-1));//������䣬�����ַ�ȡ����
	    }else{
		textField.setText("0");
		append = false;
	    }
	}else if("CK".equals(comm)|| "C".equals(comm)){
	    //ִ������Ĳ���
	    textField.setText("0");
	    append = false;
	    s1 = "0" ;
	    s2 = "0";
	}else{
	    ;
	}
	
	
    }
    
}
