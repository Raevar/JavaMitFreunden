import java.awt.Color;
import java.awt.Graphics;

public class Square extends Obstacles {
	public Square(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
