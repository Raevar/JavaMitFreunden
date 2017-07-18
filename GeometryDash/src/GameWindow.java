import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow
{
	JFrame						frame;
	JPanel						panel;
	User						player;
	public static final int		width		= 1920;
	public static final int		height		= 1080;
	private static GameWindow	instance	= new GameWindow();
	ArrayList<Obstacles>		obstacles;
	ArrayList<Square>			ground;

	private GameWindow()
	{
		ground = new ArrayList<Square>();
		obstacles = new ArrayList<Obstacles>();
		player = new User();
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent arg0)
			{
				switch (arg0.getKeyCode())
				{
					case 27:
						System.exit(0);
						break;
					case 32:
						player.jump();
						break;
				}
				if (arg0.getKeyCode() == 27)
				{
					System.exit(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{
				// TODO Auto-generated method stub

			}

		});
		panel = new JPanel();
		panel.setLayout(null);
		frame.setLayout(null);
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		panel.setBackground(Color.black);
		frame.add(panel);
		panel.add(player);
		frame.setVisible(true);
		setUp();
	}

	public int checkGround(int x, int y) // Überprüfung Punkt unten
	{ // 0 kein Boden 1 Boden 2 Spikes
		if (y == height - 50)
		{
			for (int i = 0; i < ground.size(); i++)
			{
				if (ground.get(i).getX() <= x && ground.get(i).getX() + ground.get(i).getWidth() > x)
				{
					return 1;
				}
			}
			return 0;
		} else
		{
			for (int i = 0; i < obstacles.size(); i++)
			{ //TODO <= +24-25
				if (obstacles.get(i).getX() < x+24 && obstacles.get(i).getX() + obstacles.get(i).getWidth() >= x-24
						&& obstacles.get(i).getY() == y)
				{
					if (obstacles.get(i) instanceof Square)
					{
						return 1;
					}

				}
			}
			return 0;
		}
	}

	public void Obstacle(int x, Color color)
	{
		int e = (int) (Math.random() * 7);
		if (e == 0)
		{
			e = (int) (Math.random() * 2) + 1;
			int y = height - 100;
			for (int i = 0; i < e; i++)
			{
				Square s = new Square(x, y, color);
				y -= 50;
				obstacles.add(s);
				panel.add(s);
			}
		}
	}

	private void setUp()
	{
		for (int i = 0; i < width; i += 50)
		{
			Square s = new Square(i, height - 50, i % 100 == 0 ? Color.gray : Color.LIGHT_GRAY);
			panel.add(s);
			ground.add(s);
		}
		// frame.repaint();
	}

	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(1);
			} catch (InterruptedException e)
			{
				// TODO: handle exception
			}
			scroll();
		}
	}

	public void scroll()
	{
		for (int i = 0; i < ground.size(); i++)
		{
			ground.get(i).scroll();
			if (ground.get(i).getX() + 50 < 0)
			{
				ground.remove(i);
				panel.remove(ground.get(i));
			}
		}
		if (ground.get(ground.size() - 1).getX() < width)
		{
			Square s = new Square(ground.get(ground.size() - 1).getX() + 50, height - 50,
					ground.get(ground.size() - 2).color);
			ground.add(s);
			panel.add(s);
			Obstacle(s.getX(), s.color); // TODO
		}
		for (int i = 0; i < obstacles.size(); i++)
		{
			obstacles.get(i).scroll();
			if (obstacles.get(i).getX() + 50 < 0)
			{
				panel.remove(obstacles.get(i));
				obstacles.remove(i);
			}
		}
	}

	public static GameWindow getInstance()
	{
		return instance;
	}
}
