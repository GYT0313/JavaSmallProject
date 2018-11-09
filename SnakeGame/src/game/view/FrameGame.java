package game.view;

import game.model.GameServer;
import game.util.GameImage;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * ��Ϸ�����棺��װPanelInfo��PanelGame������Box���в��֣�����֪ͨPanelInfo����
 * �����Ӧ�Ĳ���
 * @author GuYongtao
 *
 */
public class FrameGame extends JFrame {
	// ��Ա����
	private PanelInfo info;
	
	/**
	 * ֪ͨPanleInfo�����Ƽ�����
	 * @param listener
	 */
	public void addStartListener(ActionListener listener) {
		info.addStartListener(listener);
	}
	
	
	/**
	 * ֪ͨPanelInfo����ˢ��ʱ����ʾ
	 * @param timeValue
	 */
	public void refreshTime(int timeValue) {
		info.refreshTime(timeValue);
	}
	
	/**
	 * ֪ͨPanelInfoS����ˢ�µ÷���ʾ
	 * @param value
	 */
	public void refreshScore(int value) {
		info.refreshScore(value);
	}
	
	// ���췽��
	public FrameGame(GameServer server) {
		info = new PanelInfo();
		PanelGame canvas = new PanelGame(server);
		
		// 3��3�У��м䵥Ԫ�����JPanel��BorderLayout��
		// ����PanelInfo��PanelGame
		Box vBox = Box.createVerticalBox(); {  // ����һ�����ϵ�����ʾ������� Box��
			vBox.add(Box.createVerticalStrut(4)); // ����һ�����ɼ��ġ��̶���ȵ������
			Box hBox = Box.createHorizontalBox(); {  //  ����һ����������ʾ������� Box��
				hBox.add(Box.createHorizontalStrut(4));
				JPanel panel = new JPanel(new BorderLayout(0, 4)); // ���񲼾�
				panel.add(info, BorderLayout.NORTH);
				panel.add(canvas);
				hBox.add(panel);
				hBox.add(Box.createHorizontalStrut(4));
			}
			vBox.add(hBox);
			vBox.add(Box.createVerticalStrut(4));
		}
		
		this.add(vBox);
		this.setIconImage(GameImage.getLogo()); // logo
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // �˳�
	}
	
	
}
