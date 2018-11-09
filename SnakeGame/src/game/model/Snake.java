package game.model;

import game.control.GameController;
import game.util.GameConfig;

import java.awt.Font;
import java.awt.Graphics;
import java.beans.IntrospectionException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 
 * ���ܣ��ṩ��Ϸ������ص����ݺ���Ϊ��װ����Ϣ�����ߵĽڵ㼯���ƶ������ ���ܰ����ߵ��ƶ�����������ʳ�Ｐ���ȡ�
 * 
 * ���ǵ�˳���Ĳ���Ч�ʣ����幦��ʵ��ʱֻ��˳����β�������Լ���˳����е�Ԫ���ƶ�����˽��ߵ�ͷ��������˳����β����
 * ��������ʱ��Ҳ��˳���β��׷�Ӳ��޸���ͷ��Ϊ�����ڵ�
 * 
 * @author GuYongtao
 *
 */
public class Snake {
	// ��Ա����
	private ArrayList<Node> nodes = new ArrayList<>(); // �ڵ㼯
	private Direction direction = Direction.Right; // Ĭ���ƶ�����Ϊ��
	private int[][] offsets; // ����ƫ�����飬����ȡ����֧ �򻯳���ṹ

	/**
	 * ���췽��
	 */
	public Snake() {
		defaultInit();
		offsets = new int[][] { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } }; // ��    x-
																			// ��    y-
																			// ��    x+
																			// ��    y+
	}

	/**
	 * Ĭ�ϳ�ʼ��������������Ϸ��ʼ���߶�����Ҫ���³�ʼ��
	 */
	public void defaultInit() {
		nodes.clear();
		// ��ʼʱ���߶���Ĭ�ϰ��������ڵ㣬�м俿��ˮƽ����
		int r = GameConfig.getRows() / 2 - 1;
		nodes.add(new Node(r, 0));
		nodes.add(new Node(r, 1));
		nodes.add(new Node(r, 2));
		direction = direction.Right;
	}

	/**
	 * ���ƶ��㷨����β���ڵ�(��ͷ)������ڵ�����ǰ��Ϊ��һ���ڵ� ͷ���ڵ���ݵ�ǰ�ƶ�����ǰ���޸ģ�����Ԥ�����ƫ�������ʵ��
	 */
	public void move() {
		if (GameController.getFlag2() == 1) {
			// ѭ���ƶ�
			for (int i = 0; i < nodes.size() - 1; i++) {
				Node n = nodes.get(i), next = nodes.get(i + 1);
				n.setRow(next.getRow());
				n.setCol(next.getCol());
			}
			// ͷ���ƶ�
			Node head = nodes.get(nodes.size() - 1); // ͷ��
			head.setRow(head.getRow() + offsets[direction.ordinal()][0]); // ���ݶ�ά����ʵ���ƶ�
			head.setCol(head.getCol() + offsets[direction.ordinal()][1]);
		}
		
	}

	/**
	 * ��ת�������Ҫ��ֱͬ�߷���ķ������ܲ���������ʱ����������ʱ�������ϣ�����ͬ�� ����ʵ��ʱ������ö�ٵ�Ԫ��ż�Ĺ�ϵ�����Ϊ2��ֱͬ�߷���
	 * 
	 * @param dir
	 */
	public void turnTo(Direction dir) {
		int x = Math.abs(dir.ordinal() - direction.ordinal());
		if (x != 2) {
			direction = dir;
		}
	}

	/**
	 * �ж����Ƿ�ٵ�ʳ��ڵ㣬ͨ����ͷ������һ��λ���Ƿ���ʳ��ڵ���Ƚ����ж� �������жϣ���ͷ������û�з����ƶ�
	 * 
	 * @param food
	 * @return
	 */
	public boolean isEat(Node food) {
		Node head = nodes.get(nodes.size() - 1);
		Node n = new Node(head.getRow(), head.getCol());
		n.setRow(head.getRow() + offsets[direction.ordinal()][0]);
		n.setCol(head.getCol() + offsets[direction.ordinal()][1]);
		return n.equals(food);
	}

	/**
	 * �߳Ե�ʳ���������޸�ʳ��ڵ�����
	 * 
	 * @param n
	 */
	public void grow(Node n) {
		n.setType(NodeType.Snake);
		nodes.add(n);
	}

	/**
	 * �����ߣ���֪ͨ�ڵ㼯�н��л���
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		for (Node n : nodes) {
			n.draw(g);
		}
	}

	/**
	 * ����ʳ��ʱ��ʳ��ڵ�λ���Ƿ��������ϣ��ڼ����Ϸ�
	 * 
	 * @param n
	 * @return
	 */
	public boolean checkOverlap(Node n) {
		boolean s = true;
		for (Node node : nodes) {
			if (node.equals(n)) {
				s = false;
				break;
			}
		}
		return s;
	}

	/**
	 * ��ײ��⣬�����߽����������
	 * 
	 * @return
	 */
	public boolean validate() {
		return checkCollision() && checkSelf() && eatSelf();
	}

	private boolean checkSelf() {
		return true;
	}

	/**
	 * �߽��� ������ײ����ture
	 * 
	 * @return
	 */
	private boolean checkCollision() {
		Node head = nodes.get(nodes.size() - 1);
		int r = head.getRow() + offsets[direction.ordinal()][0];
		int c = head.getCol() + offsets[direction.ordinal()][1];
		Boolean judge = (r >= 0 && r < GameConfig.getRows())
				&& (c >= 0 && c < GameConfig.getCols());
		if (judge == false) {
			GameController.getTimer().stop();  // ��ǰֹͣ��ʱ��
			String mes = "��ײǽ�ˣ���Ϸ����";
			JOptionPane.showMessageDialog(null,
					"                     " + mes, "��ʾ",
					JOptionPane.PLAIN_MESSAGE);
		}
		return judge;
	}

	/**
	 * �Ե������巵��false
	 * 
	 * @return
	 */
	private boolean eatSelf() {
		if (GameController.getFlag2() == 0) {
			return true;
		}
		Node head = nodes.get(nodes.size() - 1);
		Node n = new Node(head.getRow(), head.getCol());
		n.setRow(head.getRow() + offsets[direction.ordinal()][0]);
		n.setCol(head.getCol() + offsets[direction.ordinal()][1]);

		for (Node node : nodes) {
			if (n.equals(node)) { // �Ե�����false
				GameController.getTimer().stop();
				String mes = "��Ե��Լ��ˣ���Ϸ����";
				JOptionPane.showMessageDialog(null, "                    "
						+ mes, "��ʾ", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
		}
		// δ�Ե�����true
		return true;
	}
	
}
