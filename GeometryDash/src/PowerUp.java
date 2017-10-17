import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PowerUp extends JPanel {
	public PowerUp(int x, int y) {
		setBounds(x, y, 100, 100);
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(25, 25, 50, 50);
	}

	public void scroll() {
		setLocation(getX() - 1, getY());
	}

	public void pressable(int x, int y, int w, int h) {
	}

	public void pressed() {

	}

	public void performAction() {

	}
}
