package game.util;

import game.model.Node;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import java.awt.Graphics;

/**
 * ���й�����Ϸ�е�ͼƬ���ṩget������
 * @author GuYongtao
 * 2017��12��15�� ����10:53:57
 */
public class GameImage {

	// ��Ա����
	private static Image logo; // ����ͼ��
	private static ImageIcon start; // ��ʼ��ťͼƬ
	private static Image[] numbers; // 0-9ͼƬ���飬��Ƭ����
	private static Image[] nodes; // �ڵ�����ͼƬ���飬�߽ڵ㡢ʳ���ȱ�ϰ�ͼƬ
	private static String style = "snake1"; // ����ʽ

	static {
		logo = new ImageIcon("imgs/logo.jpg").getImage();
		start = new ImageIcon("imgs/start.png");
		numbers = splitImage("imgs/numbers.png", 10);
		nodes = new Image[3];
		nodes[0] = new ImageIcon("imgs/" + style + ".png").getImage();
		nodes[1] = new ImageIcon("imgs/food.png").getImage();
	}

	/**
	 * �����ص�0-9��ͼ�и�Ϊʮ�ŵ�ͼ���õ��ڴ�ͼƬ������10��������drawImage����
	 * 
	 * @param name
	 * @param size
	 * @return
	 */
	private static Image[] splitImage(String name, int size) {
		Image img = new ImageIcon(name).getImage();
		int ih = img.getHeight(null), iw = img.getWidth(null) / size; // ��ȡͼƬ���
		Image[] imgs = new Image[size];
		for (int i = 0; i < size; i++) {
			BufferedImage bi = new BufferedImage(iw, ih,
					BufferedImage.TRANSLUCENT); // �ڴ�ͼƬ͸��

			Graphics grfx = bi.getGraphics();
			grfx.drawImage(img, 0, 0, iw, ih, i * iw, 0, (i + 1) * iw, ih, null); // ��Ƭ����
			imgs[i] = bi;
		}

		return imgs;
	}

	/**
	 * ��ȡ��Ӧ�ڵ��������ŵ�ͼƬ
	 * @param index
	 * @return
	 */
	public static Image getNode(int index) {
		return nodes[index];
	}
	
	/**
	 * ����ʽ����
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
	 * ��ȡָ�����ֵ�ͼƬ
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
