package game.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * ��Ϸ������Ϣ������ �������������Ϣ���ṩget������
 * 
 * @author GuYongtao
 *
 */
public class GameConfig {
	// ��Ա����
	private static String skinPath = "classic"; // Ƥ��·�����ж���ͼƬ��ʽ���á�������δʹ��
	private static int cellSize;
	private static int rows;
	private static int cols;
	private static Properties cfg;

	// ��̬��ʼ��
	static {
		try {
			cfg = new Properties();
			cfg.load(new FileInputStream("config.properties"));
			cellSize = Integer.parseInt(cfg.getProperty("cellSize"));
			rows = Integer.parseInt(cfg.getProperty("rows"));
			cols = Integer.parseInt(cfg.getProperty("cols"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getCanvasWidth() {
		return cellSize * cols + 4 + 8; // �����߿���2*2 �߾�����2*4
	}

	public static int getCanvasHeight() {
		return cellSize * rows + 4;  // �����߿�߶�2*2
	}
	
	public static String getSkinPath() {
		return skinPath;
	}
	
	public static int getCellSize() {
		return cellSize;
	}
	
	public static int getRows() {
		return rows;
	}
	
	public static int getCols() {
		return cols;
	}
}
