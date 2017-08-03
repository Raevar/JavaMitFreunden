import java.awt.Color;

import javax.swing.JPanel;

public class Blocks extends JPanel {
	Color color;

	public Blocks(int x, int y,int width,int height, Color color) {
		this.color = color;
		setBounds(x, y, width+1,height);
		setBackground(Color.black);
	}

	public void scroll() {
		setLocation(getX() - 1, getY());
	}

	public boolean onTop(int x, int y, int w, int h) {
		return getY() == y + h && getX() <= x + w && getX() + getWidth() > x;
	}

	public boolean isSide(int x, int y, int w, int h) {
		if (x + w < getX() || x > getX() + getWidth() || y + h <= getY() || y >= getY() + getHeight())
			return false;
		return true;
	}

}
