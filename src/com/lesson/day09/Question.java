package com.lesson.day09;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;
//����е�һ������,������,
public class Question {
    
    private List<Integer> answer = new ArrayList<Integer>();//��ȷ��
    private String title;//���
    private List<String> options = new ArrayList<String>();//ѡ��
    
    private int score;//����
    
    private int level;//���Զ�
    private int type;//��������:��ѡ���ѡ
    
    public Question() {
	
    }
    public boolean equals(Object obj){//������д
	if(obj == null){
	    return false;
	}
	if(obj == this){
	    return true;
	}
	if(obj instanceof Question){//�жϱ�����ָ��Ķ����Ƿ���ͬ���͵�
	    Question question = (Question)obj;
	    return (this.title.equals(question.title)  );//&& this.options.equals(question.options)
	}
	return false;
    }
    
    
    public int hashCode(){
	return (int)(100000*Math.random());
    }
    
    
    public String toString(){//������ǰ������ı���ʽ
	//���Ҵ�ӡһ�����ʱ��,��ӡ������ı���Ϣ
	StringBuffer sb = new  StringBuffer();//String Ϊ��̬,���ܸĶ���С
	sb.append(title+"\n");
	for(int i=0; i<options.size(); i++){
	    sb.append((char)('A'+i)+"."+options.get(i)+"\n");
	}
	return sb.toString();
    }
    //�������ͳ���
    public static final int TYPE_SINGLE_SELECT = 0;
    public static final int TYPE_MULTI_SELECT = 1;
    //�������׶ȳ���
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
