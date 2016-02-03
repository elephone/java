package com.lesson.day08;
//自定义异常,用于描述用户输入的ID或密码错误
public class LoginException extends Exception{
    public LoginException() {
	super();
    }
    public LoginException(String message){
	super(message);
    }
    public LoginException(Throwable cause){//导致异常原因
	super(cause);
    }
    
    public LoginException(String message, Throwable cause){
	super(message, cause);
    }
    
    
}
