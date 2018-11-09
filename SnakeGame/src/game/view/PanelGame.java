package game.view;

import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import game.model.GameServer;

/**
 * ��Ϸ������壬�̳���JPanel��������Ϸ��������������ã��ػ��ʱ��֪ͨ�ö����������Ļ��Ʋ���
 * 
 * @author GuYongtao
 *
 */
public class PanelGame extends JPanel {
	// ��Ա����
	private GameServer server;
	
	// ���췽��
	public PanelGame(GameServer server) {
		this.server = server;
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED)); // Ϊ��Ϣ����������߿򣬰���Ч��
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		server.draw(g);
	}
	
}
