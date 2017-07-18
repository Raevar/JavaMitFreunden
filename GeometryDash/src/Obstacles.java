import java.awt.Color;

import javax.swing.JPanel;

public class Obstacles extends JPanel
{
	Color color;

	public Obstacles(int x, int y, Color color)
	{
		this.color = color;
		setBounds(x, y, 50, 50);
	}

	public void scroll()
	{
		setLocation(getX() - 1, getY());
	}

	public boolean onTop(int x, int y, int w, int h)
	{
		return ((x + w > getX() || x < getX() + getHeight()) && getY() == y + h);
	}

	public boolean onLeft(int x, int y, int w, int h)
	{
		return (x + w == getY() && (y + h < getY() || y > getY() + getHeight()));
	}

}
