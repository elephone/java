package com.www.day04;

import java.util.ArrayList;
import java.util.Arrays;

import com.www.day04.MySnakeFrame.mypanel;

public class Snake {
	//fianl ���������࣬����������д�����������޸�
	private static final int DEFAULT_LENGTH=10;
	//С����������ʾ��
	private Cell cells[];
	private static final int UP=1;
	private static final int DOWN=-1;
	private static final int LEFT=2;
	private static final int RIGHT=-2;
	//�����ߵ�ǰ���˶�����
	private int currentDirection;
	//��������һʱ�̵��˶�����
	private int nextDirection;
	
	public Snake() {
		//��ʼ���ߵķ���
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
	
	//�ж����Ƿ�ײǽ�����Լ��ķ���
	public boolean hit(int direction) {
		this.nextDirection=direction;
//		System.out.println(nextDirection);
		//����߽��з�����������
		if(nextDirection+currentDirection==0){
			return false;
		}
		//����ͷ��������һλ�õõ��ߵ���һ������λ��
//		System.out.println(this.nextDirection);
		Cell head=createHead(this.nextDirection);
		
		//�ж����Ƿ�ײǽ
		if(head.getX()<0 || head.getX()>=mypanel.ROWS
				|| head.getY()<0 || head.getY()>=mypanel.COLS){
			return true;
		}
		
		//�ж����Ƿ�ײ�Լ�
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

	public boolean creep(Cell food) {
		return creep(currentDirection,food);
	}
	
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
