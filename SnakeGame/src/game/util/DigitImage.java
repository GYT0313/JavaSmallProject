package game.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * 提供动态生成分数图片的功能 根据指定位数、指定数值动态生成相应数字图片，位数不足则前面补0，超出则截断高位
 * 
 * @author GuYongtao
 *
 */
public class DigitImage {
	// 成员属性
	private static int bits = 3; // 数字串位数

	public static ImageIcon getIcon(int value) {
		int iw = GameImage.getNumWidth(); // 单独数字图片宽度
		int ih = GameImage.getNumHeight(); // 单独数字图片高度

		BufferedImage bi = new BufferedImage(iw * bits, ih,
				BufferedImage.TRANSLUCENT); // 内存图片透明
		Graphics grfx = bi.getGraphics(); // 获取图片绘图表面

		int count = 0; // 记录绘制次数，共绘制bits次
		while (count < bits) {
			Image img = GameImage.getNumber(value % 10);
			grfx.drawImage(img, (bits - 1 - count) * iw, 0, null);
			value /= 10;
			count++;
		}

		return new ImageIcon(bi);
	}

	public static int getBits() {
		return bits;
	}

	public static void setBits(int bits) {
		DigitImage.bits = bits;
	}

}
