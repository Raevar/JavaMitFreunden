import java.awt.Color;
import java.awt.Graphics;

public class Ramp extends Friendly {


	public Ramp(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillPolygon(new int[] { 0, getWidth(), getWidth() }, new int[] { getHeight(), getHeight(), getHeight() }, 3);
	}

	public boolean isSide(int x, int y, int width, int height) {

		return false;
	}

}
