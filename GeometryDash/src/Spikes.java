import java.awt.Color;
import java.awt.Graphics;

public class Spikes extends Dangerous {

	public Spikes(int x, int y, int width, int height, Color color) {
		super(x, y - height, width, height * 2, color);
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillPolygon(new int[] { 0, getWidth() / 2, getWidth() },
				new int[] { getHeight(), getHeight() / 2, getHeight() }, 3);
	}

	public boolean onTop(int x, int y, int w, int h) {
		return isHit(x, y, w, h);
	}

	public boolean isSide(int x, int y, int w, int h) {
		return isHit(x, y, w, h);
	}

	public boolean isHit(int x, int y, int w, int h) {
		int relXLeft = x - getX();
		int relXRight = relXLeft + w;
		int relY = y + h - getY();
		if (relXRight < 0 || relXLeft > getWidth() || relY < getHeight() / 2 || relY - h > getHeight())
			return false;
		if (relXLeft > 50) {
			if (relXLeft < relY)
				return false;
			return true;
		}
		if (relXRight < getHeight() - relY)
			return false;
		return true;
	}
}
