package com.sky.model;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class SkyMenu extends JMenuBar{
	// 成员变量
	JMenuBar jMenuBar = new JMenuBar();
	JMenu jMenu = new JMenu("Choice");
	JMenuItem jmi1 = new JMenuItem("Auto");
	JMenuItem jmi2 = new JMenuItem("Click");

	// 构造方法
	public SkyMenu() {
		init();
	}
	
	public void init() {

		jMenuBar.add(jMenu);
		jMenu.add(jmi1);
		jMenu.add(jmi2);

	}
}
