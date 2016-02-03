package com.www.day04;

import java.util.Arrays;

import com.www.day04.MySnakeFrame.mypanel;

public class Snake {
	//fianl 不能有子类，方法不能重写，变量不能修改
	private static final int DEFAULT_LENGTH=10;
	//小方块数组显示蛇
	private Cell cells[];
	static final int UP=1;
	static final int DOWN=-1;
	static final int LEFT=2;
	static final int RIGHT=-2;
	//定义蛇当前的运动方向
	private int currentDirection;
	public void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
	}

	public void setNextDirection(int nextDirection) {
		this.nextDirection = nextDirection;
	}

	//定义蛇下一时刻的运动方向
	private int nextDirection;
	
	public Snake() {
		//初始化蛇的方向
		currentDirection=DOWN;
		cells =new Cell[DEFAULT_LENGTH];
		for(int i=0;i<cells.length;i++){
			cells[i]=new Cell(i, 0);
		}
	}

	public Cell[] getCells() {
		return Arrays.copyOf(cells, cells.length);
	}

	public boolean contains(int x,int y){
		for(int i=0;i<cells.length;i++){
			Cell node=cells[i];
			if(node.getX()==x&&node.getY()==y){
				return true;
			}
		}
		return false;
	}
	
	//判断蛇是否撞墙或者自己的方法
	public boolean hit(int direction) {
		this.nextDirection=direction;
		//如果蛇进行反向操作则结束
		if(nextDirection+currentDirection==0){
			return false;
		}
		//根据头结点和蛇下一位置得到蛇的下一个坐标位置
		Cell head=createHead(this.nextDirection);
		
		//判断蛇是否撞墙
		if(head.getX()<0 || head.getX()>=mypanel.ROWS
				|| head.getY()<0 || head.getY()>=mypanel.COLS){
			return true;
		}
		
		//判断蛇是否撞自己
		for(int i=4;i<cells.length-1;i++){
			Cell node=cells[i];
			if(node.getX()==head.getX() && node.getY()==head.getY()){
				return true;
			}
		}
		return false;
	}

	public Cell createHead(int direction) {
		Cell currenthead=cells[0];
		int x = currenthead.getX();
		int y = currenthead.getY();
		
		switch(direction){
			case DOWN:
				y++;break;
			case UP:
				y--;break;
			case LEFT:
				x--;break;
			case RIGHT:
				x++;break;
		}
		return new Cell(x,y);
	}
//是否吃到食物
	public boolean creep(Cell food) {
		return creep(currentDirection,food);
	}
	//蛇爬行
	public boolean creep(int direction,Cell food){
		nextDirection=direction;
		boolean isEat=false;
		if(currentDirection+nextDirection==0){
			return false;
		}else{
			currentDirection=nextDirection;
			Cell head=createHead(currentDirection);
			isEat=(head.getX()==food.getX() && head.getY()==food.getY());
			if(isEat){
				cells=Arrays.copyOf(cells,cells.length+1);
			}
			for(int i=cells.length-1;i>=1;i--){
				cells[i]=cells[i-1];
			}
			cells[0]=head;
		}
		return isEat;
	}

	public int getCurrentDirection() {
		return currentDirection;
	}
	
	public int getNextDirection() {
		return nextDirection;
	}
}
