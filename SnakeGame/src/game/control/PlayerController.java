package game.control;

import game.model.Snake;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;


/**
 * 玩家控制器，实现开始按钮点击事件及键盘事件，持有游戏中控对象的引用
 * @author GuYongtao
 *
 */

public class PlayerController extends KeyAdapter implements ActionListener {
	// 成员属性
	private GameController game;
	
	// 构造方法
	public PlayerController(GameController game) {
		this.game = game;
	}
	
	/**
	 * 开始按钮点击事件处理程序
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
			game.start();
	}
	
	
	/**
	 * 键盘事件处理程序
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		String[] dirs = {"Left", "Up", "Right", "Down"};
		int code = e.getKeyCode();
		if (code >= 37 && code <= 40) {
			game.turnTo(dirs[code - 37]);
		}
	}
	
	
	
}
