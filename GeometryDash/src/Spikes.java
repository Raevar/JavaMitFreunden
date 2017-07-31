import java.awt.Color;
import java.awt.Graphics;

public class Spikes extends Obstacles {

	public Spikes(int x, int y,int width, int height, Color color) {
		super(x, y,width,height, color);
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillPolygon(new int[] { 0, getWidth() / 2, getWidth() }, new int[] { getHeight(), 0, getHeight() }, 3);
	}

//	public boolean onTop(int x, int y, int w, int h) {
//		return getY() == y + h && getX() <= x + w && getX() + getWidth() > x;
//	}
//
//	public boolean isSide(int x, int y, int w, int h) {
//		if (x + w < getX() || x > getX() + getWidth() || y + h <= getY() || y >= getY() + getHeight())
//			return false;
//		if (x > getX() + getWidth() / 2)//rechts
//		{
//			
//		} else // links
//		{
//			int d = (x - getX())* 2;
//			if(y+ h + d < getY())
//				return false;
//			return true;
//		}
//		if (x + w < getX() || x > getX() + getWidth() || y + h <= getY() || y >= getY() + getHeight())
//			return false;
//		return true;
//	}
}
