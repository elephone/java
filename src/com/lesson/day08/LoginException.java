package com.lesson.day08;
//�Զ����쳣,���������û������ID���������
public class LoginException extends Exception{
    public LoginException() {
	super();
    }
    public LoginException(String message){
	super(message);
    }
    public LoginException(Throwable cause){//�����쳣ԭ��
	super(cause);
    }
    
    public LoginException(String message, Throwable cause){
	super(message, cause);
    }
    
    
}
