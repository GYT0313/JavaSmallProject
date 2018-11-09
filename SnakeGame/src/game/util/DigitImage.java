package game.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * �ṩ��̬���ɷ���ͼƬ�Ĺ��� ����ָ��λ����ָ����ֵ��̬������Ӧ����ͼƬ��λ��������ǰ�油0��������ضϸ�λ
 * 
 * @author GuYongtao
 *
 */
public class DigitImage {
	// ��Ա����
	private static int bits = 3; // ���ִ�λ��

	public static ImageIcon getIcon(int value) {
		int iw = GameImage.getNumWidth(); // ��������ͼƬ���
		int ih = GameImage.getNumHeight(); // ��������ͼƬ�߶�

		BufferedImage bi = new BufferedImage(iw * bits, ih,
				BufferedImage.TRANSLUCENT); // �ڴ�ͼƬ͸��
		Graphics grfx = bi.getGraphics(); // ��ȡͼƬ��ͼ����

		int count = 0; // ��¼���ƴ�����������bits��
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
