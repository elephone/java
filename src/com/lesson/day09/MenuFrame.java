package com.lesson.day09;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuFrame extends JFrame{
    
    
    private JLabel message;//显示欢迎信息的jlabel
    private JButton start;//考试开始
    private JButton score;//分数查询
    private JButton msg;//考试规则
    private JButton exit;//考试离开
    
    private int scoreOfAll;//总分
    
    
    //定义控制层属性
    private ClientContext clientContext;
    
    
    public int getScoreOfAll() {
        return scoreOfAll;
    }
    public void setScoreOfAll(int scoreOfAll) {
        this.scoreOfAll = scoreOfAll;
    }
    
    public ClientContext getClientContext() {
        return clientContext;
    }
    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }
    
    public MenuFrame() {
	ShowMe();
    }
    public void ShowMe(){
	this.setTitle("达内在线测试");
	this.setSize(600, 400);
	this.setLocationRelativeTo(null);
	//this.setEnabled(false);//不可编辑
	this.setUndecorated(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setContentPane(CreateContentPanel());
    }
    
    //创建窗口中的主面板
    public JPanel CreateContentPanel(){
	JPanel panel = new JPanel(new BorderLayout());
	
	
	
	//北边:图片对象
	ImageIcon image = new ImageIcon(this.getClass().getResource("title.png"));
	panel.add(new JLabel(image),BorderLayout.NORTH);
	
	
	//中间存放欢迎信息的jlabel 和4个图片按钮
	panel.add(CreateCenterPanel(),BorderLayout.CENTER);
	
	// 南边是显示版权信息的jlabel
	panel.add(new JLabel("达内时代科技",JLabel.RIGHT),BorderLayout.SOUTH);
	
	
	return panel;
    }
    
    
    //创建包含欢迎信息jlabel和图片按钮的jpanel
    private JPanel CreateCenterPanel(){
	JPanel panel = new JPanel(new BorderLayout());
	//北边显示欢迎信息
	message = new JLabel("xxx同学,你好!",JLabel.CENTER);
	panel.add(message,BorderLayout.NORTH);
	
	
	//中间包含4个图片
	panel.add(CreateBtnPanel(),BorderLayout.CENTER);
	
	
	
	return panel;
    }
    
    
    //创建包含4个菜单按钮的jpanel
    private  JPanel CreateBtnPanel(){
	JPanel panel = new JPanel();
	//封装创建按钮的方法
	start = CreatImgBtn("开始","exam.png");
	
	start.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		//当点击开始考试按钮,执行控制层的开始考试流程
		clientContext.StartExam();
		
	    }
	});
	score =	CreatImgBtn("分数","result.png");
	score.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		ShowConfirmDialog(getScoreOfAll()+"");
		
	    }
	});
	msg = CreatImgBtn("考试规则","message.png");
	msg.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		ShowConfirmDialog("考试规则如下：\n"
			+ "对在线音乐分享平台进行了研究探讨，并取得了良好的效果，具体如下：\n"
			+ "  本次实习以网易云音乐为例，设计并实现了基于B/S结构音乐分享平台，\n"
			+ "在网页中设计了一套方便快捷的音乐方案，例如用户在网站上发布自己的歌单，\n"
			+ "也可以去评论别人分享的歌单。  我所做的网站的首页部分，用户可以在\n"
			+ "首页查看最新的歌曲信息，近期网络上各种歌曲的排行榜，其他用户分享的\n"
			+ "热门歌单，本网站已入驻歌手新曲等信息，也可以在我的音乐界面点击提交歌单来\n"
			+ "源其他网友分享自己喜欢的音乐，在查看其他人分享的歌单时，也可以在歌单下方\n"
			+ "发表自己的见解。也可以点击歌单标题进入歌单详细信息页面进行播放。");

	    }
	});
	exit = CreatImgBtn("离开","exit.png");
	exit.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		System.exit(-1);
		
	    }
	});
	panel.add(start);
	panel.add(score);
	panel.add(msg);
	panel.add(exit);
	return panel;
    }
    
    
    public void ShowConfirmDialog(String msg){
	JOptionPane.showConfirmDialog(this, msg);
    }
    //创建图片按钮,2个参数(按钮上图片的文字,按钮图片的名字)
    private JButton CreatImgBtn(String txt,String img){
	
	ImageIcon  image = new ImageIcon(this.getClass().getResource(img));
	JButton btn = new JButton(txt,image);
	//文字在按钮的水平位置
	btn.setHorizontalTextPosition(JButton.CENTER);
	
	
	//文字在按钮的垂直位置
	btn.setVerticalTextPosition(JButton.BOTTOM);
	return btn;
    }
    
    //将当前登陆用户显示到欢迎信息里面
    public void UpdateView(User user){
	String  msg = "欢迎"+user.getName()+"参加考试";
	message.setText(msg);
    }
    public void CouldNotExamAgain(){
	
	start.setEnabled(false);
	
    }
}
