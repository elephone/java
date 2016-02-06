package com.lesson.day07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//DOA��,������װ��Wie����,
//��������� �Ľ������ļ��е�����ת��Ϊjava����
import java.util.Map;

public class EntityContext {
    //LoadProperties����,���ڶ�ȡϵͳ�����ļ�����Ϣ
    private LoadProperties load;
    public EntityContext(LoadProperties load){
	this.load = load;
	
	//���ؿ�����Ϣ
	loadUsers("src/com/lesson/day07/"+load.getString("UserFile"));
	//����QuestionFile
	LoadQuestions("src/com/lesson/day07/"+load.getString("QuestionFile"));
    }
    
    
    
    
    
    //���ڱ���ȫ��������hashMap,key:������ID,value :��������
    private Map<Integer, User> users = new  HashMap<Integer, User>();
    public void loadUsers(String fileName){
	try {
	    FileInputStream fis = new FileInputStream(fileName);//�����������ܵ�
	    InputStreamReader reader =  new InputStreamReader(fis,"GBK");//�ļ������ֽ����س�������
	    BufferedReader br = new BufferedReader(reader);//������
	    
	    //���ڱ���ÿ�ζ�ȡ����һ���ַ���
	    String msg = "";//�ն���
	    while((msg = br.readLine()) != null){//�����ļ�ĩβ
		
		msg = msg.trim();//ȥ�����˵Ŀհ�
		
		//�ж��Ƿ���Ч
		if(msg.equals("")|| msg.startsWith("#")){//�Կ��ַ���,#��ͷ��,
		    continue;//��������ѭ�� 
		}
		//�����д�������Ϣ���ַ���  ������User����
		User user =  ParseUser(msg);
		
		//��user���浽user���hashMap��
		users.put(user.getId(), user);
		
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    
    //������һ��������Ϣ���ַ���ת����user����
    public User ParseUser(String msg){
	//1000:������:1234:13810381038:ninglj@tarena.com.cn
	//��:�и���
	String [] msgs = msg.split(":");
	//����user����
	User user = new User();
	//����ֵ���Ϣ�洢��user�����Ӧ��������
	user.setId(Integer.parseInt(msgs[0]));
	user.setName(msgs[1]);
	user.setPassword(msgs[2]);
	user.setPhone(msgs[3]);
	user.setEmail(msgs[4]);
	return user;
    }
    //���ݿ�����id��ѯ��������
    public User findUserByID(int id){
	return users.get(id);
    }
    /****************************************************/
    //���ڱ���ȫ����������Ϣ
    //key:��������׶�  value:�����Ӧ���׶ȵ�ȫ������ļ���list
    private Map<Integer, List<Question>> questions = new HashMap<Integer, List<Question>>() ;
    
    //�������ļ��е��ƶ���¼������ļ�������Questions,�����浽questions������
    public void LoadQuestions(String fileName){
	try {
	    FileInputStream fis = new FileInputStream(fileName);//�����������ܵ�
	    InputStreamReader reader =  new InputStreamReader(fis,"GBK");//�ļ������ֽ����س�������
	    BufferedReader br = new BufferedReader(reader);//������
	    
	    //���ڱ���ÿ�ζ�ȡ����һ���ַ���
	    String msg = "";//�ն���
	    while((msg = br.readLine()) != null){//�����ļ�ĩβ
		
		msg = msg.trim();//ȥ�����˵Ŀհ�
		
		//�ж��Ƿ���Ч
		if(msg.equals("")|| msg.startsWith("#")){//�Կ��ַ���,#��ͷ��,
		    continue;//��������ѭ�� 
		}
		
		//����һ������
		Question question =  ParseQuestion(msg,br);
		
		//���������Ŀ�����뼯��questions��,��һ���û�����
//		users.put(user.getId(), user);
		AddQuestionToMap(question);
		
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    
    //��������ķ���,������Ϣ(��ȷ�� ,����,�Ѷ�)
    //��������:��һ�����,2-5��ѡ��
    public Question ParseQuestion(String msg, BufferedReader br)throws Exception{
	Question question = new Question();
	//1.����������Ϣ 
	//@answer=2/3,score=5,level=5
	String []data = msg.split("[@,][a-z]+=");//������ʽ,�г�����4��Ԫ��,
	//data={"",2/3,5,5}
	
	question.setAnswer(parseAnswers(data[1]));
	question.setScore(Integer.parseInt(data[2]));
	question.setLevel(Integer.parseInt(data[3]));
	/////////////  ��������   ///////////////////
	//2.ʹ����������������Ϣ,��ȡ��һ������
	question.setTitle(br.readLine());
	//��ȡ����,
	question.getOptions().add(br.readLine());
	question.getOptions().add(br.readLine());
	question.getOptions().add(br.readLine());
	question.getOptions().add(br.readLine());
	
	
	//����type
	question.setType(question.getAnswer().size()>1?Question.TYPE_MULTI_SELECT:Question.TYPE_SINGLE_SELECT);
	return question;
    }
    
    
    //����ȷ�𰸵��ַ������ת����int �����list������
    private List<Integer> parseAnswers(String answer){
	List<Integer> answers = new ArrayList<Integer>();
	//answer:2/3   2 3
	String []data = answer.split("/");
	//��data �еı��int 
	for(String msg:data){
	    answers.add(Integer.parseInt(msg));
	}
	return answers;
    }
    
    //��һ�����⣬�������׶ȴ����ü�����
    
    private void AddQuestionToMap(Question question ){
	//���ݿ������׶ȣ���question�в�ѯ�����׶ȼ����Ƿ�֮ǰ��װ��ļ��ϣ����û��
	//��list==null�򴴽��µ� ����list������������ȥ������У���ֱ�ӰѴ�����ӵ�������
	List<Question> list = questions.get(question.getLevel());
	if(list == null){//С���ϲ�����
	    list = new ArrayList<Question>();
	    questions.put(question.getLevel(),list);
	}
	list.add(question);
	
    }
    
    //�������׶ȣ�������Ӧ���׶ȵ�ȫ������
    public List<Question> findQuestionByLevel(int level){
	return  questions.get(level);
    }

    
}
