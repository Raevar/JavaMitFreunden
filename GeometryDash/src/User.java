import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class User extends JPanel implements Runnable
{
	public static final int	gravity		= 1;
	boolean					isGround	= true;
	int						vy			= 0;
	boolean					jump		= false;

	public User()
	{
		// setBackground(Color.white);
		setBounds(100, GameWindow.height - 100, 50, 50);
		Thread t = new Thread(this);
		t.isDaemon();
		t.start();
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.red);
		g.fillOval(0, 0, getWidth(), getHeight());
		// g.fillPolygon(new int[] {0,0,getWidth()}, new int[]
		// {0,getHeight(),0}, 3);
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(10);
			} catch (InterruptedException e)
			{

			}
			checkGround();
			if (!isGround || jump)
			{
				vy += gravity;
				if (vy == 0)
					jump = false;
				if (jump)
				{
					for (int i = 0; i > vy; i--)
					{
						setLocation(getX(), getY() - 1);
					}
				} else
				{
					for (int i = 0; i < vy; i++)
					{
						checkGround();
						if (isGround)
							break;
						setLocation(getX(), getY() + 1);
					}

				}
			}
		}
	}

	public void jump()
	{
		if (isGround)
		{
			vy = -20;
			jump = true;
			isGround = false;
		}
	}

	public void checkObstacle()
	{

	}

	public void checkGround()
	{ // 0 kein Boden 1 Boden 2 Spikes^
		int x = GameWindow.getInstance().checkGround(getX() + getWidth() / 2, getY() + getHeight());
		switch (x)
		{
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
}
