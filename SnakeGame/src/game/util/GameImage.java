package game.util;

import game.model.Node;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import java.awt.Graphics;

/**
 * 集中管理游戏中的图片，提供get访问器
 * @author GuYongtao
 * 2017年12月15日 下午10:53:57
 */
public class GameImage {

	// 成员变量
	private static Image logo; // 程序图标
	private static ImageIcon start; // 开始按钮图片
	private static Image[] numbers; // 0-9图片数组，切片处理
	private static Image[] nodes; // 节点类型图片数组，蛇节点、食物、暂缺障碍图片
	private static String style = "snake1"; // 蛇样式

	static {
		logo = new ImageIcon("imgs/logo.jpg").getImage();
		start = new ImageIcon("imgs/start.png");
		numbers = splitImage("imgs/numbers.png", 10);
		nodes = new Image[3];
		nodes[0] = new ImageIcon("imgs/" + style + ".png").getImage();
		nodes[1] = new ImageIcon("imgs/food.png").getImage();
	}

	/**
	 * 将加载的0-9整图切割为十张单图，用到内存图片及包含10个参数的drawImage方法
	 * 
	 * @param name
	 * @param size
	 * @return
	 */
	private static Image[] splitImage(String name, int size) {
		Image img = new ImageIcon(name).getImage();
		int ih = img.getHeight(null), iw = img.getWidth(null) / size; // 获取图片宽高
		Image[] imgs = new Image[size];
		for (int i = 0; i < size; i++) {
			BufferedImage bi = new BufferedImage(iw, ih,
					BufferedImage.TRANSLUCENT); // 内存图片透明

			Graphics grfx = bi.getGraphics();
			grfx.drawImage(img, 0, 0, iw, ih, i * iw, 0, (i + 1) * iw, ih, null); // 切片处理
			imgs[i] = bi;
		}

		return imgs;
	}

	/**
	 * 获取对应节点的类型序号的图片
	 * @param index
	 * @return
	 */
	public static Image getNode(int index) {
		return nodes[index];
	}
	
	/**
	 * 蛇样式设置
	 * @param s
	 */
	public static void setNode(String s) {
		nodes[0] = new ImageIcon("imgs/" + s + ".png").getImage();
	}
	
	public static String getStyle() {
		return style;
	}
	
	public static void setStyle(String s) {
		style = s;
	}
	
	public static Image getLogo() {
		return logo;
	}
	
	public static ImageIcon getStart() {
		return start;
	}
	public static void setStart(ImageIcon jbn) {
		start = jbn;
	}
	
	/**
	 * 获取指定数字的图片
	 * @param num
	 * @return
	 */
	public static Image getNumber(int num) {
		return numbers[num];
	}
	
	public static int getNumWidth() {
		return numbers[0].getWidth(null);
	}
	
	public static int getNumHeight() {
		return numbers[0].getHeight(null);
	}

}
