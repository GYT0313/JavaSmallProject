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
 * 功能：提供游戏中蛇相关的数据和行为封装。信息包括蛇的节点集、移动方向等 功能包括蛇的移动、增长、吃食物及检测等。
 * 
 * 考虑到顺序表的操作效率，具体功能实现时只在顺序表的尾部进行以减少顺序表中的元素移动，因此将蛇的头部保存在顺序表的尾部，
 * 蛇增长的时候也从顺序表尾部追加并修改蛇头部为新增节点
 * 
 * @author GuYongtao
 *
 */
public class Snake {
	// 成员属性
	private ArrayList<Node> nodes = new ArrayList<>(); // 节点集
	private Direction direction = Direction.Right; // 默认移动方向为右
	private int[][] offsets; // 定义偏移数组，用于取消分支 简化程序结构

	/**
	 * 构造方法
	 */
	public Snake() {
		defaultInit();
		offsets = new int[][] { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } }; // 左    x-
																			// 上    y-
																			// 右    x+
																			// 下    y+
	}

	/**
	 * 默认初始化操作，单局游戏开始，蛇对象都需要重新初始化
	 */
	public void defaultInit() {
		nodes.clear();
		// 初始时，蛇对象默认包括三个节点，中间靠左水平排列
		int r = GameConfig.getRows() / 2 - 1;
		nodes.add(new Node(r, 0));
		nodes.add(new Node(r, 1));
		nodes.add(new Node(r, 2));
		direction = direction.Right;
	}

	/**
	 * 蛇移动算法：除尾部节点(蛇头)，后面节点依次前进为上一个节点 头部节点根据当前移动方向前进修改，利用预定义的偏移数组简化实现
	 */
	public void move() {
		if (GameController.getFlag2() == 1) {
			// 循环移动
			for (int i = 0; i < nodes.size() - 1; i++) {
				Node n = nodes.get(i), next = nodes.get(i + 1);
				n.setRow(next.getRow());
				n.setCol(next.getCol());
			}
			// 头部移动
			Node head = nodes.get(nodes.size() - 1); // 头部
			head.setRow(head.getRow() + offsets[direction.ordinal()][0]); // 根据二维数据实现移动
			head.setCol(head.getCol() + offsets[direction.ordinal()][1]);
		}
		
	}

	/**
	 * 蛇转向操作，要求同直线方向的反方向不能操作，即右时不能向左，下时不能向上，其余同理。 方法实现时利用了枚举单元序号间的关系，间距为2即同直线方向
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
	 * 判断蛇是否迟到食物节点，通过蛇头部的下一个位置是否与食物节点相等进行判断 仅仅是判断，蛇头部本身并没有发生移动
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
	 * 蛇吃到食物增长，修改食物节点类型
	 * 
	 * @param n
	 */
	public void grow(Node n) {
		n.setType(NodeType.Snake);
		nodes.add(n);
	}

	/**
	 * 绘制蛇，蛇通知节点集中进行绘制
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		for (Node n : nodes) {
			n.draw(g);
		}
	}

	/**
	 * 创建食物时，食物节点位置是否在蛇身上，在即不合法
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
	 * 碰撞检测，包括边界检查和自身检查
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
	 * 边界检测 不会碰撞返回ture
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
			GameController.getTimer().stop();  // 提前停止计时器
			String mes = "你撞墙了，游戏结束";
			JOptionPane.showMessageDialog(null,
					"                     " + mes, "提示",
					JOptionPane.PLAIN_MESSAGE);
		}
		return judge;
	}

	/**
	 * 吃到蛇身体返回false
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
			if (n.equals(node)) { // 吃到返回false
				GameController.getTimer().stop();
				String mes = "你吃到自己了，游戏结束";
				JOptionPane.showMessageDialog(null, "                    "
						+ mes, "提示", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
		}
		// 未吃到返回true
		return true;
	}
	
}
