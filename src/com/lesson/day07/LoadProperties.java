package com.lesson.day07;

import java.io.FileInputStream;
import java.util.Properties;

//���ฺ���ȡ�����������ļ���.properties)
public class LoadProperties {
    //javaϵͳ�ṩ��api���ڽ���.properties�ļ�
    //���ڲ��ṹ��һ��map���ϣ���.properties�ļ������ݽ�����
    //timelimit = 30,������ Map������Entey�����һ��Ԫ��
    //key = timelimit, value=30
    private Properties map = new Properties();
    //���������ļ���������LoadProperties����
    public LoadProperties(String fileName){
	try {
	    map.load(new FileInputStream(fileName));//������
	    
	} catch (Exception e) {
	    e.printStackTrace();
	    
	}
    }//
    
    //���ݸ��� ��keyֵ������Ӧ��valueֵ
    public String getString (String key){
	return map.getProperty(key);
    }
    public Integer getInt(String key){
	return Integer.parseInt(map.getProperty(key));
    }
    public  Double getDouble(String  key){
	return Double.parseDouble(map.getProperty(key));
    }
}
