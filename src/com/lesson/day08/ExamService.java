package com.lesson.day08;


//���Ե�ҵ���߼��ӿ�,����Ϳ�����ص�ҵ�񷽷�
//(ҵ��ϸ�ڵĳ���)
//����ӿڵ���������:���ں��ڵ�ά��,ͨ������Ŀ���ּ������,
//ÿ����μ��໥����Эͬ�����������,ʵ�ִ��� �Ľ���
public interface ExamService {
    //��֤,���û������id �� ��������ݿ��ش��û�����Ϣ������֤�����ش��û�
    public User Login(int id,String pwd)throws LoginException;
    
    //�����Ծ��Ͽ���ķ���,���ؿ�����Ϣ����
    public PaperExam start();
    
    //����Papaer������±��ȡ�Ծ��ϵ�һ����
    public PaperQuestion getQuestion(int index);
}
