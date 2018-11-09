package game.view;

import game.control.GameController;
import game.util.GameImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 * ��Ϸ�˵����̳���JMenuBar������ResourceBundle��������ú�GameController���������
 * 
 * @author GuYongtao
 *
 */
public class MenuGame extends JMenuBar {
	// ��Ա����
	private ResourceBundle bundle;
	private GameController game;
	
	// ���췽��
	public MenuGame(ResourceBundle b, GameController g) {
		bundle = b;
		game = g;
		initalizeMenus();
	}
	
	
	private class NewHangdler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			game.start();
		}
	}
	
	/**
	 * ������Ϸ
	 * @author GuYongtao
	 *
	 */
	private class ExitHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	
	private void initalizeMenus() {
		//-------------------��Ϸ�˵���-------------------------
		JMenu mnGame = new JMenu(bundle.getString("game"));
		mnGame.setMnemonic('G');  // ���õ�ǰģ���ϵļ������Ƿ�
		
		JMenuItem itemNew = new JMenuItem(bundle.getString("new"));
		itemNew.setMnemonic('N');
		itemNew.setAccelerator(KeyStroke.getKeyStroke("F2"));  // �����޸ļ�������ֱ�ӵ��ò˵���Ĳ�����������������ʾ�˵��Ĳ�νṹ��
		itemNew.addActionListener(new NewHangdler());
		
		JMenuItem itemExit = new JMenuItem(bundle.getString("exit"));
		itemExit.setMnemonic('x');
		itemExit.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
		itemExit.addActionListener(new ExitHandler());
		
		mnGame.add(itemNew);
		mnGame.addSeparator(); // ���·ָ���׷�ӵ��˵���ĩβ
		mnGame.add(itemExit);
		//--------------------------------------------------
		
		//--------------------��������-------------------------
		// �˵�1��ͼ��
		JMenu mnStyle = new JMenu(bundle.getString("style"));
		mnStyle.setMnemonic('S');
		
		// Ϊͼ�β˵�����
		ButtonGroup styleGroup = new ButtonGroup();
		
		// ����ͼ�μ�����
		StyleHandler styleHandler = new StyleHandler();
		
		// ���Ӳ˵���
		
		JRadioButtonMenuItem snake1 = new JRadioButtonMenuItem(bundle.getString("snake1"));
		snake1.setMnemonic('1');
		snake1.setAccelerator(KeyStroke.getKeyStroke('1'));
		snake1.addActionListener(new NewHangdler());
		snake1.setSelected(GameImage.getStyle().equals("snake1"));
		snake1.addActionListener(styleHandler); // ע�������
		
		
		JRadioButtonMenuItem snake2 = new JRadioButtonMenuItem(bundle.getString("snake2"));
		snake2.setMnemonic('2');
		snake2.setAccelerator(KeyStroke.getKeyStroke('2'));
		snake2.addActionListener(new NewHangdler());
		snake2.setSelected(GameImage.getStyle().equals("snake2"));
		snake2.addActionListener(styleHandler); // ע�������
		
		JRadioButtonMenuItem snake3 = new JRadioButtonMenuItem(bundle.getString("snake3"));
		snake3.setMnemonic('3');
		snake3.setAccelerator(KeyStroke.getKeyStroke('3'));
		snake3.addActionListener(new NewHangdler());
		snake3.setSelected(GameImage.getStyle().equals("snake3"));
		snake3.addActionListener(styleHandler); // ע�������
				
		// ���˵������buttonGroup
		styleGroup.add(snake1);
		styleGroup.add(snake2);
		styleGroup.add(snake3);
		
		// ���˵�����뵽�˵���
		mnStyle.add(snake1);
		mnStyle.add(snake2);
		mnStyle.add(snake3);
		
/*		// �˵�1��ͼ��
		JMenu mnStyle = new JMenu("Style");
		mnStyle.setMnemonic('s');  // ���õ�ǰģ���ϵļ������Ƿ�		
		
		
		// Ϊͼ�β˵�����
		ButtonGroup styleGroup = new ButtonGroup();
		
		// ����ͼ�μ�����
		StyleHandler styleHandler = new StyleHandler();
		
		// ���Ӳ˵���
		
		JRadioButtonMenuItem snake1 = new JRadioButtonMenuItem("snake1");
		snake1.setSelected(GameImage.getStyle().equals("snake1"));
		snake1.addActionListener(styleHandler); // ע�������
		
		JRadioButtonMenuItem snake2 = new JRadioButtonMenuItem("snake2");
		snake2.setSelected(GameImage.getStyle().equals("snake2"));
		snake2.addActionListener(styleHandler); // ע�������
		
		JRadioButtonMenuItem snake3 = new JRadioButtonMenuItem("snake3");
		snake3.setSelected(GameImage.getStyle().equals("snake3"));
		snake3.addActionListener(styleHandler); // ע�������
				
		// ���˵������buttonGroup
		styleGroup.add(snake1);
		styleGroup.add(snake2);
		styleGroup.add(snake3);
		
		// ���˵�����뵽�˵���
		mnStyle.add(snake1);
		mnStyle.add(snake2);
		mnStyle.add(snake3);*/
		//-------------------------------------------------
		
		//-------------------�����˵���-------------------------
		JMenu mnHelp = new JMenu(bundle.getString("help"));
		mnHelp.setMnemonic('H');
		
		JMenuItem itemAbout = new JMenuItem(bundle.getString("about"));
		itemAbout.setMnemonic('A');
		
		mnHelp.add(itemAbout);
		//-------------------------------------------------
		
		this.add(mnGame);
		this.add(mnStyle);
		this.add(mnHelp);
	}
	
	
	// �ڲ���ʵ��Shape  ActionListener
	private class StyleHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem)e.getSource();
			GameImage.setStyle(item.getText());
			GameImage.setNode(item.getText());
		}
	}
}
