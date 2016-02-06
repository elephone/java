package com.lesson.day07;

//User��,����һ����������Ϣ,ÿһ��userʵ��
//��¼5����Ϣ,��Ӧuser.txt�ļ�������
//��һһ������
public class User {
    private int id;
    private String name;
    private String password;
    private String phone;
    private String email;
    
    public User(){
	this.id = 0;
	this.name = "";
	this.password = "";
	this.phone = "";
	this.email = "";
    }
    public User(int id, String name, String password, String phone, String email) {
	
	this.id = id;
	this.name = name;
	this.password = password;
	this.phone = phone;
	this.email = email;
    }
    
    //������ǰ�ı���Ϣ
    public String toString(){
	return name;
    }
    
    public boolean equals(Object obj){//��дequal����
	if(obj == null){
	    return false;
	}
	if(obj == this){
	    return true;
	}
	if(obj instanceof User){//�жϱ�����ָ��Ķ����Ƿ���ͬ���͵�
	    User user = (User)obj;
	    return this.id == user.id;
	}
	return false;
    }
    
    public int hashCode(){
	return (int)(100000*Math.random());
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
