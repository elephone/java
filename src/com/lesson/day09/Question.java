package com.lesson.day09;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;
//题库中的一道考题,考题类,
public class Question {
    
    private List<Integer> answer = new ArrayList<Integer>();//正确答案
    private String title;//题干
    private List<String> options = new ArrayList<String>();//选项
    
    private int score;//分数
    
    private int level;//难以度
    private int type;//考题类型:单选或多选
    
    public Question() {
	
    }
    public boolean equals(Object obj){//方法重写
	if(obj == null){
	    return false;
	}
	if(obj == this){
	    return true;
	}
	if(obj instanceof Question){//判断变量所指向的对象是否是同类型的
	    Question question = (Question)obj;
	    return (this.title.equals(question.title)  );//&& this.options.equals(question.options)
	}
	return false;
    }
    
    
    public int hashCode(){
	return (int)(100000*Math.random());
    }
    
    
    public String toString(){//描述当前对象的文本形式
	//当我打印一道题的时候,打印考题的文本信息
	StringBuffer sb = new  StringBuffer();//String 为静态,不能改动大小
	sb.append(title+"\n");
	for(int i=0; i<options.size(); i++){
	    sb.append((char)('A'+i)+"."+options.get(i)+"\n");
	}
	return sb.toString();
    }
    //考题类型常量
    public static final int TYPE_SINGLE_SELECT = 0;
    public static final int TYPE_MULTI_SELECT = 1;
    //考题难易度常量
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;
    public static final int LEVEL_4 = 4;
    public static final int LEVEL_5 = 5;
    public static final int LEVEL_6 = 6;
    public static final int LEVEL_7 = 7;
    public static final int LEVEL_8 = 1;
    public static final int LEVEL_9 = 1;
    public static final int LEVEL_10 = 10;
    
    
    public List<Integer> getAnswer() {
        return answer;
    }
    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<String> getOptions() {
        return options;
    }
    public void setOptions(List<String> options) {
        this.options = options;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    
    
    
    
}
