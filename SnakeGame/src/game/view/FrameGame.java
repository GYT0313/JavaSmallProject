package game.view;

import game.model.GameServer;
import game.util.GameImage;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * 游戏主界面：组装PanelInfo和PanelGame，采用Box进行布局，负责通知PanelInfo对象
 * 完成相应的操作
 * @author GuYongtao
 *
 */
public class FrameGame extends JFrame {
	// 成员属性
	private PanelInfo info;
	
	/**
	 * 通知PanleInfo对象定制监听器
	 * @param listener
	 */
	public void addStartListener(ActionListener listener) {
		info.addStartListener(listener);
	}
	
	
	/**
	 * 通知PanelInfo对象刷新时间显示
	 * @param timeValue
	 */
	public void refreshTime(int timeValue) {
		info.refreshTime(timeValue);
	}
	
	/**
	 * 通知PanelInfoS对象刷新得分显示
	 * @param value
	 */
	public void refreshScore(int value) {
		info.refreshScore(value);
	}
	
	// 构造方法
	public FrameGame(GameServer server) {
		info = new PanelInfo();
		PanelGame canvas = new PanelGame(server);
		
		// 3行3列，中间单元格采用JPanel及BorderLayout，
		// 布局PanelInfo和PanelGame
		Box vBox = Box.createVerticalBox(); {  // 创建一个从上到下显示其组件的 Box。
			vBox.add(Box.createVerticalStrut(4)); // 创建一个不可见的、固定宽度的组件。
			Box hBox = Box.createHorizontalBox(); {  //  创建一个从左到右显示其组件的 Box。
				hBox.add(Box.createHorizontalStrut(4));
				JPanel panel = new JPanel(new BorderLayout(0, 4)); // 网格布局
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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // 退出
	}
	
	
}
