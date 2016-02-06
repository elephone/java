package com.www.day04;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MySnakeFrame extends JFrame{
	
	public mypanel mypanel;
	boolean isExit=false;
	public MySnakeFrame() {
		this.setSize(470,483);
		this.setLocationRelativeTo(null);
		mypanel=new mypanel();
		
		mypanel.move();
		
		this.add(mypanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		mypanel.requestFocus();
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
		public Timer timer;
		
		public mypanel() {
//			back=new ImageIcon("src/com/www/day04/bg.png").getImage();
			back=Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("bg.png"));
			cellimg=Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("cell.png"));
			foodimg=Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("food.png"));
			snake=new Snake();
			food=createFood();
			timer=new Timer();
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
			
			
			timer.schedule(new TimerTask() {
				public void run() {
					//���ƶ��߼�(�ж����Ƿ�ײ��ǽ�����Լ�,���߳Ե�ʳ��)
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
			//����ǰ�齨��ȡ���뽹��
			
			this.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					int key=e.getKeyCode();
					boolean isEat;
					
					switch(key){
						case KeyEvent.VK_UP:
							isEat=snake.creep(Snake.UP,food);
							if(isEat){
								food=createFood();
							}
							break;
						case KeyEvent.VK_DOWN:
							isEat=snake.creep(Snake.DOWN,food);
							if(isEat){
								food=createFood();
							}
							break;
						case KeyEvent.VK_LEFT:
							isEat=snake.creep(Snake.LEFT,food);
							if(isEat){
								food=createFood();
							}
							break;
						case KeyEvent.VK_RIGHT:
							isEat=snake.creep(Snake.RIGHT,food);
							if(isEat){
								food=createFood();
							}
							break;
						case KeyEvent.VK_SPACE:
							isExit=true;
							timer.cancel();
							
							break;
						case KeyEvent.VK_ENTER:
							if(isExit){
								isExit=false;
								timer=new Timer();
								timer.schedule(new TimerTask() {
									public void run() {
										//���ƶ��߼�(�ж����Ƿ�ײ��ǽ�����Լ�,���߳Ե�ʳ��)
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
							break;
					}
				}
			});
		}

	}
}
