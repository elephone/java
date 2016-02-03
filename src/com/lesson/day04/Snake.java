package com.lesson.day04;

import java.util.Arrays;

public class Snake {
    private static final int UP = 1;
    private static final int DOWN = -1;
    private static final int LEFT = 2;
    private static final int RIGHT = -2;
    
    
    //定义蛇初始化长度
    private static final int DEFAULT_LENGTH = 10;
    private Cell[] cells;
    
    
    //蛇当前运动方向
    
    private int  currentDirection = DOWN;
    //定义蛇下一时刻运动方向
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
	return Arrays.copyOf(cells, cells.length);//为了保留初始值
    }
    
    //判断蛇是否撞墙，撞自己的方法
    public boolean hit(int direction){

	this.nextDirection = direction;
	//如果蛇进行反向操作，则方法结束
	if((currentDirection + nextDirection) == 0){//判断没有撞
	    return false;
	}
	Cell head =  CreateHead(this.nextDirection);
	//判断蛇是否撞自己
	for(int i=4; i<cells.length-1; i++){//最后一个小方块撞不到
	    Cell node=  cells[i];
	    if((node.getX() == head.getX())&&(node.getY() == head.getY())){
		return true;
	    }
	}
	//判断是否撞墙
	if(head.getX()<0 || head.getX()>=35||head.getY()<0||head.getY()>=35){
	    return true;
	}
	return false;    
    }
    //生成头结点的方法
    public Cell CreateHead(int direction){
	//当前头结点的坐标
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
    //蛇爬行的方法
    public boolean Creep(Cell food){//方向不变
	return Creep(currentDirection, food);
    }
    public boolean Creep(int direction,Cell food){//方向变动
	nextDirection = direction;
	
	if((currentDirection + nextDirection) == 0){
	    return false;
	}
	currentDirection = nextDirection;
	Cell head = CreateHead(currentDirection);
	boolean isEat = (head.getX() == food.getX()
		&& head.getY() == food.getY());
	
	if(isEat){//蛇下一刻要吃到食物->数组要扩容，
	    cells = Arrays.copyOf(cells, cells.length+1);//新数组长度 比 原来的多一位
	    //cells[cells.length] = cells[cells.length-1];
	}
	for(int i=cells.length-1; i>0; i--){
	    cells[i] = cells[i-1];
	}
	
	cells[0] = head;
	return isEat;
    }
}
