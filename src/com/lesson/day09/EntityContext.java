package com.lesson.day09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//DOA层,将数据装潢Wie对象,
//此类的作用 的将配置文件中的数据转换为java对象
import java.util.Map;

public class EntityContext {
    //LoadProperties属性,用于读取系统配置文件的信息
    private LoadProperties load;
    public EntityContext(LoadProperties load){
	this.load = load;
	
	//加载考生信息
	loadUsers("src/com/lesson/day07/"+load.getString("UserFile"));
	//加载QuestionFile
	LoadQuestions("src/com/lesson/day07/"+load.getString("QuestionFile"));
    }
    
    
    
    
    
    //用于保存全部考生的hashMap,key:考生的ID,value :考生对象
    private Map<Integer, User> users = new  HashMap<Integer, User>();
    public void loadUsers(String fileName){
	try {
	    FileInputStream fis = new FileInputStream(fileName);//构件输入流管道
	    InputStreamReader reader =  new InputStreamReader(fis,"GBK");//文件输入字节流回出现乱码
	    BufferedReader br = new BufferedReader(reader);//缓冲流
	    
	    //用于保存每次读取到的一行字符串
	    String msg = "";//空对象
	    while((msg = br.readLine()) != null){//到达文件末尾
		
		msg = msg.trim();//去掉两端的空白
		
		//判断是否有效
		if(msg.equals("")|| msg.startsWith("#")){//以空字符串,#开头的,
		    continue;//结束本次循环 
		}
		//将这行代表考生信息的字符串  解析成User对象
		User user =  ParseUser(msg);
		
		//将user保存到user这个hashMap中
		users.put(user.getId(), user);
		
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    
    //将描述一个考生信息的字符串转换成user对象
    public User ParseUser(String msg){
	//1000:宁丽娟:1234:13810381038:ninglj@tarena.com.cn
	//以:切割拆分
	String [] msgs = msg.split(":");
	//创建user对象
	User user = new User();
	//将拆分的信息存储到user对象对应的属性上
	user.setId(Integer.parseInt(msgs[0]));
	user.setName(msgs[1]);
	user.setPassword(msgs[2]);
	user.setPhone(msgs[3]);
	user.setEmail(msgs[4]);
	return user;
    }
    //根据考生的id查询考生对象
    public User findUserByID(int id){
	return users.get(id);
    }
    /****************************************************/
    //用于保存全部的试题信息
    //key:考题的难易度  value:保存对应难易度的全部考题的集合list
    private Map<Integer, List<Question>> questions = new HashMap<Integer, List<Question>>() ;
    
    //将配置文件中的制定记录考题的文件解析成Questions,并保存到questions集合中
    public void LoadQuestions(String fileName){
	try {
	    FileInputStream fis = new FileInputStream(fileName);//构件输入流管道
	    InputStreamReader reader =  new InputStreamReader(fis,"GBK");//文件输入字节流回出现乱码
	    BufferedReader br = new BufferedReader(reader);//缓冲流
	    
	    //用于保存每次读取到的一行字符串
	    String msg = "";//空对象
	    while((msg = br.readLine()) != null){//到达文件末尾
		
		msg = msg.trim();//去掉两端的空白
		
		//判断是否有效
		if(msg.equals("")|| msg.startsWith("#")){//以空字符串,#开头的,
		    continue;//结束本次循环 
		}
		
		//解析一道考题
		Question question =  ParseQuestion(msg,br);
		
		//将解析出的考题放入集合questions中,将一个用户对象
//		users.put(user.getId(), user);
		AddQuestionToMap(question);
		
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    
    //解析考题的方法,考题信息(正确答案 ,分数,难度)
    //考题内容:第一行题干,2-5行选项
    public Question ParseQuestion(String msg, BufferedReader br)throws Exception{
	Question question = new Question();
	//1.解析考题信息 
	//@answer=2/3,score=5,level=5
	String []data = msg.split("[@,][a-z]+=");//正则表达式,切除后有4个元素,
	//data={"",2/3,5,5}
	
	question.setAnswer(parseAnswers(data[1]));
	question.setScore(Integer.parseInt(data[2]));
	question.setLevel(Integer.parseInt(data[3]));
	/////////////  考试内容   ///////////////////
	//2.使用输入流继续读信息,读取第一行问题
	question.setTitle(br.readLine());
	//读取四行,
	question.getOptions().add(br.readLine());
	question.getOptions().add(br.readLine());
	question.getOptions().add(br.readLine());
	question.getOptions().add(br.readLine());
	
	
	//定义type
	question.setType(question.getAnswer().size()>1?Question.TYPE_MULTI_SELECT:Question.TYPE_SINGLE_SELECT);
	return question;
    }
    
    
    //将正确答案的字符串拆分转换成int 存放在list集合中
    private List<Integer> parseAnswers(String answer){
	List<Integer> answers = new ArrayList<Integer>();
	//answer:2/3   2 3
	String []data = answer.split("/");
	//将data 中的变成int 
	for(String msg:data){
	    answers.add(Integer.parseInt(msg));
	}
	return answers;
    }
    
    //将一道考题，根据难易度存放如该集合中
    
    private void AddQuestionToMap(Question question ){
	//根据考题难易度，在question中查询次难易度级别是否之前有装题的集合，如果没有
	//即list==null则创建新的 集合list，把这题放入进去，如果有，则直接把此题添加到集合中
	List<Question> list = questions.get(question.getLevel());
	if(list == null){//小集合不存在
	    list = new ArrayList<Question>();
	    questions.put(question.getLevel(),list);
	}
	list.add(question);
	
    }
    
    //根据难易度，渠道对应难易度的全部考题
    public List<Question> findQuestionByLevel(int level){
	return  questions.get(level);
    }

    
    //获取考试时间(分钟)
    public int getTimeLimit(){
	return load.getInt("TimeLimit");
	
    }
    public String getTitle(){
	return load.getString("PaperTitle");
    }
}
