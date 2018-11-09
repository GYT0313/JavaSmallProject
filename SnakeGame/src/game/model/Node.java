package game.model;

import game.util.GameConfig;
import game.util.GameImage;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 游戏中最小组成单元，可表示蛇的节点、食物、障碍物。包含节点位置信息(节点所处位置的行列号)及节点类型，为配合Java集合框架中的集合类使用，
 * 需要重写Object的equals方法
 * 
 * @author GuYongtao
 *
 */
public class Node {
	// 成员属性
	private int row; // 单元格行号
	private int col; // 单元格列好
	private NodeType type; // 节点类型

	/**
	 * 接受行列号的构造方法
	 * 
	 * @param r
	 * @param c
	 */
	public Node(int r, int c) {
		this(r, c, NodeType.Snake);
	}

	/**
	 * 构造方法重载 接受行列号及类型参数
	 * 
	 * @param r
	 * @param c
	 * @param t
	 */
	public Node(int r, int c, NodeType t) {
		row = r;
		col = c;
		type = t;
	}

	/**
	 * 重写Object提供的equals方法，用于节点对象相等判断，如集合中的查找操作
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;

		Node n = (Node) obj;
		return row == n.row && col == n.col;
	}

	/**
	 * 绘画游戏
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		int size = GameConfig.getCellSize(); // 获取配置信息中单元格大小
		int x = col * size + 2, y = row * size + 2; // 坐标平移(2, 2)，留出边框位置
		Image img = GameImage.getNode(type.ordinal()); // 获取相应节点类型的图片
		g.drawImage(img, x, y, size, size, null); // 根据指定大小缩放绘制图片
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}
}
