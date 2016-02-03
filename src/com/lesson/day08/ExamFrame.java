package com.lesson.day08;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ExamFrame extends JFrame{
    
    private JLabel examMsgLabel;//考试信息的JLabel
    private JTextArea quesetionArea;//显示考题信息的 文本域

    private JLabel txt;//考题内容
    private Option[] options;//定义多选数组
    private JLabel questionNumber;//显示当前考试题号
    private JButton previous, next, send;//上一题,下一题,交卷按钮
    private JLabel timerLabel;//考试倒计时JLabel
    //考试窗口
    public ExamFrame() {
	ShowMe();
    }
    public void ShowMe(){
	this.setTitle("考试窗口");
	this.setSize(600, 400);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setContentPane(CreateContentPanel());
    }
    
    public JPanel CreateContentPanel(){
	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(new EmptyBorder(10,0,10,0));//TODO:
	//北面,图片
	ImageIcon image = new ImageIcon(getClass().getResource("exam_title.png"));
	panel.add(new JLabel(image,JLabel.CENTER),BorderLayout.NORTH);
	
	//中间  :姓名 ,试卷考试时间     含有  题目的jlabel和选项    多选框
	panel.add(CreateCenterPanel(),BorderLayout.CENTER);
	
	//南面:题号  ,3个按钮  ,剩余时间
	panel.add(CreateSouthPanel(),BorderLayout.SOUTH);
	
	return panel;
    }
    //包含考试信息,题干和选项,4个选项框
    private JPanel CreateCenterPanel(){
	JPanel panel = new JPanel(new BorderLayout());
	
	//北面: 姓名 ,试卷考试时间  
	
	examMsgLabel = new JLabel("姓名:xxx 编号:10000",JLabel.CENTER);
	panel.add(examMsgLabel,BorderLayout.NORTH);
	
	
	//中间: 一个带滚动条的jPanel  存放题干和选项的文本域
	panel.add(CreateQuestionPanel(),BorderLayout.CENTER);
	
	
	//南面:创建包含功能按钮,考试题号,考试剩余时间的JPanel
	panel.add(CreateToolsPanel(),BorderLayout.SOUTH);
	
	return panel;
    }
    //一个带滚动条的jPanel
    private JScrollPane CreateQuestionPanel(){
	JScrollPane panel =  new JScrollPane();
	panel.setBorder(new TitledBorder("题目"));
	
	quesetionArea = new JTextArea();//文本域
	quesetionArea.setText("1.学好编程?ddddddddd"
		+ "dddddddddddddddddddddddd\n A.\nB.\nC.\nD.\n");
	
	//允许回行显示
	quesetionArea.setLineWrap(true);
	//文本域不可编辑
	quesetionArea.setEditable(false);
	//note:不可以直接将文本域添加到pan中
	//当前的panel是带滚动条的,因此需要让panel关注
	//文本域组件,则当文本超出panel时,才显示滚动条
	panel.getViewport().add(quesetionArea);
	return panel;
    }
    private JPanel CreateSouthPanel(){
	JPanel panel = new JPanel();
	options = new Option[4];
	for(int i=0; i<options.length; i++){
	    options[i] = new Option(i, (char)('A'+i)+"");
	    panel.add(options[i]);
	}
	return panel;
    }
    class Option extends JCheckBox{
	//自定义一个属性,用于记录当前选项代表的值
	private int value;
	
	public Option(int Value,String msg){
	    super(msg);
	    this.value = value;
	}
    }
    //创建包含功能按钮,考试题号,考试剩余时间的JPanel
    private JPanel CreateToolsPanel(){
	JPanel panel = new JPanel(new BorderLayout());
	panel.setBorder(new EmptyBorder(0, 10, 0, 10));
	//西边:题号的JLagel
	questionNumber = new JLabel("题目:第一题");
	panel.add(questionNumber,BorderLayout.EAST);
	
	//中间包含功能按钮的jpanel
	panel.add(CreateBtnPanel(),BorderLayout.CENTER);
	
	//东边:考试倒计时的 JLabel
	timerLabel = new JLabel("剩余时间:00:23:00");
	panel.add(timerLabel,BorderLayout.EAST);
	
	return panel;
	
    }
    
    //创建3 个功能按钮的jpanel
    private JPanel CreateBtnPanel(){
	JPanel panel =  new JPanel();
	previous = new JButton("上一题");
	next = new JButton("下一题");
	send = new JButton("交卷");
	
	panel.add(previous);
	panel.add(next);
	panel.add(send);
	return panel;
    }
    //更新考试信息的方法
    public void updataPaperExam(PaperExam paperExam){
	//将考试信息更新到显示考试信息的jlabel
	examMsgLabel.setText(paperExam.toString());
    }
    //将考题更新到考试窗口的方法
    public void updataQuestion(PaperQuestion paperQuestion){
	//将考题信息显示到考试的文本域上面
	quesetionArea.setText(paperQuestion.toString());
	
    }
    
}
