package game.control;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;
import game.model.GameServer;
import game.model.OverListener;
import game.model.ScoreListener;
import game.model.Snake;
import game.util.GameConfig;
import game.util.GameImage;
import game.view.FrameGame;
import game.view.MenuGame;
import game.view.PanelInfo;

/**
 * 游戏中控，负责界面、业务模型的创建和组装，负责创建玩家控制器和玩家菜单并组装，负责游戏线程 和时间线程的管理，最后开启游戏、显示游戏、进行游戏方向操作。
 * 该类所起的主要作用就是完成游戏各个模块的创建及安装(这里的意思是就是在对象间建立联系)，
 * 
 * @author GuYongtao
 *
 */
public class GameController {
	// 成员属性
	private static FrameGame view; // 游戏主界面
	private MenuGame menu; // 游戏菜单
	private GameServer model; // 游戏服务器
	private PlayerController player; // 玩家控制器
	private Timer gameTimer; // 游戏线程，每190ms刷新一次
	private static Timer timer; // 时间显示线程，每秒刷新一次
	private static int timeValue = 0; // 时间计数
	private static int flag = 1;	// 游戏标致  1 最开始运行   2运行中暂停    3 暂停中运行
	private static int flag2 = 1;   // 0 暂停  1 运行
	
	/**
	 * 游戏线程事件处理，游戏服务器操作，界面刷新
	 * 
	 * @author GuYongtao
	 *
	 */
	private class GameHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.move();
			view.repaint();
		}
	}

	/**
	 * 时间线程事件处理，时间增一并刷新
	 * 
	 * @author GuYongtao
	 *
	 */
	private class TimeHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			timeValue++;
			view.refreshTime(timeValue);
		}
	}

	/**
	 * 计分时间处理，刷新即可
	 * 
	 * @author GuYongtao
	 *
	 */
	private class ScoreHandler implements ScoreListener {

		@Override
		public void report(int score) {
			view.refreshScore(score);
		}

	}

	/**
	 * 游戏结束事件处理，关闭定时器
	 * 
	 * @author GuYongtao
	 *
	 */
	private class OverHandler implements OverListener {

		@Override
		public void overGame() {
			GameController.flag = 1;
			timer.stop();
			gameTimer.stop();
		}

	}


	// 构造方法
	public GameController() {
		// 创建游戏服务器对象，并制定计分和计时监听器
		model = new GameServer();
		model.addScoreListener(new ScoreHandler());
		model.addOverListener(new OverHandler());
//		model.addPauseListener(new PauseHandler());
		
		// 创建玩家控制器，通过参数this使游戏中控和玩家控制器连接
		player = new PlayerController(this);

		// 创建游戏主界面，并定制开始按钮监听器和键盘监听器
		view = new FrameGame(model);
		view.addStartListener(player);
		view.addKeyListener(player);

		// 创建游戏菜单对象，并完成国际化与本地化处理
		ResourceBundle bundle = ResourceBundle.getBundle("game/i18n/base");
		menu = new MenuGame(bundle, this);
		view.setJMenuBar(menu);
		view.setTitle(bundle.getString("title"));

		// 创建游戏线程和时间线程(可以只用一个线程，自己思考扩展)
		gameTimer = new Timer(190, new GameHandler());
		timer = new Timer(1000, new TimeHandler());
	}

	/**
	 * 开启游戏
	 */
	public void start() {
		if (GameController.flag == 1) {
			GameController.flag = 2;  // 点击后即将处于暂停
			model.start();
			view.refreshScore(0);
			view.refreshTime(0);
			timeValue = 0;
			gameTimer.start();
			timer.start();
			ImageIcon running = new ImageIcon("imgs/running.png");
			PanelInfo.getBtnStart().setIcon(running);
		} else if (GameController.flag == 2) {
			GameController.flag2 = 0;
			GameController.flag = 3;
			ImageIcon pause = new ImageIcon("imgs/start.png");
			PanelInfo.getBtnStart().setIcon(pause);
			timer.stop();
		} else if (GameController.flag == 3) {
			GameController.flag2 = 1;
			GameController.flag = 2;
			ImageIcon start = new ImageIcon("imgs/running.png");
			PanelInfo.getBtnStart().setIcon(start);
			timer.start();
		}
	}
	
	/**
	 * 重启游戏
	 */
	public void pause() {
		
	}

	/**
	 * 游戏方向操作
	 * 
	 * @param name
	 */
	public void turnTo(String name) {
		model.turnTo(name);
	}

	public void show() {
		view.setVisible(true);
		Insets is = view.getInsets(); // 界面除客户区外的上下左右区域
		view.setSize(GameConfig.getCanvasWidth() + is.left * 2,
				GameConfig.getCanvasHeight() + 96 + is.top + is.bottom);
		view.setLocationRelativeTo(null);	// 居中
	}

	public static Timer getTimer() {
		return timer;
	}

	public static void setTimer(Timer timer) {
		GameController.timer = timer;
	}

	public static int getFlag() {
		return flag;
	}

	public static void setFlag(int flag) {
		GameController.flag = flag;
	}

	public static FrameGame getView() {
		return view;
	}

	public static void setView(FrameGame view) {
		GameController.view = view;
	}

	public static int getTimeValue() {
		return timeValue;
	}

	public static void setTimeValue(int timeValue) {
		GameController.timeValue = timeValue;
	}

	public static int getFlag2() {
		return flag2;
	}

	public static void setFlag2(int flag2) {
		GameController.flag2 = flag2;
	}
	
	
}
