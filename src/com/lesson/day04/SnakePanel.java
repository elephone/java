package com.lesson.day04;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import com.homework.day3.Panel;

public class SnakePanel extends JPanel{
    private final int Cell_SIZE = 10;
    private final int ROWS = 35, COLS = 35;
    private static Image background ; 
    //蛇
    private Snake snake;
    private static Image snakeImage;//蛇结点图片
    //食物
    private static Image foodImage;
    private Cell food;
   
//    private final int STATR_X = 49;
//    private final int STATR_Y = 40;
    public SnakePanel() {
	//加载背景图片，蛇，食物图片
	background  = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("bg.png"));
	snake = new Snake();
	snakeImage = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("cell.png"));
	foodImage = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("food.png"));
    }
    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        
        
        g.drawImage(background, 0, 0, this);
        //将图形上下文的原点平移到坐标系中(X,Y)
        g.translate(52, 49);
        //画蛇
       
        Cell Cells[] = snake.GetCells();
        for(int i=0; i<Cells.length; i++){
            Cell node = Cells[i];
            g.drawImage(snakeImage, Cells[i].getX()*Cell_SIZE, Cells[i].getY()*Cell_SIZE, this);
        }
        //画食物
        food = CreateFood();//随机生成食物的方法->随机生成x,y坐标（在游戏区域中，不能生成到蛇身上，如果在身上要重新生成）
        g.drawImage(foodImage, food.getX()*Cell_SIZE, food.getY()*Cell_SIZE,this);
       
    }
    
    //蛇移动的
    public void move(){
	final Timer timer =  new Timer();//创建定时器对象
	timer.schedule(new TimerTask() {
	    
	    @Override
	    public void run() {
		// 移动逻辑 判断蛇是否撞到墙，撞到自己，判断蛇是否吃到食物，
		if(snake.hit(snake.getCurrentDirection())){// 判断蛇是否撞到墙，撞到自己
		    snake = new Snake();
		    food = CreateFood();
		}else{
		    boolean isEat = snake.Creep(food);//蛇是否吃到食物，
		    if(isEat){
			food = CreateFood();
		    }
		}
		repaint();		
	    }
	}, 0, 100);
    }
    
    private Cell CreateFood(){
	Cell food = new Cell((int)(Math.random()*35),(int)(Math.random()*35));
	Cell bodys[] = snake.GetCells();
	
	int i=0;
	while(i<bodys.length){
	    if((bodys[i].getX()== food.getX())&&(bodys[i].getY()== food.getY())){
		
		i=0;
	    }
	    i++;
	}
	
	
	return new Cell(food.getX(),food.getY());
    }
}
