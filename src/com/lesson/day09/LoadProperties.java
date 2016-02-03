

package com.lesson.day09;

import java.io.FileInputStream;
import java.util.Properties;

//此类负责读取并解析配置文件（.properties)
public class LoadProperties {
    //java系统提供的api用于解析.properties文件
    //其内部结构是一个map集合，将.properties文件的内容解析成
    //timelimit = 30,解析问 Map对象中Entey对象的一个元素
    //key = timelimit, value=30
    private Properties map = new Properties();
    //根据配置文件名，创建LoadProperties对象
    public LoadProperties(String fileName){
	try {
	    map.load(new FileInputStream(fileName));//输入流
	    
	} catch (Exception e) {
	    e.printStackTrace();
	    
	}
    }//
    
    //根据给定 的key值渠道对应的value值
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
