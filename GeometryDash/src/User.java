import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class User extends JPanel implements Updater {
	public static final int gravity = 1;
	boolean isGround = true;
	int vy = 0;
	boolean jump = false;
	boolean gameover = false;

	public User() {
		setBounds(100, GameWindow.height - GameWindow.size * 2, GameWindow.size,
				GameWindow.size);
	}

	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.blue);
		g.fillRect(25,25, getWidth()-50, getHeight()-50);
	}

	public void jump() {
		if (isGround) {
			vy = -23;
			jump = true;
			isGround = false;
			// could change color at jump;
			// GameWindow.getInstance().c = new Color((int) (Math.random()*256),(int)
			// (Math.random()*256),(int) (Math.random()*256));
		}
	}

	public void checkSide() {
		switch (GameWindow.getInstance().checkSide(getX(), getY())) {
		case 0:
			break;
		case 2:
			GameOver();
			break;
		}
	}

	public void checkGround() { // 0 kein Boden 1 Boden 2 Spikes^
		int x = GameWindow.getInstance().checkBottom(getX(), getY());
		switch (x) {
		case 0:
			isGround = false;
			break;
		case 1:
			isGround = true;
			break;
		case 2:
			GameOver();
			break;
		}
	}

	private void GameOver() {

		GameWindow.gameover = true;
		GameWindow.getInstance().GameOver();
		setBounds(100, GameWindow.height - GameWindow.size * 2 - 5, GameWindow.size,
				GameWindow.size);
		GameWindow.gameover = false;
	}

	public void update() {
		if (GameWindow.gameover)
			return;
		checkGround();
		checkSide();
		if (!isGround || jump) {
			vy += gravity;
			if (vy == 0)
				jump = false;
			if (jump) {
				for (int i = 0; i > vy; i--) {
					setLocation(getX(), getY() - 1);
				}
			} else {
				for (int i = 0; i < vy; i++) {
					checkGround();
					if (isGround) {
						vy = 0;
						break;
					}
					setLocation(getX(), getY() + 1);
				}
			}
		}
	}
}
