package game.control;

import game.model.Snake;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;


/**
 * ��ҿ�������ʵ�ֿ�ʼ��ť����¼��������¼���������Ϸ�пض��������
 * @author GuYongtao
 *
 */

public class PlayerController extends KeyAdapter implements ActionListener {
	// ��Ա����
	private GameController game;
	
	// ���췽��
	public PlayerController(GameController game) {
		this.game = game;
	}
	
	/**
	 * ��ʼ��ť����¼��������
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
			game.start();
	}
	
	
	/**
	 * �����¼��������
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
