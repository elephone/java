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
    //��
    private Snake snake;
    private static Image snakeImage;//�߽��ͼƬ
    //ʳ��
    private static Image foodImage;
    private Cell food;
   
//    private final int STATR_X = 49;
//    private final int STATR_Y = 40;
    public SnakePanel() {
	//���ر���ͼƬ���ߣ�ʳ��ͼƬ
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
        //��ͼ�������ĵ�ԭ��ƽ�Ƶ�����ϵ��(X,Y)
        g.translate(52, 49);
        //����
       
        Cell Cells[] = snake.GetCells();
        for(int i=0; i<Cells.length; i++){
            Cell node = Cells[i];
            g.drawImage(snakeImage, Cells[i].getX()*Cell_SIZE, Cells[i].getY()*Cell_SIZE, this);
        }
        //��ʳ��
        food = CreateFood();//�������ʳ��ķ���->�������x,y���꣨����Ϸ�����У��������ɵ������ϣ����������Ҫ�������ɣ�
        g.drawImage(foodImage, food.getX()*Cell_SIZE, food.getY()*Cell_SIZE,this);
       
    }
    
    //���ƶ���
    public void move(){
	final Timer timer =  new Timer();//������ʱ������
	timer.schedule(new TimerTask() {
	    
	    @Override
	    public void run() {
		// �ƶ��߼� �ж����Ƿ�ײ��ǽ��ײ���Լ����ж����Ƿ�Ե�ʳ�
		if(snake.hit(snake.getCurrentDirection())){// �ж����Ƿ�ײ��ǽ��ײ���Լ�
		    snake = new Snake();
		    food = CreateFood();
		}else{
		    boolean isEat = snake.Creep(food);//���Ƿ�Ե�ʳ�
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
