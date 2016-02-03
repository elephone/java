package com.lesson.day09;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.xml.crypto.Data;

public class ExamFrame extends JFrame{
    
    private JLabel examMsgLabel;//考试信息的JLabel
    private JTextArea quesetionArea;//显示考题信息的 文本域

    private JLabel txt;//考题内容
    private Option[] options;//定义多选数组
    private JLabel questionNumber;//显示当前考试题号
    private JButton previous, next, send;//上一题,下一题,交卷按钮
    private JLabel timerLabel;//考试倒计时JLabel
    
    
    private PaperExam paperExam;//考试信息
    
    
    private Timer timer;//考试倒计时
    
    
    
    
    private ClientContext clientContext;
    
    public ClientContext getClientContext() {
        return clientContext;
    }
    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }
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
	this.setUndecorated(true);
	
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
	panel.setBorder(new EmptyBorder(0, 6, 0, 6));
	//西边:题号的JLagel
	questionNumber = new JLabel("题目:第一题");
	panel.add(questionNumber,BorderLayout.WEST);
	
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
	previous.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		clientContext.prevQuestion();
		
	    }
	});
	next = new JButton("下一题");
	next.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		clientContext.nextQuestion();
		
	    }
	});
	send = new JButton("交卷");
	send.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		  clientContext.examOver();
		
	    }
	});
	panel.add(previous);
	panel.add(next);
	panel.add(send);
	return panel;
    }
    //更新考试信息的方法
    public void updataPaperExam(PaperExam paperExam){
	//将考试信息更新到显示考试信息的jlabel
	examMsgLabel.setText(paperExam.toString());
	//将paperexam保存为属性
	this.paperExam = paperExam;
    }
    //将考题更新到考试窗口的方法
    public void updataQuestion(PaperQuestion paperQuestion){
	
	//将考题信息显示到考试的文本域上面
	quesetionArea.setText(paperQuestion.toString());
	//将当前考题题号更新
	UpdataQuestionCount(paperQuestion.getQuestionIndex());
	//更新上一题或下一题按钮是否可点击
	UpdataButton(paperQuestion.getQuestionIndex());
	//将当前显示考题的用户答案 与窗口中的4个选项关联起来
	UpdataOptions(paperQuestion.getUserAnswers());
    }
    
    private  void UpdataQuestionCount(int index){
	String msg = "";
	if(index < 10){
	    msg = "题目:第"+"0"+index+"题";
	}else{
	    msg = "题目:第"+index+"题";
	}
	questionNumber.setText(msg);
    }
    
    //根据给定题号,设置上一题或下一题按钮是否可点击
    
    public void UpdataButton(int questionID){
	//上一题是否可点击
	previous.setEnabled(questionID != 1);
	//下题
	next.setEnabled(questionID != paperExam.getQuestionCount());
    }
    
    //将用户的答案与窗口中4个选项关联
    public void UpdataOptions(List<Integer> userAnswers){
	//循环判断 判断每一个operation值在不在operation里面
	//若存在,则让当前option选中
	for(Option option: options){
//	    if(userAnswers.contains(option.value)){
//		option.setSelected(true);
//	    }else{
//		option.setSelected(false);
//	    }
	    option.setSelected(userAnswers.contains(option.value));
	}
    }
    
   
    //开始考试倒计时
    public void StartTimer(){
	timer = new Timer();
	
	int min = paperExam.getTimeLinit();
	final long endTime = System.currentTimeMillis()+min*60*1000;//获取当前系统时间,取得考试结束时间
	timer.schedule(new TimerTask() {
	    
	    @Override
	    public void run() {
		long show = endTime - System.currentTimeMillis();
		long h = show/1000/60/60;
		long m = show/1000/60%60;
		long s = show/1000%60;
		
		
		//显示倒计时的jlable中
		timerLabel.setText("剩余时间:"+(h<10?"0"+h:h)+":"+(m<10?"0"+m:m)+":"+(s<10?"0"+s:s));
	    }
	}, 0, 1000);//1000ms
	
	//提前交卷
	
	//考试结束,自动交卷
	timer.schedule(new TimerTask() {
	    
	    @Override
	    public void run() {
		//强制执行交卷
		clientContext.sendPaper();
//		timer.cancel();
	    }
	},new Date(endTime));
	//停止考试倒计时
	

    }
    
    
    public void CancelTimer(){
	    timer.cancel();
	}
    //获取考试窗口考题答案
	public List<Integer> GetUserAnswers(){
	    //创建集合用于保存用户的答案,循环四个选项
	    //若选项被选中,将选项operation的vaule属性值放入集合中,返回保存用户答案的集合
	    List<Integer> userAnswers = new ArrayList<Integer>();
	    for(Option option:options){
		if(option.isSelected()){
		    userAnswers.add(option.value);
		    
		}
	    }
	    return userAnswers;//当前一道题的答案已被记录下来
	}
	
    //将给定内容作为提示框的询问信息,返回用户操作的结果
    public boolean showConfirm(String msg){
	int val = JOptionPane.showConfirmDialog(this, msg);
	return val == JOptionPane.YES_OPTION;//确定交卷
	
    }
    
    //弹出个消息框,显示给定内容
    public void ShowMessage(String msg){
	JOptionPane.showMessageDialog(this, msg);
    }
}
