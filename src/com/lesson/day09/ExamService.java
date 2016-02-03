package com.lesson.day09;

import java.util.List;


//考试的业务逻辑接口,定义和考试相关的业务方法
//(业务细节的抽象)
//定义接口的意义在于:利于后期的维护,通常将项目划分几个层次,
//每个层次间相互调用协同工作完成需求,实现代码 的解耦
public interface ExamService {
    //验证,将用户输入的id 和 密码和数据库重此用户的信息进行验证并返回此用户
    public User Login(int id,String pwd)throws LoginException;
    
    //生成试卷上考题的方法,返回考试信息对象
    public PaperExam start();
    
    //根据Papaer结合中下标获取试卷上的一道题
    public PaperQuestion getQuestion(int index);
    
  //根据考题题号,保存用户答案
    public void saveUserAnswers(int questionID,List<Integer> userAnswers);
    
    //考试结束,判断用户答案和正确答案,最终返回用户得分,
    public int getScore();
}
