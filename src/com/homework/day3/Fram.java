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

  //���������޸Ķ��������˵�� ʵ�÷�
    //������Ϳ�ݷż���������ɷ������������Ļ�Ʒ��ɿ��
    //���������޸Ķ��������˵�� ʵ�÷�
    //������Ϳ�ݷż���������ɷ������������Ļ�Ʒ��ɿ��
    public Fram() {
	JFrame frame = new JFrame();
	frame.setSize(300,390);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setUndecorated(true);//ȡ���߿�
	Panel panel = new Panel();
	frame.add(panel);
	frame.setVisible(true);
	
	
	Font style = new Font("����", Font.BOLD, 20);
	textField.setFont(style);
	textField.setEnabled(false);//����ѡ��
	textField.setText("0");//Ĭ��
	textField.setHorizontalAlignment(JTextField.RIGHT);//ˮƽ������ʾ
	frame.add(textField, BorderLayout.NORTH);
	
    }
    public static void main(String[] args) {
	Fram frame = new Fram();
	
    }
    
}
