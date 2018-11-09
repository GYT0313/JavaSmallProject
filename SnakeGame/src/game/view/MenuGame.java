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
 * 游戏菜单：继承于JMenuBar，持有ResourceBundle对象的引用和GameController对象的引用
 * 
 * @author GuYongtao
 *
 */
public class MenuGame extends JMenuBar {
	// 成员属性
	private ResourceBundle bundle;
	private GameController game;
	
	// 构造方法
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
	 * 结束游戏
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
		//-------------------游戏菜单组-------------------------
		JMenu mnGame = new JMenu(bundle.getString("game"));
		mnGame.setMnemonic('G');  // 设置当前模型上的键盘助记符
		
		JMenuItem itemNew = new JMenuItem(bundle.getString("new"));
		itemNew.setMnemonic('N');
		itemNew.setAccelerator(KeyStroke.getKeyStroke("F2"));  // 设置修改键，它能直接调用菜单项的操作侦听器而不必显示菜单的层次结构。
		itemNew.addActionListener(new NewHangdler());
		
		JMenuItem itemExit = new JMenuItem(bundle.getString("exit"));
		itemExit.setMnemonic('x');
		itemExit.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
		itemExit.addActionListener(new ExitHandler());
		
		mnGame.add(itemNew);
		mnGame.addSeparator(); // 将新分隔符追加到菜单的末尾
		mnGame.add(itemExit);
		//--------------------------------------------------
		
		//--------------------蛇种类组-------------------------
		// 菜单1：图形
		JMenu mnStyle = new JMenu(bundle.getString("style"));
		mnStyle.setMnemonic('S');
		
		// 为图形菜单分组
		ButtonGroup styleGroup = new ButtonGroup();
		
		// 创建图形监听器
		StyleHandler styleHandler = new StyleHandler();
		
		// 增加菜单项
		
		JRadioButtonMenuItem snake1 = new JRadioButtonMenuItem(bundle.getString("snake1"));
		snake1.setMnemonic('1');
		snake1.setAccelerator(KeyStroke.getKeyStroke('1'));
		snake1.addActionListener(new NewHangdler());
		snake1.setSelected(GameImage.getStyle().equals("snake1"));
		snake1.addActionListener(styleHandler); // 注册监听器
		
		
		JRadioButtonMenuItem snake2 = new JRadioButtonMenuItem(bundle.getString("snake2"));
		snake2.setMnemonic('2');
		snake2.setAccelerator(KeyStroke.getKeyStroke('2'));
		snake2.addActionListener(new NewHangdler());
		snake2.setSelected(GameImage.getStyle().equals("snake2"));
		snake2.addActionListener(styleHandler); // 注册监听器
		
		JRadioButtonMenuItem snake3 = new JRadioButtonMenuItem(bundle.getString("snake3"));
		snake3.setMnemonic('3');
		snake3.setAccelerator(KeyStroke.getKeyStroke('3'));
		snake3.addActionListener(new NewHangdler());
		snake3.setSelected(GameImage.getStyle().equals("snake3"));
		snake3.addActionListener(styleHandler); // 注册监听器
				
		// 将菜单项加入buttonGroup
		styleGroup.add(snake1);
		styleGroup.add(snake2);
		styleGroup.add(snake3);
		
		// 将菜单项加入到菜单里
		mnStyle.add(snake1);
		mnStyle.add(snake2);
		mnStyle.add(snake3);
		
/*		// 菜单1：图形
		JMenu mnStyle = new JMenu("Style");
		mnStyle.setMnemonic('s');  // 设置当前模型上的键盘助记符		
		
		
		// 为图形菜单分组
		ButtonGroup styleGroup = new ButtonGroup();
		
		// 创建图形监听器
		StyleHandler styleHandler = new StyleHandler();
		
		// 增加菜单项
		
		JRadioButtonMenuItem snake1 = new JRadioButtonMenuItem("snake1");
		snake1.setSelected(GameImage.getStyle().equals("snake1"));
		snake1.addActionListener(styleHandler); // 注册监听器
		
		JRadioButtonMenuItem snake2 = new JRadioButtonMenuItem("snake2");
		snake2.setSelected(GameImage.getStyle().equals("snake2"));
		snake2.addActionListener(styleHandler); // 注册监听器
		
		JRadioButtonMenuItem snake3 = new JRadioButtonMenuItem("snake3");
		snake3.setSelected(GameImage.getStyle().equals("snake3"));
		snake3.addActionListener(styleHandler); // 注册监听器
				
		// 将菜单项加入buttonGroup
		styleGroup.add(snake1);
		styleGroup.add(snake2);
		styleGroup.add(snake3);
		
		// 将菜单项加入到菜单里
		mnStyle.add(snake1);
		mnStyle.add(snake2);
		mnStyle.add(snake3);*/
		//-------------------------------------------------
		
		//-------------------帮助菜单组-------------------------
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
	
	
	// 内部类实现Shape  ActionListener
	private class StyleHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem)e.getSource();
			GameImage.setStyle(item.getText());
			GameImage.setNode(item.getText());
		}
	}
}
