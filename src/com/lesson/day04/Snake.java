package com.lesson.day04;

import java.util.Arrays;

public class Snake {
    private static final int UP = 1;
    private static final int DOWN = -1;
    private static final int LEFT = 2;
    private static final int RIGHT = -2;
    
    
    //�����߳�ʼ������
    private static final int DEFAULT_LENGTH = 10;
    private Cell[] cells;
    
    
    //�ߵ�ǰ�˶�����
    
    private int  currentDirection = DOWN;
    //��������һʱ���˶�����
    private int nextDirection;
    
    
    public int getCurrentDirection() {
        return currentDirection;
    }
    public void setCurrentDirection(int currentDirection) {
        this.currentDirection = currentDirection;
    }
    public int getNextDirection() {
        return nextDirection;
    }
    public void setNextDirection(int nextDirection) {
        this.nextDirection = nextDirection;
    }
  
    
    public Snake() {
	cells = new Cell[DEFAULT_LENGTH];
	for(int i=0; i<cells.length; i++){
	    cells[i] = new Cell(i,0);
	}
    }
    public Cell[] GetCells(){
	return Arrays.copyOf(cells, cells.length);//Ϊ�˱�����ʼֵ
    }
    
    //�ж����Ƿ�ײǽ��ײ�Լ��ķ���
    public boolean hit(int direction){

	this.nextDirection = direction;
	//����߽��з���������򷽷�����
	if((currentDirection + nextDirection) == 0){//�ж�û��ײ
	    return false;
	}
	Cell head =  CreateHead(this.nextDirection);
	//�ж����Ƿ�ײ�Լ�
	for(int i=4; i<cells.length-1; i++){//���һ��С����ײ����
	    Cell node=  cells[i];
	    if((node.getX() == head.getX())&&(node.getY() == head.getY())){
		return true;
	    }
	}
	//�ж��Ƿ�ײǽ
	if(head.getX()<0 || head.getX()>=35||head.getY()<0||head.getY()>=35){
	    return true;
	}
	return false;    
    }
    //����ͷ���ķ���
    public Cell CreateHead(int direction){
	//��ǰͷ��������
	int x = cells[0].getX();
	int y = cells[0].getY();
	switch(direction){
	case DOWN:
	    y++;
	    break;
	case UP:
	    y--;
	    break;
	case LEFT:
	    x--;
	    break;
	case RIGHT:
	    x++;
	    break;
	}
	return new Cell(x,y);
    }
    //�����еķ���
    public boolean Creep(Cell food){//���򲻱�
	return Creep(currentDirection, food);
    }
    public boolean Creep(int direction,Cell food){//����䶯
	nextDirection = direction;
	
	if((currentDirection + nextDirection) == 0){
	    return false;
	}
	currentDirection = nextDirection;
	Cell head = CreateHead(currentDirection);
	boolean isEat = (head.getX() == food.getX()
		&& head.getY() == food.getY());
	
	if(isEat){//����һ��Ҫ�Ե�ʳ��->����Ҫ���ݣ�
	    cells = Arrays.copyOf(cells, cells.length+1);//�����鳤�� �� ԭ���Ķ�һλ
	    //cells[cells.length] = cells[cells.length-1];
	}
	for(int i=cells.length-1; i>0; i--){
	    cells[i] = cells[i-1];
	}
	
	cells[0] = head;
	return isEat;
    }
}
