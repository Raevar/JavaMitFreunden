import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow implements Updater {
	JFrame frame;
	JPanel panel;
	User player;
	public static final int size = 100;
	public static final int width = 1920;
	public static final int height = 1080;
	private static GameWindow instance = new GameWindow();
	ArrayList<Blocks> obstacles;
	ArrayList<Square> ground;
	private int r, g, b;
	Color c;
	Time time;
	public static boolean gameover = false;

	private GameWindow() {
		r = 100;
		b = 172;
		g = 50;
		c = new Color(r, g, b);
		ground = new ArrayList<Square>();
		obstacles = new ArrayList<Blocks>();
		player = new User();
		new Timer(10, player);
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				case 27:
					System.exit(0);
					break;
				case 32:
					player.jump();
					break;
				}
				if (arg0.getKeyCode() == 27) {
					System.exit(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		panel = new JPanel();
		time = new Time(0, 0);
		panel.add(time);
		panel.setLayout(null);
		frame.setLayout(null);
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		panel.setBackground(Color.black);
		frame.add(panel);
		panel.add(player);
		frame.setVisible(true);
		setUp();
	}

	public int checkBottom(int x, int y) // Überprüfung Punkt unten (Übergebener Punkt ist Links Oben)
	{ // 0 kein Boden 1 Boden 2 GameOver
		if (y > height)
			return 2;
		if (y == height - 2 * size) {
			for (Blocks o : ground) {
				if (o.onTop(x, y, size, size))
					return 1;
			}
		} else {
			for (Blocks o : obstacles) { // TODO <= +24-25
				if (o.onTop(x, y, size, size)) {
					if (o instanceof Square)
						return 1;
					else
						return 2;
				}
			}
		}
		return 0;
	}

	public int checkSide(int x, int y) {
		// 0 alles in Ordnung 2 GameOver
		for (Blocks o : obstacles) {
			if (o.isSide(x, y, size, size)) {
				return 2;
			}
		}
		return 0;
	}

	public void GameOver() {
		gameover = true;
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
		}
		while (!obstacles.isEmpty()) {
			Blocks o = obstacles.remove(0);
			panel.remove(o);
		}
		while (!ground.isEmpty()) {
			Blocks o = ground.remove(0);
			panel.remove(o);
		}
		setUp();
		frame.repaint();
	}

	public void Obstacle(int x, Color color) {
		int e = (int) (Math.random() * 7);
		if (e == 0) {
			e = (int) (Math.random() * 2) + 1;
			int y = height - size * 2;
			for (int i = 0; i < e; i++) {
				Blocks s = null;
				if (i == e - 1) {
					int n = (int) (Math.random() * 2);
					if (n == 0) {
						s = new Spikes(x, y + size / 2, size, size / 2, c);
					} else
						s = new Square(x, y, size, size, c);
				} else {
					s = new Square(x, y, size, size, c);
				}
				y -= size;
				obstacles.add(s);
				panel.add(s);
			}
		}
	}

	private void setUp() {
		time.reset();
		for (int i = 0; i < width; i += size) {
			Square s = new Square(i, height - size, size, size, c);
			panel.add(s);
			ground.add(s);
		}

	}

	public void update() {
		if (gameover)
			return;
		scroll();
	}

	public void scroll() {
		for (int i = 0; i < ground.size(); i++) {
			ground.get(i).scroll();
			if (ground.get(i).getX() + size * 2 < 0) {
				ground.remove(i);
				panel.remove(ground.get(i));
			}
		}
		if (ground.get(ground.size() - 1).getX() < width) {
			r += (int) (Math.random() * 5) - 2;
			b += (int) (Math.random() * 5) - 2;
			g += (int) (Math.random() * 5) - 2;
			if (r < 30)
				r = 200;
			else if (r > 220)
				r = 50;
			if (g < 30)
				g = 200;
			else if (g > 220)
				g = 50;
			if (b < 30)
				b = 200;
			else if (b > 220)
				b = 50;
			c = new Color(r, g, b);
			Square s = new Square(ground.get(ground.size() - 1).getX() + size, height - size, size, size, c);
			ground.add(s);
			panel.add(s);
			Obstacle(s.getX(), c);
		}
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).scroll();
			if (obstacles.get(i).getX() + size < 0) {
				panel.remove(obstacles.get(i));
				obstacles.remove(i);
			}
		}
	}

	public static GameWindow getInstance() {
		return instance;
	}
}
