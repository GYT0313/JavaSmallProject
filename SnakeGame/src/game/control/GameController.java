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
 * ��Ϸ�пأ�������桢ҵ��ģ�͵Ĵ�������װ�����𴴽���ҿ���������Ҳ˵�����װ��������Ϸ�߳� ��ʱ���̵߳Ĺ����������Ϸ����ʾ��Ϸ��������Ϸ���������
 * �����������Ҫ���þ��������Ϸ����ģ��Ĵ�������װ(�������˼�Ǿ����ڶ���佨����ϵ)��
 * 
 * @author GuYongtao
 *
 */
public class GameController {
	// ��Ա����
	private static FrameGame view; // ��Ϸ������
	private MenuGame menu; // ��Ϸ�˵�
	private GameServer model; // ��Ϸ������
	private PlayerController player; // ��ҿ�����
	private Timer gameTimer; // ��Ϸ�̣߳�ÿ190msˢ��һ��
	private static Timer timer; // ʱ����ʾ�̣߳�ÿ��ˢ��һ��
	private static int timeValue = 0; // ʱ�����
	private static int flag = 1;	// ��Ϸ����  1 �ʼ����   2��������ͣ    3 ��ͣ������
	private static int flag2 = 1;   // 0 ��ͣ  1 ����
	
	/**
	 * ��Ϸ�߳��¼�������Ϸ����������������ˢ��
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
	 * ʱ���߳��¼�����ʱ����һ��ˢ��
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
	 * �Ʒ�ʱ�䴦��ˢ�¼���
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
	 * ��Ϸ�����¼������رն�ʱ��
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


	// ���췽��
	public GameController() {
		// ������Ϸ���������󣬲��ƶ��Ʒֺͼ�ʱ������
		model = new GameServer();
		model.addScoreListener(new ScoreHandler());
		model.addOverListener(new OverHandler());
//		model.addPauseListener(new PauseHandler());
		
		// ������ҿ�������ͨ������thisʹ��Ϸ�пغ���ҿ���������
		player = new PlayerController(this);

		// ������Ϸ�����棬�����ƿ�ʼ��ť�������ͼ��̼�����
		view = new FrameGame(model);
		view.addStartListener(player);
		view.addKeyListener(player);

		// ������Ϸ�˵����󣬲���ɹ��ʻ��뱾�ػ�����
		ResourceBundle bundle = ResourceBundle.getBundle("game/i18n/base");
		menu = new MenuGame(bundle, this);
		view.setJMenuBar(menu);
		view.setTitle(bundle.getString("title"));

		// ������Ϸ�̺߳�ʱ���߳�(����ֻ��һ���̣߳��Լ�˼����չ)
		gameTimer = new Timer(190, new GameHandler());
		timer = new Timer(1000, new TimeHandler());
	}

	/**
	 * ������Ϸ
	 */
	public void start() {
		if (GameController.flag == 1) {
			GameController.flag = 2;  // ����󼴽�������ͣ
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
	 * ������Ϸ
	 */
	public void pause() {
		
	}

	/**
	 * ��Ϸ�������
	 * 
	 * @param name
	 */
	public void turnTo(String name) {
		model.turnTo(name);
	}

	public void show() {
		view.setVisible(true);
		Insets is = view.getInsets(); // ������ͻ������������������
		view.setSize(GameConfig.getCanvasWidth() + is.left * 2,
				GameConfig.getCanvasHeight() + 96 + is.top + is.bottom);
		view.setLocationRelativeTo(null);	// ����
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
