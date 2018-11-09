package game.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 游戏配置信息管理类 负责加载配置信息及提供get访问器
 * 
 * @author GuYongtao
 *
 */
public class GameConfig {
	// 成员属性
	private static String skinPath = "classic"; // 皮肤路径，有多套图片样式可用。本程序未使用
	private static int cellSize;
	private static int rows;
	private static int cols;
	private static Properties cfg;

	// 静态初始块
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
		return cellSize * cols + 4 + 8; // 画布边框宽度2*2 边距留白2*4
	}

	public static int getCanvasHeight() {
		return cellSize * rows + 4;  // 画布边框高度2*2
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
