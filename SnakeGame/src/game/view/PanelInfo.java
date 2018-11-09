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
 * ��Ϸ��Ϣ��壬�̳���JPanel��������Ϸ��ʱ���Ʒֵ���ʾ����ʼͼƬ��ť
 * 
 * @author GuYongtao
 *
 */
public class PanelInfo extends JPanel {
	// ��Ա����
	private static JButton btnStart; // ��ʼ��ť
	private JLabel lbTime; // ��Jlabel��ʾ��ʱͼƬ
	private JLabel lbScore; // ��Jlabel��ʾ�Ʒ�ͼƬ

	/**
	 * �����ṩ���ư�ť�¼��ķ���
	 * 
	 * @param listener
	 */
	public void addStartListener(ActionListener listener) {
		btnStart.addActionListener(listener);
	}
	
	
	/**
	 * ˢ�¼�ʱ��ʾ
	 * 
	 * @param timeValue
	 */
	public void refreshTime(int timeValue) {
		ImageIcon icon = DigitImage.getIcon(timeValue); // ֪ͨ����ͼƬ���ȡ��Ӧ��ͼƬ
		lbTime.setIcon(icon);
	}
	
	/**
	 * ˢ�¼Ʒ���ʾ
	 * @param value
	 */
	public void refreshScore(int value) {
		ImageIcon icon = DigitImage.getIcon(value);
		lbScore.setIcon(icon);
	}
	
	/**
	 * ����Box��ʽ���в��֣�1��7��
	 */
	public PanelInfo() {
		Box vBox = Box.createVerticalBox(); // ����һ�����ϵ�����ʾ������� Box��
		{
			Box hBox = Box.createHorizontalBox(); //  ����һ����������ʾ������� Box��
			{
				hBox.add(Box.createHorizontalStrut(4)); // ����һ�����ɼ��ġ��̶���ȵ������
				
				ImageIcon imgScore = DigitImage.getIcon(0);
				lbScore = new JLabel(imgScore);
				hBox.add(lbScore);
				
				hBox.add(Box.createHorizontalGlue()); // ����һ������ glue �����
				
				btnStart = new JButton(GameImage.getStart()); // ʹ��ͼƬ��ť
				btnStart.setFocusable(false); // ȡ����ť�������߿�
				btnStart.setContentAreaFilled(false); // �����ť�������ƣ��ﵽ͸��Ч��
				btnStart.setMargin(new Insets(0, 0, 0, 0)); // ���ͼƬ�밴ť�߽������
				btnStart.setBorderPainted(false); // �����ť�߿�
				hBox.add(btnStart);
				
				hBox.add(Box.createHorizontalGlue());
				
				ImageIcon imgTime = DigitImage.getIcon(0);
				lbTime = new JLabel(imgTime);
				hBox.add(lbTime);
				
				hBox.add(Box.createHorizontalStrut(4));
			}
			vBox.add(hBox);
		}
		this.setLayout(new BorderLayout()); // ���񲼾�
		this.add(vBox);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED)); // Ϊ��Ϣ����������߿򣬰���Ч��
	}


	public static JButton getBtnStart() {
		return btnStart;
	}


	public static void setBtnStart(JButton btnStart) {
		PanelInfo.btnStart = btnStart;
	}
	
}
