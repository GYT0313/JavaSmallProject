import game.control.GameController;
import javax.swing.SwingUtilities;


/**
 * 游戏入口，创建游戏中控制对象，并显示界面
 * @author GuYongtao
 *
 */
public class GameEntry {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				GameController game = new GameController();
				game.show();
			}
		});
	}
}
