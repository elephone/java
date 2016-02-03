package com.lesson.day07;

import java.util.List;

public class Test {
    public static void main(String[] args) {
	String fileName = "src/com/lesson/day07/client.properties";
	LoadProperties load = new LoadProperties(fileName);
	double e = load.getDouble("TimeLimit");
	EntityContext entity = new EntityContext(load);
	
	User user = entity.findUserByID(1001);
	System.out.println(user.getName());
	
	List<Question> questions = 
	entity.findQuestionByLevel(5);
	for(int i=0; i<questions.size(); i++){
	    System.out.println(questions.get(i));//默认调用tostring 方法
	}
	
    }
}
