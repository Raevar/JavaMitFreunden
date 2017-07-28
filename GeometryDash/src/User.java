import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class User extends JPanel implements Updater {
	public static final int gravity = 1;
	boolean isGround = true;
	int vy = 0;
	boolean jump = false;

	public User() {
		setBounds(100, GameWindow.height - GameWindow.getInstance().size*2, GameWindow.getInstance().size, GameWindow.getInstance().size);
	}

	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(0, 0, getWidth(), getHeight());
	}

	public void jump() {
		if (isGround) {
			vy = -23;
			jump = true;
			isGround = false;
		}
	}

	public void checkObstacle() {

	}

	public void checkGround() { // 0 kein Boden 1 Boden 2 Spikes^
		int x = GameWindow.getInstance().checkGround(getX() + getWidth() / 2, getY() + getHeight());
		switch (x) {
		case 0:
			isGround = false;
			break;
		case 1:
			isGround = true;
			break;
		case 2:
			break;
		}
	}

	public void update() {
		checkGround();
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
