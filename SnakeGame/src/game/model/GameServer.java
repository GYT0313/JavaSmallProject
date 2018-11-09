package game.model;

import game.util.GameConfig;
import java.awt.Graphics;
import java.util.Random;


/**
 * 游戏服务器，采用外观模式(满足迪米特法则)封装业务模型，是业务模型对外提供的唯一接口。
 * 包含蛇对象、食物对象、游戏得分、游戏结束监听器引用、
 * 游戏得分监听器引用，对外提供游戏开始、游戏绘制、游戏进行等方法，
 * 并提供事件定制方法及事件触发的时机实现
 * 
 * @author GuYongtao
 *
 */

public class GameServer {
	// 成员属性
	private Snake snake;		// 蛇对象
	private Node food;			// 食物对象
	private int score = 0;		// 游戏得分
	private ScoreListener scoreListener;	// 得分监听器引用
	private OverListener overListener;		// 结束监听器引用
	
	/**
	 * 事件定制方法，即监听器引用初始化操作
	 * @param scoreListener
	 */
	public void addScoreListener(ScoreListener scoreListener) {
		this.scoreListener = scoreListener;
	}
	
	/**
	 * 事件定制方法，即监听器引用初始化操作
	 * @param overListener
	 */
	public void addOverListener(OverListener overListener) {
		this.overListener = overListener;
	}
	
	
	// 构造方法
	public GameServer() {
		snake = new Snake();
	}
	
	
	public void start() {
		score = 0;
		snake.defaultInit();
		createFood();
	}
	
	/**
	 * 游戏进行中，即蛇边移动边吃食物，如果没有吃到食物就碰撞检测和移动
	 * 否则通知蛇增长，产生新食物，计算得分并通知事件使用者进行界面刷新显示
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
	 * 根据方向名，通知蛇修改移动方向
	 * @param name
	 */
	public void turnTo(String name) {
		Direction d = Direction.valueOf(name);  // string类型转为enum类型
		snake.turnTo(d);
	}
	
	/**
	 * 游戏绘制，包括蛇和食物的绘制
	 * @param g
	 */
	public void draw(Graphics g) {
		snake.draw(g);
		if (food != null) {
			food.draw(g);
		}
	}
	
	/**
	 * 随机位置产生食物，过程中要检查食物是否合法
	 */
	private void createFood() {
		Random random = new Random();
		while(true) {
			int r = random.nextInt(GameConfig.getRows());
			int c = random.nextInt(GameConfig.getCols());
			Node n = new Node(r, c, NodeType.Food);
			if (snake.checkOverlap(n)) {  // 检查食物是否在蛇身上
				food = n;
				break;
			}
		}
	}
	
	
	/**
	 * 计算游戏得分，目前规则为吃到一个食物加1分，计分规则可自行修改
	 */
	private void calcScore() {
		score++;
	}
	
	/**
	 * 检测并移动，合法移动，否则通知事件使用者进行游戏结束处理
	 */
	private void checkAndMove() {
		if (snake.validate()) {  // 检测合法就移动
			snake.move();
		} else if (overListener != null) { // 不合法就结束游戏
			overListener.overGame();
		}
	}
	
	
}
