package com.www.day04;

//小方块类,代表蛇身上的节点或者食物
public class Cell {
	private int x;
	private int y;
	
	public Cell() {
	}
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}


	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
