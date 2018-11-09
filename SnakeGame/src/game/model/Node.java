package game.model;

import game.util.GameConfig;
import game.util.GameImage;

import java.awt.Graphics;
import java.awt.Image;

/**
 * ��Ϸ����С��ɵ�Ԫ���ɱ�ʾ�ߵĽڵ㡢ʳ��ϰ�������ڵ�λ����Ϣ(�ڵ�����λ�õ����к�)���ڵ����ͣ�Ϊ���Java���Ͽ���еļ�����ʹ�ã�
 * ��Ҫ��дObject��equals����
 * 
 * @author GuYongtao
 *
 */
public class Node {
	// ��Ա����
	private int row; // ��Ԫ���к�
	private int col; // ��Ԫ���к�
	private NodeType type; // �ڵ�����

	/**
	 * �������кŵĹ��췽��
	 * 
	 * @param r
	 * @param c
	 */
	public Node(int r, int c) {
		this(r, c, NodeType.Snake);
	}

	/**
	 * ���췽������ �������кż����Ͳ���
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
	 * ��дObject�ṩ��equals���������ڽڵ��������жϣ��缯���еĲ��Ҳ���
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
	 * �滭��Ϸ
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		int size = GameConfig.getCellSize(); // ��ȡ������Ϣ�е�Ԫ���С
		int x = col * size + 2, y = row * size + 2; // ����ƽ��(2, 2)�������߿�λ��
		Image img = GameImage.getNode(type.ordinal()); // ��ȡ��Ӧ�ڵ����͵�ͼƬ
		g.drawImage(img, x, y, size, size, null); // ����ָ����С���Ż���ͼƬ
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
