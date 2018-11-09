package game.model;

import game.util.GameConfig;
import java.awt.Graphics;
import java.util.Random;


/**
 * ��Ϸ���������������ģʽ(��������ط���)��װҵ��ģ�ͣ���ҵ��ģ�Ͷ����ṩ��Ψһ�ӿڡ�
 * �����߶���ʳ�������Ϸ�÷֡���Ϸ�������������á�
 * ��Ϸ�÷ּ��������ã������ṩ��Ϸ��ʼ����Ϸ���ơ���Ϸ���еȷ�����
 * ���ṩ�¼����Ʒ������¼�������ʱ��ʵ��
 * 
 * @author GuYongtao
 *
 */

public class GameServer {
	// ��Ա����
	private Snake snake;		// �߶���
	private Node food;			// ʳ�����
	private int score = 0;		// ��Ϸ�÷�
	private ScoreListener scoreListener;	// �÷ּ���������
	private OverListener overListener;		// ��������������
	
	/**
	 * �¼����Ʒ����������������ó�ʼ������
	 * @param scoreListener
	 */
	public void addScoreListener(ScoreListener scoreListener) {
		this.scoreListener = scoreListener;
	}
	
	/**
	 * �¼����Ʒ����������������ó�ʼ������
	 * @param overListener
	 */
	public void addOverListener(OverListener overListener) {
		this.overListener = overListener;
	}
	
	
	// ���췽��
	public GameServer() {
		snake = new Snake();
	}
	
	
	public void start() {
		score = 0;
		snake.defaultInit();
		createFood();
	}
	
	/**
	 * ��Ϸ�����У����߱��ƶ��߳�ʳ����û�гԵ�ʳ�����ײ�����ƶ�
	 * ����֪ͨ��������������ʳ�����÷ֲ�֪ͨ�¼�ʹ���߽��н���ˢ����ʾ
	 */
	public void move() {
		if (!snake.isEat(food)) {
			checkAndMove();
		} else {
			snake.grow(food);
			createFood();
			calcScore();
			if (scoreListener != null) {
				scoreListener.report(score);
			}
		}
	}
	
	/**
	 * ���ݷ�������֪ͨ���޸��ƶ�����
	 * @param name
	 */
	public void turnTo(String name) {
		Direction d = Direction.valueOf(name);  // string����תΪenum����
		snake.turnTo(d);
	}
	
	/**
	 * ��Ϸ���ƣ������ߺ�ʳ��Ļ���
	 * @param g
	 */
	public void draw(Graphics g) {
		snake.draw(g);
		if (food != null) {
			food.draw(g);
		}
	}
	
	/**
	 * ���λ�ò���ʳ�������Ҫ���ʳ���Ƿ�Ϸ�
	 */
	private void createFood() {
		Random random = new Random();
		while(true) {
			int r = random.nextInt(GameConfig.getRows());
			int c = random.nextInt(GameConfig.getCols());
			Node n = new Node(r, c, NodeType.Food);
			if (snake.checkOverlap(n)) {  // ���ʳ���Ƿ���������
				food = n;
				break;
			}
		}
	}
	
	
	/**
	 * ������Ϸ�÷֣�Ŀǰ����Ϊ�Ե�һ��ʳ���1�֣��Ʒֹ���������޸�
	 */
	private void calcScore() {
		score++;
	}
	
	/**
	 * ��Ⲣ�ƶ����Ϸ��ƶ�������֪ͨ�¼�ʹ���߽�����Ϸ��������
	 */
	private void checkAndMove() {
		if (snake.validate()) {  // ���Ϸ����ƶ�
			snake.move();
		} else if (overListener != null) { // ���Ϸ��ͽ�����Ϸ
			overListener.overGame();
		}
	}
	
	
}
