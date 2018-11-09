package com.sky.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SkyFrame extends JFrame implements ActionListener {
	// 成员属性
	ImageIcon icon = new ImageIcon("images/favorite.jpg");
	SkyPanel menuSkyPanel = new SkyPanel();
	SkyMenu menuSkyMenu = new SkyMenu();
	/**
	 * 构造方法
	 */
	public SkyFrame() {
		init();
	}
	
	/**
	 * 窗口初始化
	 */
	void init() {
		//this.setFont(new Font("宋体", Font.PLAIN, 14));
		this.setTitle("StarrySky");
		this.setSize(800, 600);
		this.setResizable(false); ///用户是否可以调整大小
		this.setLocationRelativeTo(null); //位于屏幕中间
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(icon.getImage());
		this.setJMenuBar(menuSkyMenu.jMenuBar);
		menuSkyMenu.jmi1.addActionListener(this);
		menuSkyMenu.jmi2.addActionListener(this);
		this.add(menuSkyPanel);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuSkyMenu.jmi1) {
			menuSkyPanel.autoPaint(menuSkyPanel.getGraphics());
		} else if (e.getSource() == menuSkyMenu.jmi2) {
			menuSkyPanel.addMouseListener(menuSkyPanel);
		}
	}
}
