package com.sky.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SkyPanel extends JPanel implements MouseListener {
	SkyMenu menu = new SkyMenu();
	/**
	 *  构造方法，改变背景色
	 */
	public SkyPanel() {		
		this.setBackground(new Color(43, 27, 27));
		
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // 父类先绘制
	}
	
	protected void autoPaint(Graphics g) {
//		super.paintComponent(g); // 父类先绘制
		Random random = new Random();
		Dimension thisFrame = this.getSize();
		
		for (int i = 0; i < 30; i++) {
			// 随机位置    getWidth  getHeight  在窗口内随机
			int starX = random.nextInt(thisFrame.width)-10;
			int starY = random.nextInt(thisFrame.height)-10;
			
			// 随机颜色    r+g+b [0-255]
			int rColor = random.nextInt(255);
			int gColor = random.nextInt(255);
			int bColor = random.nextInt(255);
			g.setColor(new Color(rColor, gColor, bColor));
			
			
			// 随机大小   [0-30]
			int starSize = random.nextInt(30);
			g.setFont(new Font("TimesRoman", Font.BOLD, starSize));
			
			// 使用g.drawString 在随机位置绘制"★"
			g.drawString("★", starX, starY);
		}
	}
	
	public void clickPaint(Graphics g, int starX, int starY) {
		Random random = new Random();

		// 随机颜色 r+g+b [0-255]
		int rColor = random.nextInt(255);
		int gColor = random.nextInt(255);
		int bColor = random.nextInt(255);
		g.setColor(new Color(rColor, gColor, bColor));

		// 随机大小 [0-30]
		int starSize = random.nextInt(30);
		g.setFont(new Font("TimesRoman", Font.BOLD, starSize));

		// 使用g.drawString 在随机位置绘制"*"
		g.drawString("★", starX, starY);
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("x = " + e.getX() + "  y = " + e.getY());
		clickPaint(this.getGraphics(), e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
