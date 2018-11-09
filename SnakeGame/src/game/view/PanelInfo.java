package game.view;

import game.control.GameController;
import game.model.Snake;
import game.util.DigitImage;
import game.util.GameImage;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

/**
 * 游戏信息面板，继承于JPanel，包括游戏计时、计分的显示及开始图片按钮
 * 
 * @author GuYongtao
 *
 */
public class PanelInfo extends JPanel {
	// 成员属性
	private static JButton btnStart; // 开始按钮
	private JLabel lbTime; // 用Jlabel显示计时图片
	private JLabel lbScore; // 用Jlabel显示计分图片

	/**
	 * 对外提供定制按钮事件的方法
	 * 
	 * @param listener
	 */
	public void addStartListener(ActionListener listener) {
		btnStart.addActionListener(listener);
	}
	
	
	/**
	 * 刷新计时提示
	 * 
	 * @param timeValue
	 */
	public void refreshTime(int timeValue) {
		ImageIcon icon = DigitImage.getIcon(timeValue); // 通知数字图片类获取对应的图片
		lbTime.setIcon(icon);
	}
	
	/**
	 * 刷新计分显示
	 * @param value
	 */
	public void refreshScore(int value) {
		ImageIcon icon = DigitImage.getIcon(value);
		lbScore.setIcon(icon);
	}
	
	/**
	 * 采用Box方式进行布局，1行7列
	 */
	public PanelInfo() {
		Box vBox = Box.createVerticalBox(); // 创建一个从上到下显示其组件的 Box。
		{
			Box hBox = Box.createHorizontalBox(); //  创建一个从左到右显示其组件的 Box。
			{
				hBox.add(Box.createHorizontalStrut(4)); // 创建一个不可见的、固定宽度的组件。
				
				ImageIcon imgScore = DigitImage.getIcon(0);
				lbScore = new JLabel(imgScore);
				hBox.add(lbScore);
				
				hBox.add(Box.createHorizontalGlue()); // 创建一个横向 glue 组件。
				
				btnStart = new JButton(GameImage.getStart()); // 使用图片按钮
				btnStart.setFocusable(false); // 取消按钮焦点虚线框
				btnStart.setContentAreaFilled(false); // 清除按钮背景绘制，达到透明效果
				btnStart.setMargin(new Insets(0, 0, 0, 0)); // 清除图片与按钮边界的留白
				btnStart.setBorderPainted(false); // 清除按钮边框
				hBox.add(btnStart);
				
				hBox.add(Box.createHorizontalGlue());
				
				ImageIcon imgTime = DigitImage.getIcon(0);
				lbTime = new JLabel(imgTime);
				hBox.add(lbTime);
				
				hBox.add(Box.createHorizontalStrut(4));
			}
			vBox.add(hBox);
		}
		this.setLayout(new BorderLayout()); // 网格布局
		this.add(vBox);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED)); // 为信息面板添加立体边框，凹陷效果
	}


	public static JButton getBtnStart() {
		return btnStart;
	}


	public static void setBtnStart(JButton btnStart) {
		PanelInfo.btnStart = btnStart;
	}
	
}
