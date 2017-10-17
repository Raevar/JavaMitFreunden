import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Blocks extends JPanel {
	Color color;
	Graphics g;

	public Blocks(int x, int y,int width,int height, Color color) {
		this.color = color;
		setBounds(x, y, width+1,height);
		setBackground(Color.black);
	}

	public void scroll() {
		setLocation(getX() - 15, getY());
	}

	public boolean onTop(int x, int y, int w, int h) {
		return getY() == y + h && getX() <= x + w && getX() + getWidth() > x;
	}

	public boolean isSide(int x, int y, int w, int h) {
		if (x + w < getX() || x > getX() + getWidth() || y + h <= getY() || y >= getY() + getHeight())
			return false;
		return true;
	}
	public String toString()
	{
		return "Block, " + getX() + " " + getY();
	}

}
