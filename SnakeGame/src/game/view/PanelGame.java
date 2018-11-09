package game.view;

import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import game.model.GameServer;

/**
 * 游戏绘制面板，继承于JPanel，持有游戏服务器对象的引用，重绘的时候通知该对象进行自身的绘制操作
 * 
 * @author GuYongtao
 *
 */
public class PanelGame extends JPanel {
	// 成员属性
	private GameServer server;
	
	// 构造方法
	public PanelGame(GameServer server) {
		this.server = server;
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED)); // 为信息面板添加立体边框，凹陷效果
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		server.draw(g);
	}
	
}
