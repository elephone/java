package com.www.day04;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MySnakeFrame extends JFrame{
	
	public mypanel mypanel;
	
	public MySnakeFrame() {
		this.setSize(470,483);
		this.setLocationRelativeTo(null);
		mypanel=new mypanel();
		mypanel.move();
		this.add(mypanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new MySnakeFrame();
	}
	
	class mypanel extends JPanel{
		public Image back;
		public Image cellimg;
		public Image foodimg;
		private static final int CELL_LENGTH=10;
		//��
		public Snake snake;
		//ʳ��
		public Cell food;
		
		//����,����
		public  static  final int ROWS=35;
		public static  final int COLS=35;
		
		
		public mypanel() {
//			back=new ImageIcon("src/com/www/day04/bg.png").getImage();
			back=Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("bg.png"));
			cellimg=Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("cell.png"));
			foodimg=Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("food.png"));
			snake=new Snake();
			food=createFood();
		}
		//�������ʳ�����x��y���꣩
		//ע��:ʳ���Ƿ��������ϣ�����ھ���������
		private Cell createFood() {
			Random random=new Random();
			Cell cells[]=snake.getCells();
			int x;
			int y;
			do{
				x=random.nextInt(ROWS);
				y=random.nextInt(COLS);
			}while(snake.contains(x,y));
			return new Cell(x, y);
		}

		public void paint(Graphics g){
			super.paint(g);
			g.drawImage(back, 0, 0, this);
			Cell [] cells=snake.getCells(); 
			//��ͼ�������ĵ�ԭ��ƽ�Ƶ�����ϵ�е�ĳһ��
			g.translate(54, 49);
		
			for(int i=0;i<cells.length;i++){
				Cell node=cells[i];
				g.drawImage(cellimg, node.getX()*CELL_LENGTH,node.getY()*CELL_LENGTH,this);
				
			}
			g.drawImage(foodimg, food.getX()*CELL_LENGTH,food.getY()*CELL_LENGTH,  this);
		}

		//���ƶ��ķ���
		public void move(){
			//��ʱ��,ÿ��50msִ��
			final Timer timer=new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					//���ƶ��߼�(�ж����Ƿ�ײ��ǽ�����Լ�,���߳Ե�ʳ��)
//					System.out.println(snake.hit(snake.getCurrentDirection()));
					if(snake.hit(snake.getCurrentDirection())){
						snake=new Snake();
						food=createFood();
					}else{
						//�ж����Ƿ�Ե�ʳ��
						boolean isEat=snake.creep(food); 
						if(isEat){
							food=createFood();
						}
					}
					repaint();
				}
			},0,100);
		
		}

	}
}
