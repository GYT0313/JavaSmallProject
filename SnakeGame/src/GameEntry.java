import game.control.GameController;
import javax.swing.SwingUtilities;


/**
 * ��Ϸ��ڣ�������Ϸ�п��ƶ��󣬲���ʾ����
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
